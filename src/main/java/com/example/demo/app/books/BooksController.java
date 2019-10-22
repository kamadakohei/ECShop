package com.example.demo.app.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	@GetMapping("/addBooks")
	public String addBooks(@ModelAttribute AddBooksForm form, Model model) {
		return "books/addBooks";
	}

	@GetMapping ("/bookDetail/{id}")
	public String getBookDetail(@ModelAttribute AddBooksForm form, Model model, @PathVariable("id") String Id) {

		System.out.println("bookId=" + Id);
		if(Id != null && Id.length()>0) {

			int bookId = Integer.parseInt(Id);
			Book book = booksService.selectOne(bookId);

			form.setBookName(book.getBookName());
			form.setDescription(book.getDescription());
			form.setPrice(book.getPrice());
			form.setImage(book.getImage());
			form.setCustomerCode(book.getCustomerCode());
			form.setDel_flag(book.getDel_flag());

			//model.addAttribute("addbooksform", form);
		}
		return "books/bookDetail";
	}

	@PostMapping("/addBooks")
	public String addBooks(@ModelAttribute AddBooksForm form, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			return addBooks(form, model);
		}

		System.out.println(form);

		Book book = new Book();
		book.setBookName(form.getBookName());
		book.setDescription(form.getDescription());
		book.setPrice(form.getPrice());
		book.setImage("/imgs/kokoro.jpg");
		book.setCustomerCode("Kam");
		book.setDel_flag(0);

		System.out.println(book);

		boolean result = booksService.insertOne(book);

		if(result == true) {
			System.out.println("成功");
		}else {
			System.out.println("失敗");
		}

		return "redirect:/";
	}




}
