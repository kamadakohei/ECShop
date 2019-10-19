package com.example.demo.domain.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Book;
import com.example.demo.domain.repository.books.BooksRepository;

@Service
public class BooksService {

	@Autowired
	BooksRepository booksrepo;

	public  List<Book> selectMany(){

		return booksrepo.selectMany();
	}

}
