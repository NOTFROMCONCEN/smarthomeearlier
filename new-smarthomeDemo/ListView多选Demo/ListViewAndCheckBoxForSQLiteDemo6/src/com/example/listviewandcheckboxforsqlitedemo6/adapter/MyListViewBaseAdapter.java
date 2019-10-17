package com.example.listviewandcheckboxforsqlitedemo6.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.listviewandcheckboxforsqlitedemo6.R;
import com.example.listviewandcheckboxforsqlitedemo6.mysql.MyDataBaseHelper;

/**
 * @author Administrator import android.widget.TextView;
 * @year 2019
 * @Todo TODO �Զ���������
 * @package_name com.example.listviewandcheckboxforsqlitedemo6.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo6
 * @file_name MyListViewBaseAdapter.java
 */
public class MyListViewBaseAdapter extends BaseAdapter {
	List<Bean> list = new ArrayList<Bean>();// Bean���ݼ�
	Context mContext;// ������
	// �洢��ѡ״̬��Map�ϼ�
	Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

	// ���췽��
	public MyListViewBaseAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		this.mContext = context;
		// ������������������й�ѡ��Ϊfalse
		initCheck(false);
	}

	private void initCheck(boolean flag) {
		// TODO Auto-generated method stub
		// Map�ϼ�������List��һ����
		for (int i = 0; i < list.size(); i++) {
			System.out.println("����" + list.size() + "������");
			map.put(i, flag);
		}
	}

	// ��������
	public void setData(List<Bean> data) {
		// TODO Auto-generated method stub
		this.list = data;
	}

	// ɾ������
	public void removeData(int postion) {
		// TODO Auto-generated method stub
		// ���ݿ�
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(mContext, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Bean bean = list.get(postion);
		db.execSQL("delete from user where username = ?",
				new String[] { bean.getUser() });
		System.out.println("ѡ�е��û���" + bean.getUser() + "��ɾ��");
	}

	// �������ݿ�
	public void updataData(int postion) {
		// TODO Auto-generated method stub
		Bean bean = list.get(postion);
		System.out.println("ѡ�е��û���" + bean.getUser() + "�Ѹ���");
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list != null ? list.size() : 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public Map<Integer, Boolean> getMap() {
		return map;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(mContext).inflate(R.layout.item, null,
				false);
		TextView tv_user = (TextView) view.findViewById(R.id.tv_user);
		TextView tv_pass = (TextView) view.findViewById(R.id.tv_pass);
		CheckBox cb_1 = (CheckBox) view.findViewById(R.id.cb_1);
		Bean bean = list.get(arg0);
		tv_pass.setText(bean.getPass().toString());
		tv_user.setText(bean.getUser().toString());
		cb_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				map.put(arg0, isChecked);
			}
		});
		if (bean.getUser().equals("root")) {
			tv_user.setTextColor(Color.RED);
			tv_pass.setTextColor(Color.RED);
			cb_1.setVisibility(View.INVISIBLE);
		} else {
			tv_user.setTextColor(Color.BLACK);
			tv_pass.setTextColor(Color.BLACK);
		}
		if (map.get(arg0) == null) {
			map.put(arg0, false);
		}
		cb_1.setChecked(map.get(arg0));
		return view;
	}
}