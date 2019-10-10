package com.example.demo.domain.repository.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BooksRepository {
	
	@Autowired 
	NamedParameterJdbcTemplate jdbcTemplate;
	
}
