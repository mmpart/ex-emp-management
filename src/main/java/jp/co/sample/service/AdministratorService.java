package jp.co.sample.service;
/**
 * 管理者情報を操作するサービス.
 * @author rakus
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.repository.AdministoratorRepository;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministoratorRepository administoratorrepository;
}
