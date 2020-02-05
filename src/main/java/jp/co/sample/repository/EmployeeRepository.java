package jp.co.sample.repository;
/**
 * 従業員情報テーブルを操作するリポジトリ.
 * 
 * @author rakus
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * @author mizuki.tanimori
 *
 */
@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public static final RowMapper<Employee>EMPLOYEE_ROW_MAPPER = (rs,i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hireDate"));
		employee.setMailAddress(rs.getString("mailAddress"));
		employee.setZipCode(rs.getString("zipCode"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependentsCount"));
		return employee;
	};
	
	/**
	 *  従業員一覧情報を入社日順で取得します.
	 * 
	 * 
	 * @return 従業員一覧情報（存在しない場合は0件の従業員一覧を返す）
	 */
	public List<Employee> findAll(){
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,depandents_count FROM employees ORDER BY hire_date";
		return template.query(sql, EMPLOYEE_ROW_MAPPER);
	}
	
	/**
	 *  主キーから従業員情報を取得します.
	 *  従業員が存在しない場合はSpringが自動的に例外を発生します
	 *  
	 *  @param id ID
	 *  @return　ID検索された情報
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,depandents_count FROM employees WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
	}
	
	/**
	 *  従業員情報を変更します.
	 *  従業員情報の扶養人数だけを更新できる
	 *  
	 *  @param employee 従業員情報
	 *  @return 更新された扶養家族数
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String sql = "UPDATE employees SET depandents_count=:departmentsCount WHERE id=:id";
		template.update(sql, param);
	}
	
}
