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

		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM books where del_flag=0");
		List<Book> bookList = new ArrayList<>();

		for(Map<String, Object> map:getList) {

			Book book = new Book();
			book.setBookId((int)map.get("book_Id"));
			book.setBookName((String)map.get("book_name"));
			book.setDescription((String)map.get("description"));
			book.setPrice((int)map.get("price"));
			book.setImage((String)map.get("image"));
			book.setCustomerCode((String)map.get("customer_code"));
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
		book.setCustomerCode((String)map.get("customer_code"));
		book.setDel_flag((int)map.get("del_flag"));

		return book;
	}

	public int insertOne(Book book) throws DataAccessException {
		int rowNumber = jdbc.update("insert into books(book_name, description, price, image, customer_code, del_flag)" +
													"values(?, ?, ?, ?, ?, ?)",book.getBookName(),book.getDescription(),book.getPrice(),book.getImage(), book.getCustomerCode(), book.getDel_flag());
		return rowNumber;
	}

	public int UpdateOne(Book book) throws DataAccessException {
		int rowNumber = jdbc.update("update books set book_name=?, description=?, price=? where book_id=?", book.getBookName(), book.getDescription(), book.getPrice(), book.getBookId());
		return rowNumber;
	}

	public int DeteleOne(Book book) throws DataAccessException {
		int rowNumber = jdbc.update("update books set del_flag=1 where book_id=?", book.getBookId());
		return rowNumber;
	}

	public List<Book> selectExhibitedBookList(String customerCode) throws DataAccessException {
		List<Map<String, Object>> getExhibitedBookList = jdbc.queryForList("SELECT * FROM books" + " WHERE customer_code = ? and del_flag=0", customerCode);
		List<Book> exhibitedbookList = new ArrayList<>();

		for(Map<String, Object> map:getExhibitedBookList) {

			Book book = new Book();
			book.setBookId((int)map.get("book_Id"));
			book.setBookName((String)map.get("book_name"));
			book.setDescription((String)map.get("description"));
			book.setPrice((int)map.get("price"));
			book.setImage((String)map.get("image"));
			book.setCustomerCode((String)map.get("customer_code"));
			book.setDel_flag((int)map.get("del_flag"));
			System.out.println(book);

			exhibitedbookList.add(book);
		}

	return exhibitedbookList;
	}
}
