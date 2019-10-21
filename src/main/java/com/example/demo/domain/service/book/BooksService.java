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

	public Book selectOne(int bookId) {
		return booksrepo.selectOne(bookId);
	}

	public boolean insertOne(Book book) {

		int rowNumber = booksrepo.insertOne(book);

		boolean result = false;

		if(rowNumber > 0) {
			result = true;
		}

		return result;
	}

}
