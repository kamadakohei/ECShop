package com.example.demo.domain.repository.books;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.Book;

@Repository
@Transactional
public class BooksRepository {

	@Autowired
	JdbcTemplate jdbc;


	public List<Book> selectMany() throws DataAccessException {

		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM books");
		List<Book> bookList = new ArrayList<>();

		for(Map<String, Object> map:getList) {

			Book book = new Book();

			book.setBookId(UUID.fromString((String)map.get("book_id")));
			book.setBookName((String)map.get("book_name"));
			book.setDescription((String)map.get("description"));
			book.setPrice((int)map.get("price"));
			book.setImage((String)map.get("image"));
			book.setDel_flag((int)map.get("del_flag"));

			bookList.add(book);
		}

	return bookList;
	}
}
