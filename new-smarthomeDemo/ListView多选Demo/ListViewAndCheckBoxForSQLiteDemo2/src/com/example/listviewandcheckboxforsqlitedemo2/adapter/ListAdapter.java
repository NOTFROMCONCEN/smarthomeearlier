package com.example.listviewandcheckboxforsqlitedemo2.adapter;

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

import com.example.listviewandcheckboxforsqlitedemo2.R;
import com.example.listviewandcheckboxforsqlitedemo2.mysql.MydataBaseHelper;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO �Զ���������
 * @package_name com.example.listviewandcheckboxforsqlitedemo2.adapter
 * @project_name ListViewAndCheckBoxForSQLiteDemo2
 * @file_name ListAdapter.java
 */
public class ListAdapter extends BaseAdapter {
	// ���ݼ�
	private List<Bean> list = new ArrayList<Bean>();
	// Context
	Context mContext;
	// Map�ϼ��洢��ѡ״̬
	Map<Integer, Boolean> isCheck = new HashMap<Integer, Boolean>();

	// ���췽��
	public ListAdapter(Context mContext) {
		// TODO Auto-generated constructor stub
		super();
		this.mContext = mContext;
		// Ĭ��ȫ����ѡ��
		initCheck(false);
	}

	// ��ʼ��map�ϼ�
	private void initCheck(boolean flag) {
		// map�ϼ���������list��һ�µ�
		for (int i = 0; i < list.size(); i++) {
			// ����Ĭ�ϵ���ʾ
			isCheck.put(i, flag);
		}
	}

	// ��������
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
		public TextView tv_user, tv_pass;
		public CheckBox cb_check;
	}

	public Map<Integer, Boolean> getMap() {
		return isCheck;
	}

	public void removeData(int postion) {
		// TODO Auto-generated method stub
		// ���ݿ�
		MydataBaseHelper dbHelper = new MydataBaseHelper(mContext, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// �õ����ж���
		Bean bean = list.get(postion);
		db.execSQL("delete from user where username = ?", new String[] { bean
				.getUser().toString() });
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(mContext).inflate(R.layout.item, null,
				false);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.tv_user = (TextView) view.findViewById(R.id.tv_user);
		viewHolder.tv_pass = (TextView) view.findViewById(R.id.tv_pass);
		viewHolder.cb_check = (CheckBox) view.findViewById(R.id.check_Box);
		view.setTag(viewHolder);
		viewHolder = (ViewHolder) view.getTag();
		Bean bean = list.get(position);
		viewHolder.tv_user.setText(bean.getUser().toString());
		viewHolder.tv_pass.setText(bean.getPass().toString());
		viewHolder.cb_check
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
		viewHolder.cb_check.setChecked(isCheck.get(position));
		return view;
	}
}