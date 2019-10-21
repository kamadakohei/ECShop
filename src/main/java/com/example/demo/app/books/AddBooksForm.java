package com.example.demo.app.books;

import lombok.Data;

@Data
public class AddBooksForm {
	private int bookId;
	private String bookName;
	private String description;
	private int price;
	private String image;
	private int del_flag;
}
