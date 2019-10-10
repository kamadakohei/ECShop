package com.example.demo.app.books;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BooksController {

	@GetMapping("/")
	public String getBooks(Model model){
		
		return "books/bookList";
	}


}
