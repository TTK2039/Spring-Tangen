package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;

@Repository
public class PgProductDao implements ProductDao {

	private static final String WHERE_ID = " WHERE p.id = :id";
	private static final String SQL_SELECT_ALL_JOIN = """
														SELECT
														p.id id
														, p.product_id product_id
														, p.name name
														, p.price price
														, p.description description
														,c.name category_name
														, p.created_at
														FROM products p 
														JOIN categories c 
														ON p.category_id = c.id""";

															
															
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Product> findAll() {
		String sql = SQL_SELECT_ALL_JOIN;

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));

	}
	
	@Override
	public Product findById(int id) {
		String sql = SQL_SELECT_ALL_JOIN + WHERE_ID;
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
	
		List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
        return resultList.isEmpty() ? null : resultList.get(0);
	}
}

