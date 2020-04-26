package com.javaweb.dao.impl;

import java.util.List;
import com.javaweb.dao.DAO;
import com.javaweb.domain.User;
import com.javaweb.dao.UserDAO;


public class UserDAOJdbclmpl extends DAO<User> implements UserDAO{

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User get(String name) {
		
		String sql = "SELECT id, name, password FROM user WHERE name = ?";
		
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
