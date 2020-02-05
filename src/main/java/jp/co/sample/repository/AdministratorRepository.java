package jp.co.sample.repository;

/**
 * 管理者情報テーブルを操作するリポジトリ.
 * 
 * @author rakus
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Administratorオブジェクトを生成するローマッパー.
	 */
	public static final RowMapper<Administrator> ADMINISTORATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailAddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	/** 
	 * 管理者情報を挿入します.
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String sql = "INSERT INTO administrators (name,mail_address,password) VALUES (:name, :mailAddress, :password) ";
		template.update(sql, param);
	}

	/**
	 * メールアドレスとパスワードから管理者情報を取得します.
	 * 存在しない場合はnullを返す
	 * 
	 * @param mailAddress メールアドレス
	 * @param password パスワード
	 * @return 検索された管理者情報
	 * 
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address=:mailAddress AND password=:password ORDER BY id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",password);
		try {
			return template.queryForObject(sql, param, ADMINISTORATOR_ROW_MAPPER);
		} catch (Exception e) {
			return null;
		}
	}
}
