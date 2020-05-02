package com.javaweb.domain;

public class User {
	
	private Integer id;
	private String user_login;
	private String user_pass;
	
	public User() {
		
	}	
	



	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getUser_login() {
		return user_login;
	}




	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}




	public String getUser_pass() {
		return user_pass;
	}




	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}




	public User(String user_login, String user_pass) {
		super();
		this.user_login = user_login;
		this.user_pass = user_pass;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", user_login=" + user_login + ", user_pass=" + user_pass + "]";
	}
}
