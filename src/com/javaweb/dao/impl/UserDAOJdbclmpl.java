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
		String sql = "UPDATE cmf_user SET user_login = ? ,user_pass = ? WHERE id = ?";
		
		update(sql, user.getUser_login(),user.getUser_pass(),user.getId()); 
		
	}
	@Override
	public void updates(User user) {
		String sql = "UPDATE cmf_user SET user_nickname = ? ,sex = ? ,birthday = ? ,user_url = ? ,signature = ? WHERE id = ?";

		update(sql, user.getUser_nickname(), user.getSex(), user.getBirthday(), user.getUser_url(), user.getSignature(), user.getId());
		
	}
	
	@Override
	public long getCountWithName(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User get(Integer id) {
		
		String sql = "SELECT id, user_login, user_pass, user_nickname, sex, birthday,  user_url, signature   FROM cmf_user WHERE id = ?";
		
		return get(sql, id);
	}
	
}
