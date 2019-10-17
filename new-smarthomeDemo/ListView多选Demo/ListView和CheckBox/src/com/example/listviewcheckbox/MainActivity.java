package com.example.listviewcheckbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Color;
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

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	TextView tv_add;
	Button btn_detele;
	Button btn_select_all;
	ListAdapter adapter;
	ListView listview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 关闭title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void initView() {
		tv_add = (TextView) findViewById(R.id.tv_add);
		tv_add.setOnClickListener(this);
		btn_detele = (Button) findViewById(R.id.btn_detele);
		btn_detele.setOnClickListener(this);
		btn_select_all = (Button) findViewById(R.id.btn_select_all);
		btn_select_all.setOnClickListener(this);
		listview = (ListView) findViewById(R.id.listview);
	}

	private void initData() {
		// 默认显示的数据
		List<Bean> list = new ArrayList<Bean>();
		list.add(new Bean("张三"));
		list.add(new Bean("李四"));
		list.add(new Bean("王五"));
		adapter = new ListAdapter(this);
		adapter.setData(list);
		listview.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_detele:
			// 拿到所有数据
			Map<Integer, Boolean> isCheck_delete = adapter.getMap();
			// 获取到条目数量，map.size = list.size,所以
			int count = adapter.getCount();
			// 遍历
			for (int i = 0; i < count; i++) {
				// 删除有两个map和list都要删除 ,计算方式
				int position = i - (count - adapter.getCount());
				// 判断状态 true为删除
				if (isCheck_delete.get(i) != null && isCheck_delete.get(i)) {
					// listview删除数据
					isCheck_delete.remove(i);
					adapter.removeData(position);
				}
			}
			btn_select_all.setText("全选");
			btn_select_all.setTextColor(Color.WHITE);
			adapter.notifyDataSetChanged();
			break;
		case R.id.btn_select_all:
			// 全选——全不选
			Map<Integer, Boolean> isCheck = adapter.getMap();
			if (btn_select_all.getText().equals("全选")) {
				adapter.initCheck(true);
				// 通知刷新适配器
				adapter.notifyDataSetChanged();
				btn_select_all.setText("全不选");
				// btn_select_all.setTextColor(Color.YELLOW);
			} else if (btn_select_all.getText().equals("全不选")) {
				adapter.initCheck(false);
				// 通知刷新适配器
				adapter.notifyDataSetChanged();
				btn_select_all.setText("全选");
				// btn_select_all.setTextColor(Color.YELLOW);
			}
			break;
		case R.id.tv_add:
			adapter.addData(new Bean("刘桂林"));
			// 通知刷新适配器
			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

}
