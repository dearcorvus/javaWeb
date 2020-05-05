package com.javaweb.domain;

public class User {
	
	private Integer id;
	private String user_login;
	private String user_pass;
	private String user_nickname;
	private String sex;
	private long birthday;
	private String user_url;
	private String signature;
	
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

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getUser_url() {
		return user_url;
	}

	public void setUser_url(String user_url) {
		this.user_url = user_url;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public User(String user_login, String user_pass) {
		super();
		this.user_login = user_login;
		this.user_pass = user_pass;
	}

	public User(String user_nickname, String sex, long birthday, String user_url, String signature) {
		super();

		this.user_nickname = user_nickname;
		this.sex = sex;
		this.birthday = birthday;
		this.user_url = user_url;
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_login=" + user_login + ", user_pass=" + user_pass + "]";
	}
}
