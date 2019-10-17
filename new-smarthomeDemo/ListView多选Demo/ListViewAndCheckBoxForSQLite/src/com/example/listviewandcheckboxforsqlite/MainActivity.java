package com.example.listviewandcheckboxforsqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listviewandcheckboxforsqlite.adapter.Bean;
import com.example.listviewandcheckboxforsqlite.adapter.ListAdapter;
import com.example.listviewandcheckboxforsqlite.mysql.MyDataBaseHelper;
import com.example.listviewandcheckboxforsqlite.toast.DiyToast;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO �����棨ListView��ʾ��ɾ�������ӡ�ȫѡȡ��ȫѡ��
 * @package_name com.example.listviewandcheckbox
 * @project_name ListViewAndCheckBox
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity {
	private ListView lv_1;// ListView
	private TextView tv_add;// ���
	private Button btn_select_all, btn_delete;// ȫѡ��ɾ����ť
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		initView();
		// setUser();
		initData();
		// ɾ��
		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// �õ���������
				Map<Integer, Boolean> isCheck_delete = adapter.getMap();
				// ��ȡ����Ŀ������map.size = list.size,����
				int count = adapter.getCount();
				// ����
				for (int i = 0; i < count; i++) {
					// ɾ��������map��list��Ҫɾ�� ,���㷽ʽ
					int position = i - (count - adapter.getCount());
					// �ж�״̬ trueΪɾ��
					if (isCheck_delete.get(i) != null && isCheck_delete.get(i)) {
						// listviewɾ������
						isCheck_delete.remove(i);
						adapter.removeData(position);
						initData();
					}
				}
			}
		});
	}

	private void initData() {
		// Ĭ����ʾ������
		Cursor cursor = db.rawQuery("select * from user", null);
		List<Bean> list = new ArrayList<Bean>();
		if (cursor.getCount() != 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				// list.add(Bean());
				list.add(new Bean(cursor.getString(cursor
						.getColumnIndex("username")), cursor.getString(cursor
						.getColumnIndex("passward"))));
			}
		}
		adapter = new ListAdapter(this);
		adapter.setData(list);
		lv_1.setAdapter(adapter);
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_delete = (Button) findViewById(R.id.btn_detele);
		btn_select_all = (Button) findViewById(R.id.btn_select_all);
		tv_add = (TextView) findViewById(R.id.tv_add);
		lv_1 = (ListView) findViewById(R.id.listview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}