package com.example.demo.domain.model;

import lombok.Data;

@Data
public class Customer {
	private String customerCode;
	private String password;
	private int del_flag;
	private String role;
}
