package com.example.shengsaiademo2017918;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * @�ļ�����ChoseActivity.java
 * @����������ѡ���л�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-18
 */
public class ChoseActivity extends Fragment implements OnClickListener {
	private LinearLayout line_base;// ��������
	private LinearLayout line_link;// ��������
	private LinearLayout line_mode;// ģʽ����
	private LinearLayout line_draw;// ��ͼ����
	private ImageView iv_base;// ����
	private ImageView iv_link;// ����
	private ImageView iv_mode;// ģʽ
	private ImageView iv_draw;// ��ͼ
	private TextView tv_time;// ʱ��

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_chose, container, false);

		iv_base = (ImageView) view.findViewById(R.id.iv_base);
		iv_draw = (ImageView) view.findViewById(R.id.iv_draw);
		iv_link = (ImageView) view.findViewById(R.id.iv_link);
		iv_mode = (ImageView) view.findViewById(R.id.iv_mode);
		line_base = (LinearLayout) view.findViewById(R.id.line_base);
		line_draw = (LinearLayout) view.findViewById(R.id.line_draw);
		line_link = (LinearLayout) view.findViewById(R.id.line_link);
		line_mode = (LinearLayout) view.findViewById(R.id.line_mode);
		tv_time = (TextView) view.findViewById(R.id.tv_chose_time);
		iv_base.setOnClickListener(this);
		iv_draw.setOnClickListener(this);
		iv_link.setOnClickListener(this);
		iv_mode.setOnClickListener(this);
		// �����߳�
		handler.post(timeRunnable);
		iv_base.setVisibility(View.VISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		iv_draw.setVisibility(View.INVISIBLE);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_base:
			iv_base.setVisibility(View.VISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(1);
			break;
		case R.id.line_link:
			iv_base.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.VISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(1);
			break;
		case R.id.line_mode:
			iv_base.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.VISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(1);
			break;
		case R.id.line_draw:
			iv_base.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.VISIBLE);
			BarActivity.mViewPager.setCurrentItem(1);
			break;

		default:
			break;
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_time.setText(BarActivity.time);
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
