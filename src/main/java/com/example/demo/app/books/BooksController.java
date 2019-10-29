package com.example.demo.app.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public String getBooks(Model model, Authentication authentication){

		String customercode = authentication.getName();
		System.out.println(customercode);
		List<Book> bookList = booksService.selectMany();
		model.addAttribute("bookList", bookList);
		model.addAttribute("customercode", customercode);
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

	@GetMapping("/exhibitedBooks/{customercode}")
	public String getexhibitedBooks(Model model, @PathVariable("customercode") String customerCode) {

		List<Book> exibitedbookList = booksService.selectselectExhibitedBookList(customerCode);
		model.addAttribute("exibitedbookList", exibitedbookList);
		return "books/exhibitedBookList";
	}

	@GetMapping ("/updateBook/{id}")
	public String updateDetailBook(@ModelAttribute AddBooksForm form, Model model, @PathVariable("id") String Id) {

		System.out.println("bookId=" + Id);
		if(Id != null && Id.length()>0) {

			int bookId = Integer.parseInt(Id);
			Book book = booksService.selectOne(bookId);

			form.setBookId(bookId);
			form.setBookName(book.getBookName());
			form.setDescription(book.getDescription());
			form.setPrice(book.getPrice());
			form.setImage(book.getImage());
			form.setCustomerCode(book.getCustomerCode());
			form.setDel_flag(book.getDel_flag());

			//model.addAttribute("addbooksform", form);
		}
		return "books/updateBook";
	}

	@PostMapping("/updateBook/{id}")
	public String updateBook(@ModelAttribute AddBooksForm form, Model model, @PathVariable("id") String Id) {
		Book book = new Book();

		System.out.println(Id);
		book.setBookId(Integer.parseInt(Id));
		book.setBookName(form.getBookName());
		book.setDescription(form.getDescription());
		book.setPrice(form.getPrice());


		System.out.println(book);

		boolean result = booksService.updateOne(book);
		System.out.println(result);
		if(result = true) {
			model.addAttribute("result", "更新成功");
		}else {
			model.addAttribute("result", "更新失敗");
		}

		return "redirect:/";
	}

	@GetMapping("deleteBook/{id}")
	public String deteleBook(@ModelAttribute AddBooksForm form, Model model, @PathVariable("id") String id) {

		int bookId = Integer.parseInt(id);
		System.out.print(id);
		Book book = new Book();

		book.setBookId(bookId);

		boolean result = booksService.deleteOne(book);
		System.out.println(result);
		if(result = true) {
			System.out.println("成功");
		}else {
			System.out.println("失敗");
		}

		return "redirect:/";
	}








}
