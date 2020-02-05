package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者登録画面を表示する処理を記述するコントローラー.
 * 
 * @author rakus
 *
 */
@Controller
@RequestMapping("/")
public class AdministoratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministoratorForm() {
		return new InsertAdministratorForm();
	}
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * @return 初期画面
	 * */
	@RequestMapping("/toInsert")
	public String toInsert(){
		return "administrator/insert";
	}
	
	/**
	 * 管理者情報を登録するメソッド.
	 * 
	 * @param form フォーム
	 * @return 初期画面
	 * */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form){
		Administrator administrator = new Administrator();
		//formオブジェクトからadministratorオブジェクトにプロパティ値をコピー
		BeanUtils.copyProperties(form, administrator);
		
		administratorService.insert(administrator);
		
		return "/";
	}
	
	/**
	 * @return 初期画面
	 * */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
}
