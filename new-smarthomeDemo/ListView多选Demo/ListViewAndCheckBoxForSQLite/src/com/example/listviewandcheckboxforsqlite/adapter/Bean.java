package com.example.listviewandcheckboxforsqlite.adapter;

public class Bean {
	private String user;
	private String pass;

	// 构造方法
	public Bean(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}

	public String getTitle() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public void setTitle(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
}