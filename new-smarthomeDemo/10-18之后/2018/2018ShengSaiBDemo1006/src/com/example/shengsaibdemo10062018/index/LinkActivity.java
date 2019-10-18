package com.example.shengsaibdemo10062018.index;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaibdemo10062018.R;
import com.example.shengsaibdemo10062018.toast.DiyToast;

/*
 * @文件名：LinkActivity.java
 * @描述：联动
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-6
 */
public class LinkActivity extends Activity {
	private Spinner sp_1;
	private Spinner sp_2;
	private Spinner sp_3;
	private Spinner sp_4;
	private EditText et_number_get_1;
	private EditText et_number_get_2;
	private CheckBox cb_link_1, cb_link_2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		setTitle("LinkActivity");
		initView();// 绑定
		cb_link_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get_1.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "数值不能为空");
						cb_link_1.setChecked(false);
					}
				}
			}
		});
		cb_link_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get_2.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "数值不能为空");
						cb_link_2.setChecked(false);
					}
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：联动
	 * 
	 * @时 间：上午9:40:02
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			/**
			 * 第一部分联动
			 */
			if (cb_link_1.isChecked()) {
				if (et_number_get_1.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入数值");
					cb_link_1.setChecked(false);
				} else {
					String spinner1 = sp_1.getSelectedItem().toString();
					String spinner2 = sp_2.getSelectedItem().toString();
					int number_get = Integer.valueOf(et_number_get_1.getText()
							.toString());
					if (spinner1.equals("温度")) {
						if (spinner2.equals(">")) {
							if (BaseActivity.temp > number_get) {
								// 开风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								cb_link_1.setChecked(false);
							}
						}
						if (spinner2.equals("<=")) {
							if (BaseActivity.temp < number_get
									|| BaseActivity.temp == number_get) {
								// 开风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								cb_link_1.setChecked(false);
							}
						}
					}
					if (spinner1.equals("湿度")) {
						if (spinner2.equals(">")) {
							if (BaseActivity.hum > number_get) {
								// 开风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								cb_link_1.setChecked(false);
							}
						}
						if (spinner2.equals("<=")) {
							if (BaseActivity.hum < number_get
									|| BaseActivity.hum == number_get) {
								// 开风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								cb_link_1.setChecked(false);
							}
						}
					}
				}
			}
			/**
			 * 第二部分联动
			 */
			if (cb_link_2.isChecked()) {
				if (et_number_get_2.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入数值");
					cb_link_2.setChecked(false);
				} else {
					String spinner3 = sp_3.getSelectedItem().toString();
					String spinner4 = sp_4.getSelectedItem().toString();
					int number_get = Integer.valueOf(et_number_get_2.getText()
							.toString());
					if (spinner3.equals(">")) {
						if (BaseActivity.ill > number_get) {
							if (spinner4.equals("报警灯开")) {// 开报警灯
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner4.equals("射灯全开")) {// 射灯
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							cb_link_2.setChecked(false);
						}
					}
					if (spinner4.equals("<=")) {
						if (BaseActivity.ill < number_get
								|| BaseActivity.ill == number_get) {
							if (spinner4.equals("报警灯开")) {// 开报警灯
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner4.equals("射灯全开")) {// 射灯
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							cb_link_2.setChecked(false);
						}
					}
				}
			}
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
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午9:38:11
	 */
	private void initView() {
		// TODO Auto-generated method stub
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		sp_4 = (Spinner) findViewById(R.id.spinner4);
		et_number_get_1 = (EditText) findViewById(R.id.et_number_get_1);
		et_number_get_2 = (EditText) findViewById(R.id.et_number_get_2);
		cb_link_1 = (CheckBox) findViewById(R.id.cb_1);
		cb_link_2 = (CheckBox) findViewById(R.id.cb_2);
	}
}