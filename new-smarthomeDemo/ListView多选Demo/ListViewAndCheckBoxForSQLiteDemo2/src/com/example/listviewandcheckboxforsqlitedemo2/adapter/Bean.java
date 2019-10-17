package com.example.listviewandcheckboxforsqlitedemo2.adapter;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO Êý¾Ý¼¯
 * @package_name com.example.listviewandcheckboxforsqlitedemo2.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo2
 * @file_name Bean.java
 */
public class Bean {
	public String user;
	public String pass;

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

	public void setTitle(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
}
