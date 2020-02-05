package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.form.InsertAdministoratorForm;
import jp.co.sample.service.AdministratorService;

/**
 * 
 * @author rakus
 *
 */
@Controller
@RequestMapping("/")
public class AdministoratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	/** InsertAdministoratorFormのインスタンス化*/
	@ModelAttribute
	public InsertAdministoratorForm setUpInsertAdministoratorForm() {
		return new InsertAdministoratorForm();
	}
	
	/**「 administorator/insert.html」にフォワードする処理*/
	@RequestMapping("/toInsert")
	public String toInsert(){
		return "administorator/insert";
	}
	
	

}
