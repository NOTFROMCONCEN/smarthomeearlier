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
		// �ر�title
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
		// Ĭ����ʾ������
		List<Bean> list = new ArrayList<Bean>();
		list.add(new Bean("����"));
		list.add(new Bean("����"));
		list.add(new Bean("����"));
		adapter = new ListAdapter(this);
		adapter.setData(list);
		listview.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_detele:
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
				}
			}
			btn_select_all.setText("ȫѡ");
			btn_select_all.setTextColor(Color.WHITE);
			adapter.notifyDataSetChanged();
			break;
		case R.id.btn_select_all:
			// ȫѡ����ȫ��ѡ
			Map<Integer, Boolean> isCheck = adapter.getMap();
			if (btn_select_all.getText().equals("ȫѡ")) {
				adapter.initCheck(true);
				// ֪ͨˢ��������
				adapter.notifyDataSetChanged();
				btn_select_all.setText("ȫ��ѡ");
				// btn_select_all.setTextColor(Color.YELLOW);
			} else if (btn_select_all.getText().equals("ȫ��ѡ")) {
				adapter.initCheck(false);
				// ֪ͨˢ��������
				adapter.notifyDataSetChanged();
				btn_select_all.setText("ȫѡ");
				// btn_select_all.setTextColor(Color.YELLOW);
			}
			break;
		case R.id.tv_add:
			adapter.addData(new Bean("������"));
			// ֪ͨˢ��������
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
