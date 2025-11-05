package com.app.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.errors.EmployeeNotFoundExeption;
import com.app.model.Employee;
import com.app.service.IEmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee") // for thymeleaf it necessary
public class EmployeeOperationController {
	@Autowired
	private IEmployeeService service;

	@GetMapping("/")
	public String showHome() {
		return "home";
	}

	@GetMapping("/emp_report")
	public String showEmployeeReport(Map<String, Object> map) {
		Iterable<Employee> itEmps = service.getALLEmployee();
		map.put("emplist", itEmps);
		return "show_employee_report";
	}

	@GetMapping("/emp_add") // for form launching
	public String showAddEmployee(@ModelAttribute("emp") Employee e, Map<String, Object> map) {
		map.put("emp", new Employee());
		return "register_employee";
	}

	@PostMapping("/emp_register")
	public String saveEmployee(@ModelAttribute("emp") Employee e, RedirectAttributes ses, HttpServletRequest request) {
		String referer = request.getHeader("referer");
		System.out.println("referer::" + referer);
		String msg = service.saveEmployee(e);
		String msgNew = null;
		if (referer.contains("emp_edit")) {
			msgNew = "employee updated succesfully" + msg;
		} else if (referer.contains("emp_delete")) {
			msgNew = "employee deleted succesfully" + msg;
		} else {
			msgNew = "employee added  successfully" + msg;
		}
		ses.addAttribute("resultmsg", msgNew); // only for redirection activiy the msg will be there\
		return "redirect:emp_report";
	}

	@GetMapping("/emp_edit")
	public String showEditFormPage(@ModelAttribute("emp") Employee empS, @RequestParam("no") Integer no,
			HttpSession ses ,Model model) {
		try {
			Employee empdb = service.getEmployeeByNo(no);
			ses.setAttribute("resultmsg", "Employee Successfully updated with Id::" + empdb.getEno());
			BeanUtils.copyProperties(empdb, empS);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			model.addAttribute("code", HttpStatus.BAD_GATEWAY);
			return "error";
		}
		return "update_employee";
	}

	@GetMapping("/emp_delete")
	public String deleteFormPage(@RequestParam("no") Integer no) {
		service.deleteEmployyeByNo(no);
		return "redirect:emp_report";
	}
}
