package com.example.demo.app.customers;

import lombok.Data;

@Data
public class  SignUpCustomersForm{
	private String customerCode;
	private String password;
	private int del_flag;
	private String role;
}
