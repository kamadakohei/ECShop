package com.example.demo.app.customers;

import lombok.Data;

@Data
public class  SignUpCustomersForm{
	private int customerId;
	private String customerName;
	private String password;
	private int del_flag;
}
