package com.example.demo.domain.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Customer;
import com.example.demo.domain.repository.customers.CustomersRepository;

@Service
public class CustomersService {
	@Autowired
	CustomersRepository customersrepo;

	public boolean signupCustomer(Customer customer) {

		int rowNumber = customersrepo.signupCustomer(customer);

		boolean result = false;

		if(rowNumber > 0) {
			result = true;
		}

		return result;
	}
}
