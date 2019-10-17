package com.example.listviewandcheckbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listviewandcheckbox.ListAdapter.ViewHolder;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 主界面（ListView显示、删除、增加、全选取消全选）
 * @package_name com.example.listviewandcheckbox
 * @project_name ListViewAndCheckBox
 * @file_name MainActivity.java
 */

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private ListView lv_1;// ListView
	private TextView tv_add;// 添加
	private Button btn_select_all, btn_delete;// 全选、删除按钮
	private ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_delete = (Button) findViewById(R.id.btn_detele);
		btn_delete.setOnClickListener(this);
		btn_select_all = (Button) findViewById(R.id.btn_select_all);
		btn_select_all.setOnClickListener(this);
		tv_add = (TextView) findViewById(R.id.tv_add);
		tv_add.setOnClickListener(this);
		lv_1 = (ListView) findViewById(R.id.listview);
	}

	private void initData() {
		// TODO Auto-generated method stub
		// 默认显示数据
		List<Bean> list = new ArrayList<Bean>();
		adapter = new ListAdapter(this);
		adapter.setData(list);
		lv_1.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_detele:
			// 删除
			// 得到数据
			Map<Integer, Boolean> isCheck_delete = adapter.getMap();
			// 得到条目数量，map.size = list.size，所以：
			int count = adapter.getCount();
			// 查找所有（遍历）
			for (int i = 0; i < count; i++) {
				int postion = i - (count - adapter.getCount());
				// 判断状态是不是true
				if (isCheck_delete.get(i) != null && isCheck_delete.get(i)) {
					// ListView删除数据
					isCheck_delete.remove(0);
					adapter.removeData(postion);
				}
			}
			adapter.notifyDataSetChanged();
			break;
		case R.id.btn_select_all:
			// 选择全部

			break;

		default:
			break;
		}
	}
}