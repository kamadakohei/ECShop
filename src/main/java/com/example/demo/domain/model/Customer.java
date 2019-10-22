package com.example.demo.domain.model;

import lombok.Data;

@Data
public class Customer {
	private int customerId;
	private String customerName;
	private String password;
	private int del_flag;
}
