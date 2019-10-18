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
 * @�ļ�����ChoseActivity.java
 * @������ѡ�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-23
 */
public class ChoseActivity extends Fragment implements OnClickListener {
	private ImageView iv_base;// ��������
	private ImageView iv_link;// ��������
	private ImageView iv_mode;// ģʽ����
	private ImageView iv_draw;// ��ͼ����
	private TextView tv_base;// ��������
	private TextView tv_link;// ��������
	private TextView tv_mode;// ģʽ����
	private TextView tv_draw;// ��ͼ����
	private TextView tv_chose_time;// ʱ��

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
		// ����Ĭ������
		iv_base.setVisibility(View.VISIBLE);
		iv_draw.setVisibility(View.INVISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		// �������
		handler.post(timeRunnable);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_base:
			// ����
			BarActivity.mViewPager.setCurrentItem(1);
			iv_base.setVisibility(View.VISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			break;
		case R.id.tv_link:
			// ����
			BarActivity.mViewPager.setCurrentItem(2);
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.VISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			break;
		case R.id.tv_mode:
			// ģʽ
			BarActivity.mViewPager.setCurrentItem(3);
			iv_base.setVisibility(View.INVISIBLE);
			iv_draw.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.VISIBLE);
			break;
		case R.id.tv_draw:
			// ��ͼ
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
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ��
	 * 
	 * @ʱ �䣺����9:05:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ʱ��
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
