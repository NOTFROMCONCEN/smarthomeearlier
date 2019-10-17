package com.example.guosaifdemo926;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LinkActivity extends Fragment {
	private ListView lv_1, lv_2, lv_3, lv_4;
	private ArrayAdapter<String> mAdapter;
	private List<String> list1 = new ArrayList<String>();
	private List<String> list2 = new ArrayList<String>();
	private List<String> list3 = new ArrayList<String>();
	private List<String> list4 = new ArrayList<String>();
	private String lv_1_state, lv_2_state, lv_3_state, lv_4_state;
	private Button btn_add;
	private EditText et_big_small_number, et_mode_name;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_linke, container, false);
		lv_1 = (ListView) view.findViewById(R.id.lv_1);
		lv_2 = (ListView) view.findViewById(R.id.lv_2);
		lv_3 = (ListView) view.findViewById(R.id.lv_3);
		lv_4 = (ListView) view.findViewById(R.id.lv_4);
		et_big_small_number = (EditText) view
				.findViewById(R.id.et_big_small_number);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		et_mode_name = (EditText) view.findViewById(R.id.et_mode_name);
		btn_add = (Button) view.findViewById(R.id.btn_add);
		// 1
		list1.add("温度");
		list1.add("湿度");
		list1.add("光照");
		list1.add("烟雾");
		list1.add("燃气");
		list1.add("气压");
		// 2
		list2.add(">");
		list2.add("<");
		list2.add("=");
		// 3
		list3.add("空调");
		list3.add("DVD");
		list3.add("电视");
		list3.add("射灯全开");
		list3.add("射灯全关");
		list3.add("报警灯开");

		read();

		// 添加单选操作s1
		mAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_single_choice, list1);
		lv_1.setAdapter(mAdapter);
		lv_1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// 添加单选操作s2
		mAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_single_choice, list2);
		lv_2.setAdapter(mAdapter);
		lv_2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// 添加单选操作s3
		mAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_single_choice, list3);
		lv_3.setAdapter(mAdapter);
		lv_3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv_4.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		// 设置点击响应lv1
		lv_1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> persion, View view,
					int ponsion, long id) {
				// TODO Auto-generated method stub
				for (int i = 0; i < 99; i++) {
					if (ponsion == i) {
						System.out.println(list1.get(i));
						lv_1_state = list1.get(i).toString();
					}
				}
				DiyToast.showToast(getActivity(), lv_1_state);
			}
		});
		// 设置点击响应lv2
		lv_2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> persion, View view,
					int ponsion, long id) {
				// TODO Auto-generated method stub
				for (int i = 0; i < 99; i++) {
					if (ponsion == i) {
						System.out.println(list2.get(i));
						lv_2_state = list2.get(i).toString();
					}
				}
				DiyToast.showToast(getActivity(), lv_2_state);
			}
		});
		// 设置点击响应lv3
		lv_3.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> persion, View view,
					int ponsion, long id) {
				// TODO Auto-generated method stub
				for (int i = 0; i < 99; i++) {
					if (ponsion == i) {
						System.out.println(list3.get(i));
						lv_3_state = list3.get(i).toString();
					}
				}
				DiyToast.showToast(getActivity(), lv_3_state);
			}
		});
		// 添加到按钮
		btn_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL(
						"insert into link_state (one,two,number,three,name)values(?,?,?,?,?)",
						new String[] { lv_1_state, lv_2_state,
								et_big_small_number.getText().toString(),
								lv_3_state, et_mode_name.getText().toString() });
				DiyToast.showToast(getActivity(), lv_1_state + lv_2_state
						+ lv_3_state);
				read();
			}
		});
		return view;
	}

	public void read() {
		// 插入s4
		Cursor cursor = db.rawQuery("select * from link_state", null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					getActivity(),
					android.R.layout.simple_list_item_multiple_choice, cursor,
					new String[] { "name" }, new int[] { android.R.id.text1 });
			lv_4.setAdapter(adapter);
			// list4.add(String.valueOf(cursor));
			// // 添加多选操作s4
			// mAdapter = new ArrayAdapter<String>(getActivity(),
			// android.R.layout.simple_list_item_multiple_choice, list4);
			// lv_4.setAdapter(mAdapter);
		}
	}
}
