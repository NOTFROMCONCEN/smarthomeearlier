package com.example.listviewandcheckboxforsqlitedemo3.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.listviewandcheckboxforsqlitedemo3.R;
import com.example.listviewandcheckboxforsqlitedemo3.mysql.MyDataBaseHelper;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 自定义Adapter for ListView控件
 * @package_name com.example.listviewandcheckboxforsqlitedemo3.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo3
 * @file_name MyListViewAdapter.java
 */
public class MyListViewAdapter extends BaseAdapter {
	// 数据集
	private List<Bean> list = new ArrayList<Bean>();
	// Context
	Context context;

	// Mep合集存储勾选状态
	HashMap<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();

	// 构造方法
	public MyListViewAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		this.context = context;
		// 默认全部取消选中
		initCheck(false);
	}

	// 存储勾选状态
	private void initCheck(boolean flag) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			// 默认全部取消选中
			isCheckMap.put(i, flag);
		}
	}

	// 设置参数
	public void setData(List<Bean> data) {
		// TODO Auto-generated method stub
		this.list = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// 如果数值为null就返回一个0
		return list != null ? list.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public static class ViewHolder {
		public TextView tv_user, tv_pass;
		public CheckBox cb_1;
	}

	public Map<Integer, Boolean> getMap() {
		return isCheckMap;
	}

	public void removeData(int postion) {
		// TODO Auto-generated method stub
		// 数据库
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// 得到所有的对象
		Bean bean = list.get(postion);
		db.execSQL("delete from user where username = ?", new String[] { bean
				.getUser().toString() });
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(context).inflate(R.layout.item, null,
				false);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.cb_1 = (CheckBox) view.findViewById(R.id.cb_1);
		viewHolder.tv_pass = (TextView) view.findViewById(R.id.tv_pass);
		viewHolder.tv_user = (TextView) view.findViewById(R.id.tv_user);
		view.setTag(viewHolder);
		viewHolder = (ViewHolder) view.getTag();
		Bean bean = list.get(position);
		viewHolder.tv_pass.setText(bean.getPass().toString());
		viewHolder.tv_user.setText(bean.getUser().toString());
		viewHolder.cb_1
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						isCheckMap.put(position, isChecked);
					}
				});
		if (isCheckMap.get(position) == null) {
			isCheckMap.put(position, false);
		}
		viewHolder.cb_1.setChecked(isCheckMap.get(position));
		return null;
	}
}