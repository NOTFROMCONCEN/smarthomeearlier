package com.example.drawdemo1003.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import com.example.drawdemo1003.R;
import com.example.drawdemo1003.helpclass.DiyToast;
import com.example.drawdemo1003.myview.MyView;

/*
 * @文件名：DrawFragment.java
 * @描述：绘制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-5
 */
public class DrawFragment extends Fragment {
	private RadioButton ra_temp, ra_smo, ra_ill;// 温度烟雾光照选项
	private ListView lv_1;
	public static float getdata;
	public static String num;
	public static boolean draw_state = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		initView(view);// 绑定控件
		lv_1.setAdapter(null);
		// 激活进程
		handler.post(timeRunnable);
		// 设置ListView点击事件
		/**
		 * 弃用，报错，HashMap无法用游标？ lv_1.setOnItemClickListener(new
		 * OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> arg0, View arg1, int
		 *           arg2, long arg3) { // TODO Auto-generated method stub //
		 *           Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
		 *           DiyToast.showToast(getActivity(), "当前数值：" + cursor); } });
		 */
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：加载ListView、传输数值
	 * 
	 * @时 间：下午3:23:13
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 当XX被选中、就传输数据采集界面的数值
			if (ra_ill.isChecked()) {
				getdata = BaseFragment.ill;
				num = "光照";
			}
			if (ra_smo.isChecked()) {
				getdata = BaseFragment.smo;
				num = "烟雾";
			}
			if (ra_temp.isChecked()) {
				getdata = BaseFragment.temp;
				num = "温度";
			}
			System.out.println(getdata);
			/**
			 * 更新ListView（分开来写了，写一块显得进程冗杂）
			 */
			getdata();
			handler.postDelayed(timeRunnable, 1000);
		}
	};

	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	/*
	 * @方法名：getdata()
	 * 
	 * @功 能：获取参数设置到ListView上
	 * 
	 * @时 间：下午3:21:34
	 */
	private void getdata() {
		/**
		 * XML文件中可将ListView设置底部聚焦
		 */
		SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
				MyView.list, R.layout.activity_draw_text, new String[] {
						"number", "data", "time" }, new int[] { R.id.tv_num,
						R.id.tv_data, R.id.tv_time });
		lv_1.setAdapter(simpleAdapter);
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：下午3:19:31
	 */
	private void initView(View view) {
		// TODO Auto-generated method stub
		ra_ill = (RadioButton) view.findViewById(R.id.ra_ill);
		ra_smo = (RadioButton) view.findViewById(R.id.ra_smo);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
	}
}
