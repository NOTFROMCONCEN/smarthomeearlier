package com.example.guosaicdemo923;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * @文件名：ChoseActivity.java
 * @描述：选择界面
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-23
 */
public class ChoseActivity extends Fragment implements OnClickListener {
	private ImageView iv_base;// 基本界面
	private ImageView iv_link;// 联动界面
	private ImageView iv_mode;// 模式界面
	private ImageView iv_draw;// 绘图界面
	private TextView tv_base;// 基本界面
	private TextView tv_link;// 联动界面
	private TextView tv_mode;// 模式界面
	private TextView tv_draw;// 绘图界面
	private TextView tv_chose_time;// 时间

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_chose, container, false);
		iv_base = (ImageView) view.findViewById(R.id.iv_base);
		iv_draw = (ImageView) view.findViewById(R.id.iv_draw);
		iv_link = (ImageView) view.findViewById(R.id.iv_link);
		iv_mode = (ImageView) view.findViewById(R.id.iv_mode);
		tv_base = (TextView) view.findViewById(R.id.tv_base);
		tv_draw = (TextView) view.findViewById(R.id.tv_draw);
		tv_link = (TextView) view.findViewById(R.id.tv_link);
		tv_mode = (TextView) view.findViewById(R.id.tv_mode);
		tv_chose_time = (TextView) view.findViewById(R.id.tv_chose_time);
		tv_base.setOnClickListener(this);
		tv_draw.setOnClickListener(this);
		tv_link.setOnClickListener(this);
		tv_mode.setOnClickListener(this);
		// 设置默认显隐
		iv_base.setVisibility(View.VISIBLE);
		iv_draw.setVisibility(View.INVISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_base:
			// 基本
			BarActivity.mViewPager.setCurrentItem(1);
			iv_base.setVisibility(View.VISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			break;
		case R.id.tv_link:
			// 联动
			BarActivity.mViewPager.setCurrentItem(2);
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.VISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			break;
		case R.id.tv_mode:
			// 模式
			BarActivity.mViewPager.setCurrentItem(3);
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.VISIBLE);
			break;
		case R.id.tv_draw:
			// 绘图
			BarActivity.mViewPager.setCurrentItem(4);
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.VISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			break;

		default:
			break;
		}
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间
	 * 
	 * @时 间：上午9:05:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 时间
			tv_chose_time.setText(LoginActivity.get_time);
			handler.postDelayed(timeRunnable, 500);
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

}
