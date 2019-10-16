package com.example.guosaicdemo831;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * @文件名：MenuActivity.java
 * @描述：完成选择、跳转、图片切换功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-31
 */
public class MenuActivity extends Fragment implements OnClickListener {
	private TextView tvselect_datetime;
	private String datetimevalue;
	private ImageView ivbasic, ivlinkage, ivpattern, ivchart;
	private TextView tvbasic, tvlinkage, tvpattern, tvchart, tv_menu_time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_menu, container, false);

		ivbasic = (ImageView) view.findViewById(R.id.ivbasic);
		ivlinkage = (ImageView) view.findViewById(R.id.ivlinkage);
		ivpattern = (ImageView) view.findViewById(R.id.ivpattern);
		ivchart = (ImageView) view.findViewById(R.id.ivchart);
		tv_menu_time = (TextView) view.findViewById(R.id.tv_menu_time);
		tvbasic = (TextView) view.findViewById(R.id.tvbasic);
		tvlinkage = (TextView) view.findViewById(R.id.tvlinkage);
		tvpattern = (TextView) view.findViewById(R.id.tvpattern);
		tvchart = (TextView) view.findViewById(R.id.tvchart);
		tvbasic.setOnClickListener(this);
		tvlinkage.setOnClickListener(this);
		tvpattern.setOnClickListener(this);
		tvchart.setOnClickListener(this);
		ivbasic.setVisibility(View.VISIBLE);
		ivlinkage.setVisibility(View.INVISIBLE);
		ivpattern.setVisibility(View.INVISIBLE);
		ivchart.setVisibility(View.INVISIBLE);
		handler.post(timeRunnable);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tvbasic:
			ivbasic.setVisibility(View.VISIBLE);
			ivlinkage.setVisibility(View.INVISIBLE);
			ivpattern.setVisibility(View.INVISIBLE);
			ivchart.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(1);
			break;
		case R.id.tvlinkage:
			ivbasic.setVisibility(View.INVISIBLE);
			ivlinkage.setVisibility(View.VISIBLE);
			ivpattern.setVisibility(View.INVISIBLE);
			ivchart.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(2);
			break;

		case R.id.tvpattern:
			ivbasic.setVisibility(View.INVISIBLE);
			ivlinkage.setVisibility(View.INVISIBLE);
			ivpattern.setVisibility(View.VISIBLE);
			ivchart.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(3);
			break;

		case R.id.tvchart:
			ivbasic.setVisibility(View.INVISIBLE);
			ivlinkage.setVisibility(View.INVISIBLE);
			ivpattern.setVisibility(View.INVISIBLE);
			ivchart.setVisibility(View.VISIBLE);
			BarActivity.mViewPager.setCurrentItem(4);
			break;

		default:
			break;
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_menu_time.setText(BarActivity.get_time);
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
}
