package com.example.demo.domain.repository.books;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
			book.setBookId((int)map.get("book_Id"));
			book.setBookName((String)map.get("book_name"));
			book.setDescription((String)map.get("description"));
			book.setPrice((int)map.get("price"));
			book.setImage((String)map.get("image"));
			book.setDel_flag((int)map.get("del_flag"));

			bookList.add(book);
		}

	return bookList;
	}

	public Book selectOne(int bookId) throws DataAccessException {
		Map<String, Object> map = jdbc.queryForMap("SELECT * FROM books" + " WHERE book_id = ?", bookId);

		Book book = new Book();

		book.setBookName((String)map.get("book_name"));
		book.setDescription((String)map.get("description"));
		book.setPrice((int)map.get("price"));
		book.setImage((String)map.get("image"));
		book.setDel_flag((int)map.get("del_flag"));

		return book;
	}

	public int insertOne(Book book) throws DataAccessException {
		int rowNumber = jdbc.update("insert into books(book_name, description, price, image, del_flag)" +
													"values(?, ?, ?, ?, ?)",book.getBookName(),book.getDescription(),book.getPrice(),book.getImage(),book.getDel_flag());
		return rowNumber;
	}
}
