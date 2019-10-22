package com.example.demo.domain.model;

import lombok.Data;

@Data
public class Book {
	private int bookId;
	private String bookName;
	private String description;
	private int price;
	private String image;
	private String customerCode;
	private int del_flag;
}
