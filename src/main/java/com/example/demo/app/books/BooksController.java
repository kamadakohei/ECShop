package com.example.demo.app.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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

	@GetMapping ("/bookDetail/{id}")
	public String getBookDetail(@ModelAttribute AddBooksForm form, Model model, @PathVariable("id") String bookId) {

		System.out.println("bookId=" + bookId);
		if(bookId != null && bookId.length()>0) {

			Book book = booksService.selectOne(bookId);

			form.setBookId(book.getBookId());
			form.setBookName(book.getBookName());
			form.setDescription(book.getDescription());
			form.setPrice(book.getPrice());
			form.setImage(book.getImage());
			form.setDel_flag(book.getDel_flag());

			//model.addAttribute("addbooksform", form);
		}
		return "books/bookDetail";
	}


}
