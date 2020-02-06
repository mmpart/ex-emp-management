package jp.co.sample.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を検索する.
 * 
 * @author mizuki.tanimori
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@ModelAttribute
	public UpdateEmployeeForm setUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	/**
	 * 従業員情報を全件取得する.
	 * 
	 * @param model リクエストスコープ
	 * @return 従業員情報
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		model.addAttribute("employeeList", employeeService.showList());
		return "employee/list";
	}
	
	/**
	 * 従業員IDをもとに従業員情報を取得する
	 * 
	 * @param id 従業員ID
	 * @param model リクエストスコープ
	 * @return　従業員情報
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		int iD = Integer.parseInt(id);
		Employee employee = employeeService.showDetail(iD);
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
}
