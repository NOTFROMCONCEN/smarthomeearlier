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
		list.add("北京");
		list.add("上海");
		list.add("广州");
		list.add("深圳");
		list.add("山东");
		list.add("河南");
		list.add("河北");
		list.add("陕西");
		list.add("山西");
		list.add("安徽");
		list.add("湖南");
		list.add("湖北");
		System.out.println(list);
		// listView长按事件
		listView2.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				DiyToast.showToast(getApplicationContext(), "长按");
				return false;
			}
		});
		listView2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 提取用户名(position)
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
				// 0 序号
				// 1 左边第一行
				// 2 左边第二行
				// 以此类推？
				DiyToast.showToast(getApplicationContext(), "当前选择的用户名："
						+ position + string);
			}
		});
		// listView产生滑动
		listView2.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				DiyToast.showToast(getApplicationContext(), "滑动");
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
		// 添加单选操作
		// listView2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// 添加多选操作
		mAdapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_multiple_choice, list);
		listView2.setAdapter(mAdapter);
		listView2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}
}
