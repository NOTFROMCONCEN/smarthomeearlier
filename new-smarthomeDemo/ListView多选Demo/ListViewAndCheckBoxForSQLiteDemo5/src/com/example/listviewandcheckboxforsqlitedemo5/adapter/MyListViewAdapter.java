package com.example.listviewandcheckboxforsqlitedemo5.adapter;

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

import com.example.listviewandcheckboxforsqlitedemo5.MainActivity;
import com.example.listviewandcheckboxforsqlitedemo5.R;
import com.example.listviewandcheckboxforsqlitedemo5.mysql.MyDataBaseHelper;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 自定义适配器
 * @package_name com.example.listviewandcheckboxforsqlitedemo5.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo5
 * @file_name MyListViewAdapter.java
 */
public class MyListViewAdapter extends BaseAdapter {
	// 数据集
	private List<Bean> list = new ArrayList<Bean>();
	// 上下文
	private Context mContext;
	// 存储勾选状态的Map合集
	private Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

	// 构造方法
	public MyListViewAdapter(Context mContext) {
		// TODO Auto-generated constructor stub
		super();
		this.mContext = mContext;
		// 设置首次启动APP（默认）情况下不选中
		initCheck(false);
	}

	// 初始化Map合集
	private void initCheck(boolean flag) {
		// TODO Auto-generated method stub
		// Map合集的数量和list是一样的
		for (int i = 0; i < list.size(); i++) {
			map.put(i, false);
		}
	}

	// 设置数据
	public void setData(List<Bean> data) {
		// TODO Auto-generated method stub
		this.list = data;
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

	// 删除数据
	public void remove(int postion) {
		// TODO Auto-generated method stub
		// 数据库
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(mContext, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Bean bean = list.get(postion);
		db.execSQL("delete from user where username = ?",
				new String[] { bean.getUser() });
	}

	public void updata(int postion) {
		Bean bean = list.get(postion);
		MainActivity.get_user = bean.getUser();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(mContext).inflate(R.layout.item, null,
				false);
		TextView tv_user, tv_pass;
		CheckBox cb_1;
		tv_pass = (TextView) view.findViewById(R.id.tv_pass);
		tv_user = (TextView) view.findViewById(R.id.tv_user);
		cb_1 = (CheckBox) view.findViewById(R.id.cb_1);
		Bean bean = list.get(position);
		tv_pass.setText(bean.getPass().toString());
		tv_user.setText(bean.getUser().toString());
		cb_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				map.put(position, isChecked);
			}
		});
		if (bean.getUser().equals("root")) {
			tv_user.setTextColor(Color.RED);
			tv_pass.setTextColor(Color.RED);
			cb_1.setVisibility(View.INVISIBLE);
		}
		if (map.get(position) == null) {
			map.put(position, false);
		}
		cb_1.setChecked(map.get(position));
		return view;
	}
}