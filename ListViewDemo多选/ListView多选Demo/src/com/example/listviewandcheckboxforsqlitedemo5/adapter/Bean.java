package com.example.listviewandcheckboxforsqlitedemo5.adapter;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 数据集
 * @package_name com.example.listviewandcheckboxforsqlitedemo5.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo5
 * @file_name Bean.java
 */
public class Bean {
	// 用户名、密码
	String user, pass;

	// 构造方法
	public Bean(String user, String pass) {
		// TODO Auto-generated constructor stub
		super();
		this.user = user;
		this.pass = pass;
	}

	// 得到（输出）用户名
	public String getUser() {
		return user;
	}

	// 得到（输出）密码
	public String getPass() {
		return pass;
	}

	// 设置用户名、密码
	public void getUserAmdPass(String user, String pass) {
		// TODO Auto-generated method stub
		this.user = user;
		this.pass = pass;
	}
}
