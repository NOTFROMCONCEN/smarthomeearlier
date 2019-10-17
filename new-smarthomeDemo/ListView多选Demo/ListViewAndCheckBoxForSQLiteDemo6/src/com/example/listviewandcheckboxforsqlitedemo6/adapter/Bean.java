package com.example.listviewandcheckboxforsqlitedemo6.adapter;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO Êý¾Ý¼¯
 * @package_name com.example.listviewandcheckboxforsqlitedemo6.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo6
 * @file_name Bean.java
 */
public class Bean {
	String user;
	String pass;

	public Bean(String user, String pass) {
		// TODO Auto-generated constructor stub
		super();
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public void getUserandPass(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
}
