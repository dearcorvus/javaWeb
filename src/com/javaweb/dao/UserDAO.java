package com.javaweb.dao;

import java.util.List;

import com.javaweb.domain.User;

public interface UserDAO {

	public List<User> getAll();
	
	public void save(User user);
		
	public User get(String name);
	
	public void delete(Integer id);
	
	public void update(User user);
	
	public long getCountWithName(String name);
}
