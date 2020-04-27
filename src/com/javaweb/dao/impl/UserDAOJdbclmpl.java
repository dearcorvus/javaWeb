package com.javaweb.dao.impl;

import java.util.List;
import com.javaweb.dao.DAO;
import com.javaweb.domain.User;
import com.javaweb.dao.UserDAO;


public class UserDAOJdbclmpl extends DAO<User> implements UserDAO{

	@Override
	public List<User> getAll() {
		String sql = "SELECT id, user_login, user_pass  FROM cmf_user";
		return getForList(sql);
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User get(String name) {
		
		String sql = "SELECT id, user_login, user_pass FROM cmf_user WHERE user_login = ?";
		
		return get(sql, name);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getCountWithName(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
