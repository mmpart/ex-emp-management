package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministoratorForm() {
		return new InsertAdministratorForm();
	}
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	/**
	 * 管理者情報登録画面へ遷移する.
	 * 
	 * @return 管理者情報登録画面
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
		
		return "redirect:/";
	}
	
	/**
	 * ログイン画面に遷移する.
	 * 
	 * @return ログイン画面
	 * */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	
	
	
	/**
	 * メールアドレスとパスワードでログイン後のページへ遷移する.
	 * 
	 * @param form ログインフォーム
	 * @param model　リクエストスコープ
	 * @return 従業員情報一覧(メールアドレスとパスワードが不正の時はメッセージ表示)
	 */
	@RequestMapping("/login")
	public String login(
			@Validated LoginForm form
		   ,Model model
		   ) {
		
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		
		if(administrator == null) {
			String errormesssage = "メールアドレスまたはパスワードが不正です。";
			model.addAttribute("errormesssage", errormesssage);
		} else {
			session.setAttribute("administratorName",administrator.getName());
		}
		return "forward:/employee/showList";
	}
}
