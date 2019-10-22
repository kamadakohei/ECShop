package com.example.demo.app.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.service.customer.CustomersService;

@Controller
public class CustomersController {

	@Autowired
	CustomersService customerService;

	@GetMapping("/customers")
	public String signupCustomers(@ModelAttribute SignUpCustomersForm form, Model model) {
		return "customers/signupCustomers";
	}

	@PostMapping("/customers")
	public String signupCustomers(@ModelAttribute SignUpCustomersForm form, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			return signupCustomers(form, model);
		}

		Customer customer = new Customer();
		customer.setCustomerName(form.getCustomerName());
		customer.setPassword(form.getPassword());
		customer.setDel_flag(0);

		System.out.println(customer);

		boolean result = customerService.signupCustomer(customer);

		if(result == true) {
			System.out.println("成功");
		}else {
			System.out.println("失敗");
		}

		return "redirect:/";
	}
}

