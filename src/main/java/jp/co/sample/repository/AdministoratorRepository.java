package jp.co.sample.repository;

/**
 * 
 * @author rakus
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministoratorRepository {

	/** templateの変数宣言*/
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/** Administratorオブジェクトを生成するRowMapper*/
	public static final RowMapper<Administrator>ADMINISTORATOR_ROW_MAPPER = (rs,i) -> {
		Administrator administorator = new Administrator();
		administorator.setId(rs.getInt("id"));
		administorator.setName(rs.getString("name"));
		administorator.setMailAddress(rs.getString("mailAddress"));
		administorator.setPassword(rs.getString("password"));
		return administorator;
	};
	
	/** 管理者情報を挿入する*/
	public void insert(Administrator administrator) {
		String sql = "INSERT INTO administrators (name,mail_address,password) VALUES (:name, :mailAddress, :password) ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("administrator", administrator);
		template.update(sql, param);
	}
	
	/** 
	 * メールアドレスとパスワードから管理者情報を取得する
	 *  存在しない場合はnullを返す
	 */
	public List<Administrator> findByMailAddressAndPassword(String mailAddress, String password) {
		
		if (mailAddress =  & password = ) {
			String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress AND password=:password ORDER BY id";
			
			
		} else {
			return null;
		}
	}
	
}
