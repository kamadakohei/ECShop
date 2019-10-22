package com.example.demo.domain.repository.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Customer;

@Repository
public class CustomersRepository {

	@Autowired
	JdbcTemplate jdbc;

	public int signupCustomer(Customer customer) throws DataAccessException {
		int rowNumber = jdbc.update("insert into customers(customer_name, password, del_flag)" +
				"values(?, ?, ?)",customer.getCustomerName(),customer.getPassword(),customer.getDel_flag());
		return rowNumber;
	}
}
