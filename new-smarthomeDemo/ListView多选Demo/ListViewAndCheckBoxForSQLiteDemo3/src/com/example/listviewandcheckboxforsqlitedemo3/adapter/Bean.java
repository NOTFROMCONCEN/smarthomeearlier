package com.example.listviewandcheckboxforsqlitedemo3.adapter;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO �洢���ݼ�
 * @package_name com.example.listviewandcheckboxforsqlitedemo3.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo3
 * @file_name Bean.java
 */
public class Bean {
	public String user;
	public String pass;

	// ���췽��
	public Bean(String user, String pass) {
		// TODO Auto-generated constructor stub
		super();
		this.user = user;
		this.pass = pass;
	}

	// �õ��û���
	public String getUser() {
		return user;
	}

	// �õ�����
	public String getPass() {
		return pass;
	}

	// ListViewadapter�õ��������û���������
	public void setNumber(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
}
