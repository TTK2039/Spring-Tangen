package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

@Repository
public class PgUserDao implements UserDao {

	private static final String SQL_SELECT_ID_PASS= "SELECT * FROM users where login_id = :id AND password = :pass";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public User loginCheck(User user) {
		String sql = SQL_SELECT_ID_PASS;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", user.getLoginId());
		param.addValue("pass", user.getPassword());

		List<User> rs = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));

		return rs.isEmpty() ? null : rs.get(0);
	}
	//    
	//    @Override
	//    public List<Product> findAll() {
	//    	String sql = SELECT_ALL;
	//    	       
	//        List<Product> resultList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
	//
	//        return resultList;
	//    }
	//    
	//    @Override
	//    public List<Product> findByName(String name) {
	//        String sql = SELECT_BY_PRODUCT_NAME;
	//
	//        MapSqlParameterSource param = new MapSqlParameterSource();
	//        param.addValue("name", name);
	//
	//        return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
	//    }
	//
	//    @Override
	//    public Product findById(int id) {
	//    	String sql = SELECT_BY_ID;
	//    	
	//        MapSqlParameterSource param = new MapSqlParameterSource();
	//        param.addValue("id", id);
	//
	//        List<Product> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
	//
	//        return resultList.isEmpty() ? null : resultList.get(0);
	//    }
	//    
	//    @Override
	//    public void insert(Product pd) {
	//    	String sql = INSERT;
	//    	
	//        MapSqlParameterSource param = new MapSqlParameterSource();
	//        param.addValue("price", pd.getPrice());
	//        param.addValue("name", pd.getProduct_name());
	//
	//        jdbcTemplate.update(sql, param);
	//    }
	//    
	//    @Override
	//    public List<Product> findByPrice(int price) {
	//    	String sql = SELECT_BY_PRICE;
	//    	
	//        MapSqlParameterSource param = new MapSqlParameterSource();
	//        param.addValue("price", price);
	//
	//        return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
	//    }
	//    
	//    @Override
	//    public List<Product> findByNamePrice(String name, int price) {
	//    	String sql = SELECT_BY_NAME_AND_PRICE;
	//    	
	//        MapSqlParameterSource param = new MapSqlParameterSource();
	//        param.addValue("name", name);
	//        param.addValue("price", price);
	//
	//        return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Product>(Product.class));
	//    }

}

