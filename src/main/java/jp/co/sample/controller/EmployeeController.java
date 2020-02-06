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
	 * 従業員IDをもとに従業員情報を取得する.
	 * 
	 * @param id 従業員ID
	 * @param model リクエストスコープ
	 * @return　従業員情報
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		model.addAttribute("employee", employeeService.showDetail(Integer.parseInt(id)));
		return "employee/detail";
	}
	
	/**
	 * 従業員詳細（扶養人数）を更新する.
	 * 
	 * @param form アップデートフォーム
	 * @return　従業員一覧
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
	
}
