package com.example.listonclick;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SetStringActivity extends Activity {
	private ListView listView2;
	private ArrayAdapter<String> mAdapter;
	private List<String> list = new ArrayList<String>();
	private String string;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setstring);
		listView2 = (ListView) findViewById(R.id.listView2);
		list.add("����");
		list.add("�Ϻ�");
		list.add("����");
		list.add("����");
		list.add("ɽ��");
		list.add("����");
		list.add("�ӱ�");
		list.add("����");
		list.add("ɽ��");
		list.add("����");
		list.add("����");
		list.add("����");
		System.out.println(list);
		// listView�����¼�
		listView2.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				DiyToast.showToast(getApplicationContext(), "����");
				return false;
			}
		});
		listView2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ��ȡ�û���(position)
				for (int i = 0; i < 99; i++) {
					if (position == i) {
						System.out.println(list.get(i));
						string = list.get(i).toString();
					}
				}
				// if (position == 0) {
				// System.out.println(list.get(0));
				// string = list.get(0).toString();
				// }
				// if (position == 1) {
				// System.out.println(list.get(1));
				// string = list.get(1).toString();
				// }
				// if (position == 2) {
				// System.out.println(list.get(2));
				// string = list.get(2).toString();
				// }
				// if (position == 3) {
				// System.out.println(list.get(3));
				// string = list.get(3).toString();
				// }
				// if (position == 4) {
				// System.out.println(list.get(4));
				// string = list.get(4).toString();
				// }
				// if (position == 5) {
				// System.out.println(list.get(5));
				// string = list.get(5).toString();
				// }
				// 0 ���
				// 1 ��ߵ�һ��
				// 2 ��ߵڶ���
				// �Դ����ƣ�
				DiyToast.showToast(getApplicationContext(), "��ǰѡ����û�����"
						+ position + string);
			}
		});
		// listView��������
		listView2.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				DiyToast.showToast(getApplicationContext(), "����");
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});
		// mAdapter = new ArrayAdapter<String>(getApplicationContext(),
		// android.R.layout.simple_list_item_single_choice, list);
		// listView2.setAdapter(mAdapter);
		// ��ӵ�ѡ����
		// listView2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// ��Ӷ�ѡ����
		mAdapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_multiple_choice, list);
		listView2.setAdapter(mAdapter);
		listView2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}
}
