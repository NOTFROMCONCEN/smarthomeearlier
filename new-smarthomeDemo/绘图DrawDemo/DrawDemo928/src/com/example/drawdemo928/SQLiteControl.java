package com.example.drawdemo928;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * @�ļ�����SQLiteControl.java
 * @���������ݿ���ɾ�Ĳ�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-28
 */
public class SQLiteControl {
	public static boolean login_state = false;
	public static boolean reg_state = false;
	public static boolean updata_state = false;

	/*
	 * @��������UpdataPass()
	 * 
	 * @�� �ܣ��޸����루�������ݿ⣩
	 * 
	 * @�� ����username,newpass,oldpass
	 * 
	 * @ʱ �䣺����3:22:23
	 */
	public static void UpdataPass(Context context, String username,
			String oldpass, String newpass) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (username.isEmpty()) {// �û���Ϊ��
			DiyToast.showTaost(context, "�������û���");
		} else if (oldpass.isEmpty()) {
			DiyToast.showTaost(context, "����������");
		} else if (newpass.isEmpty()) {
			DiyToast.showTaost(context, "������ȷ������");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? ",
					new String[] { username });
			if (cursor.moveToNext()) {
				if (cursor.getString(cursor.getColumnIndex("passward"))
						.toString().equals(oldpass)) {
					if (oldpass.equals(newpass)) {
						DiyToast.showTaost(context, "�¾����벻��һ��");
						updata_state = false;
					} else {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { newpass, username });
						DiyToast.showTaost(context, "�޸ĳɹ�");
						updata_state = true;
					}
				} else {
					DiyToast.showTaost(context, "���������");
					updata_state = false;
				}
			} else {
				DiyToast.showTaost(context, "�û���������");
				updata_state = false;
			}
		}
	}

	/*
	 * @��������setSQL()
	 * 
	 * @�� �ܣ�ע�ᣨ�������ݿ⣩
	 * 
	 * @�� ����username,passward
	 * 
	 * @ʱ �䣺����3:22:23
	 */
	public static void setSQL(Context context, String username,
			String passward, String repass) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (username.isEmpty()) {// �û���Ϊ��
			DiyToast.showTaost(context, "�������û���");
		} else if (passward.isEmpty()) {
			DiyToast.showTaost(context, "����������");
		} else if (repass.isEmpty()) {
			DiyToast.showTaost(context, "������ȷ������");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? ",
					new String[] { username });
			if (cursor.moveToNext()) {
				DiyToast.showTaost(context, "�û����Ѵ���");
				reg_state = false;
			} else {
				if (passward.equals(repass)) {
					db.execSQL(
							"insert into user (username,passward)values(?,?)",
							new String[] { username, passward });
					DiyToast.showTaost(context, "ע��ɹ�");
					reg_state = true;
				} else {
					DiyToast.showTaost(context, "�����������벻һ��");
					reg_state = false;
				}
			}
		}
	}

	/*
	 * @��������SeleSQL()
	 * 
	 * @�� �ܣ���¼(����û��������룬ƥ�����ݿ�)
	 * 
	 * @�� ����username,passward,context
	 * 
	 * @ʱ �䣺����3:05:33
	 */
	public static void SeleSQL(Context context, String username, String passward) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (username.isEmpty()) {// �û���Ϊ��
			DiyToast.showTaost(context, "�������û���");
		} else if (passward.isEmpty()) {
			DiyToast.showTaost(context, "����������");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward  =?",
					new String[] { username, passward });
			if (cursor.moveToNext()) {
				login_state = true;
			} else {
				login_state = false;
				DiyToast.showTaost(context, "�û����������������");
			}
		}
	}
}
