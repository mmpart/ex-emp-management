package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.InsertAdministoratorForm;
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
	public InsertAdministoratorForm setUpInsertAdministoratorForm() {
		return new InsertAdministoratorForm();
	}
	
	/**
	 * @return 初期画面
	 * */
	@RequestMapping("/toInsert")
	public String toInsert(){
		return "administrator/insert";
	}
	
	

}
