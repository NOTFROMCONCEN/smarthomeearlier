package com.example.listviewandcheckboxforsqlitedemo5.adapter;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO ���ݼ�
 * @package_name com.example.listviewandcheckboxforsqlitedemo5.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo5
 * @file_name Bean.java
 */
public class Bean {
	// �û���������
	String user, pass;

	// ���췽��
	public Bean(String user, String pass) {
		// TODO Auto-generated constructor stub
		super();
		this.user = user;
		this.pass = pass;
	}

	// �õ���������û���
	public String getUser() {
		return user;
	}

	// �õ������������
	public String getPass() {
		return pass;
	}

	// �����û���������
	public void getUserAmdPass(String user, String pass) {
		// TODO Auto-generated method stub
		this.user = user;
		this.pass = pass;
	}
}
