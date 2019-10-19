package com.example.demo.app.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.model.Book;
import com.example.demo.domain.service.book.BooksService;


@Controller
public class BooksController {

	@Autowired
	BooksService booksService;

	@GetMapping("/")
	public String getBooks(Model model){

		List<Book> bookList = booksService.selectMany();

		model.addAttribute("bookList", bookList);

		return "books/bookList";
	}


}
