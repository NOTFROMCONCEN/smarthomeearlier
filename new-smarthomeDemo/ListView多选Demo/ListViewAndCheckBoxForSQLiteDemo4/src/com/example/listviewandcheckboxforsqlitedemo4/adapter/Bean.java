package com.example.listviewandcheckboxforsqlitedemo4.adapter;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO �洢���ݼ�
 * @package_name com.example.listviewandcheckboxforsqlitedemo4.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo4
 * @file_name Bean.java
 */
public class Bean {
	public String user, pass;

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
	public void setUserAndPass(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

}