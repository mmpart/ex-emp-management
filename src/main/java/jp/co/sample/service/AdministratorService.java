package jp.co.sample.service;
/**
 * 管理者情報を操作するサービス.
 * @author rakus
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;


@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;
	
	/**
	 * 管理者情報を挿入します.
	 * 
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	
	/**
	 * ログイン処理を行います.
	 * 
	 * @param mailAddress　メールアドレス
	 * @param password パスワード
	 * @return 戻ってきた管理者情報をそのまま返す
	 */
	public Administrator login(String mailAddress, String password) {
		return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
	}
}
