package com.example.guosaicdemo904;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * @�ļ�����ChoseActivity.java
 * @������UI��ʾ������ѡ�񻬶���ָ��ҳ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-4
 */
public class ChoseActivity extends Fragment implements OnClickListener {
	// ����
	private LinearLayout line_base;// ����
	private LinearLayout line_link;// ����
	private LinearLayout line_mode;// ģʽ
	private LinearLayout line_draw;// ��ͼ
	// ͼƬ
	private ImageView iv_base;// ����
	private ImageView iv_link;// ����
	private ImageView iv_mode;// ģʽ
	private ImageView iv_draw;// ��ͼ
	// ����
	private TextView tv_base;// ����
	private TextView tv_link;// ����
	private TextView tv_mode;// ģʽ
	private TextView tv_draw;// ��ͼ
	// ʱ��
	private TextView tv_chose_time;
	private String datetimevalue;
	// ����
	Handler handler;
	Runnable timeRunnable;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_chose, container, false);
		line_base = (LinearLayout) view.findViewById(R.id.line_base);
		line_draw = (LinearLayout) view.findViewById(R.id.line_draw);
		line_link = (LinearLayout) view.findViewById(R.id.line_link);
		line_mode = (LinearLayout) view.findViewById(R.id.line_mode);
		iv_base = (ImageView) view.findViewById(R.id.iv_base);
		iv_draw = (ImageView) view.findViewById(R.id.iv_draw);
		iv_link = (ImageView) view.findViewById(R.id.iv_link);
		iv_mode = (ImageView) view.findViewById(R.id.iv_mode);
		tv_base = (TextView) view.findViewById(R.id.tv_base);
		tv_draw = (TextView) view.findViewById(R.id.tv_draw);
		tv_link = (TextView) view.findViewById(R.id.tv_link);
		tv_mode = (TextView) view.findViewById(R.id.tv_mode);
		tv_chose_time = (TextView) view.findViewById(R.id.tv_chose_time);
		line_base.setOnClickListener(this);
		line_draw.setOnClickListener(this);
		line_link.setOnClickListener(this);
		line_mode.setOnClickListener(this);
		// ��ȡʱ��
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				// ����ʱ��
				SimpleDateFormat formater = new SimpleDateFormat(
						"yyyy��MM��dd�� HH:mm:ss");
				formater.setTimeZone(TimeZone.getTimeZone("GMT+8"));
				datetimevalue = formater.format(new java.util.Date());
				tv_chose_time.setText(datetimevalue);
				handler.postDelayed(timeRunnable, 1000);
			}
		};
		timeRunnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		};
		// �������
		handler.post(timeRunnable);
		iv_base.setVisibility(View.VISIBLE);
		iv_draw.setVisibility(View.INVISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		return view;
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @ʱ �䣺����9:10:43
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_base:
			iv_base.setVisibility(View.VISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(1);
			break;
		case R.id.line_draw:
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.VISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(4);
			break;
		case R.id.line_link:
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.VISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(2);
			break;
		case R.id.line_mode:
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.VISIBLE);
			BarActivity.mViewPager.setCurrentItem(3);
			break;
		case R.id.tv_base:
			iv_base.setVisibility(View.VISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(1);
			break;
		case R.id.tv_draw:
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.VISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(4);
			break;
		case R.id.tv_link:
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.VISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			BarActivity.mViewPager.setCurrentItem(2);
			break;
		case R.id.tv_mode:
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.VISIBLE);
			BarActivity.mViewPager.setCurrentItem(3);
			break;

		default:
			break;
		}
	}
}
