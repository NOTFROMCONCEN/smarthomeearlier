package com.example.listonclick;

import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * @�ļ�����ChoseActivity.java
 * @������ѡ�����ת��Ӧ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-3
 */
public class ChoseActivity extends Activity implements OnClickListener {
	private Button btn_intent_sqlite;// ���ݿ�
	private Button btn_intent_setstring;// �趨ֵ
	private Button btn_intent_dialog_setstring;// �趨ֵDialog
	private Button btn_intent_dialog_sql;// ���ݿ�Dialog
	private Button btn_updata_sqlite;// �������ݿ�
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose);
		btn_intent_setstring = (Button) findViewById(R.id.btn_intent_setstring);
		btn_intent_sqlite = (Button) findViewById(R.id.btn_intent_sqlite);
		btn_intent_dialog_sql = (Button) findViewById(R.id.btn_intent_dialog_setsqlite);
		btn_intent_dialog_setstring = (Button) findViewById(R.id.btn_intent_dialog_setstring);
		btn_updata_sqlite = (Button) findViewById(R.id.btn_updata_sqlite);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ���ð�ť����¼�
		btn_intent_setstring.setOnClickListener(this);
		btn_intent_sqlite.setOnClickListener(this);
		btn_intent_dialog_setstring.setOnClickListener(this);
		btn_intent_dialog_sql.setOnClickListener(this);
		btn_updata_sqlite.setOnClickListener(this);
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @ʱ �䣺����7:52:34
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_intent_setstring:
			Intent intent = new Intent(ChoseActivity.this,
					SetStringActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_intent_sqlite:
			Intent intent1 = new Intent(ChoseActivity.this, MainActivity.class);
			startActivity(intent1);
			break;
		case R.id.btn_intent_dialog_setsqlite:
			Intent intent11 = new Intent(ChoseActivity.this,
					DialogSQLActivity.class);
			startActivity(intent11);
			break;
		case R.id.btn_intent_dialog_setstring:
			// Intent intent111 = new Intent(ChoseActivity.this,
			// MainActivity.class);
			// startActivity(intent111);
			break;
		case R.id.btn_updata_sqlite:
			for (int i = 0; i < 500; i++) {
				System.err.println(i);
				db.execSQL("insert into user (username,passward)values(?,?)",
						new String[] { "1" + String.valueOf(i), "1" });
				db.execSQL("insert into user (username,passward)values(?,?)",
						new String[] { "2" + String.valueOf(i), "2" });
				db.execSQL("insert into user (username,passward)values(?,?)",
						new String[] { "3" + String.valueOf(i), "3" });
				db.execSQL("insert into user (username,passward)values(?,?)",
						new String[] { "4" + String.valueOf(i), "4" });
				db.execSQL("insert into user (username,passward)values(?,?)",
						new String[] { "5" + String.valueOf(i), "5" });
				db.execSQL("insert into user (username,passward)values(?,?)",
						new String[] { "6" + String.valueOf(i), "6" });
				db.execSQL("insert into user (username,passward)values(?,?)",
						new String[] { "7" + String.valueOf(i), "7" });
				db.execSQL("insert into user (username,passward)values(?,?)",
						new String[] { "8" + String.valueOf(i), "8" });
				db.execSQL("insert into user (username,passward)values(?,?)",
						new String[] { "9" + String.valueOf(i), "9" });
				db.execSQL("insert into user (username,passward)values(?,?)",
						new String[] { "10" + String.valueOf(i), "10" });
			}
			break;
		default:
			break;
		}
	}

}
