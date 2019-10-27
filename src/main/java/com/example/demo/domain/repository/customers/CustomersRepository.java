package com.example.demo.domain.repository.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Customer;

@Repository
public class CustomersRepository {

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	PasswordEncoder passwordEncoder;

	public int signupCustomer(Customer customer) throws DataAccessException {

		String password = passwordEncoder.encode(customer.getPassword());
		int rowNumber = jdbc.update("insert into customers(customer_code, password, del_flg, role)" +
				" values(?, ?, ?, ?)",customer.getCustomerCode(),password,customer.getDel_flag(), customer.getRole());
		return rowNumber;
	}

}
