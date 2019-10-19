package com.example.demo.domain.model;

import java.util.UUID;

import lombok.Data;

@Data
public class Book {
	private UUID bookId;
	private String bookName;
	private String description;
	private int price;
	private String image;
	private int del_flag;
}
