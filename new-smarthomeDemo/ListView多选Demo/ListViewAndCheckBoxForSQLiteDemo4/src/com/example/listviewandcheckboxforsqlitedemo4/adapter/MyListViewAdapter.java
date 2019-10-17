package com.example.listviewandcheckboxforsqlitedemo4.adapter;

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

import com.example.listviewandcheckboxforsqlitedemo4.R;
import com.example.listviewandcheckboxforsqlitedemo4.mysql.MydataBaseHelper;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 自定义adapter适配器
 * @package_name com.example.listviewandcheckboxforsqlitedemo4.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo4
 * @file_name MyListViewAdapter.java
 */
public class MyListViewAdapter extends BaseAdapter {
	// 数据集
	private List<Bean> list = new ArrayList<Bean>();
	// 上下文
	private Context mContext;
	// 存储勾选框状态的Map集合
	private Map<Integer, Boolean> isCheck = new HashMap<Integer, Boolean>();

	// 构造方法
	public MyListViewAdapter(Context mContext) {
		// TODO Auto-generated constructor stub
		super();
		this.mContext = mContext;
		// 默认全部不选中
		initCheck(false);
	}

	// 初始化Map合集
	private void initCheck(boolean flag) {
		// map合集的数量和list的数量是一致的
		for (int i = 0; i < list.size(); i++) {
			// 设置默认显示状态
			isCheck.put(i, flag);
		}
	}

	// 设置数据
	public void setData(List<Bean> data) {
		// TODO Auto-generated method stub
		this.list = data;
	}

	public void addData(Bean bean) {
		// TODO Auto-generated method stub
		list.add(0, bean);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		public TextView tv_user;
		public TextView tv_pass;
		public CheckBox cb_1;
	}

	public Map<Integer, Boolean> getMap() {
		return isCheck;
	}

	// 删除数据
	public void removeData(int postion) {
		// 数据库
		MydataBaseHelper dbHelper = new MydataBaseHelper(mContext, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// 得到所有对象
		Bean bean = list.get(postion);
		System.out.println(bean.getUser().toString());
		db.execSQL("delete from user where username = ?", new String[] { bean
				.getUser().toString() });
	}

	// getView绑定、设置等
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(mContext).inflate(R.layout.item,
				parent, false);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.tv_pass = (TextView) view.findViewById(R.id.tv_pass);
		viewHolder.tv_user = (TextView) view.findViewById(R.id.tv_user);
		viewHolder.cb_1 = (CheckBox) view.findViewById(R.id.cb_1);
		view.setTag(viewHolder);
		viewHolder = (ViewHolder) view.getTag();
		Bean bean = list.get(position);
		viewHolder.tv_user.setText(bean.getUser().toString());
		viewHolder.tv_pass.setText(bean.getPass().toString());
		viewHolder.cb_1
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						isCheck.put(position, isChecked);
					}
				});
		if (isCheck.get(position) == null) {
			isCheck.put(position, false);
		}
		viewHolder.cb_1.setChecked(isCheck.get(position));
		return view;
	}
}