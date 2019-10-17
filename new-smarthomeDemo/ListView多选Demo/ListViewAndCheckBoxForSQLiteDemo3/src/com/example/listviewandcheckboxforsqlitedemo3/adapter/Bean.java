package com.example.listviewandcheckboxforsqlitedemo3.adapter;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 存储数据集
 * @package_name com.example.listviewandcheckboxforsqlitedemo3.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo3
 * @file_name Bean.java
 */
public class Bean {
	public String user;
	public String pass;

	// 构造方法
	public Bean(String user, String pass) {
		// TODO Auto-generated constructor stub
		super();
		this.user = user;
		this.pass = pass;
	}

	// 得到用户名
	public String getUser() {
		return user;
	}

	// 得到密码
	public String getPass() {
		return pass;
	}

	// ListViewadapter用到的设置用户名、密码
	public void setNumber(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
}
