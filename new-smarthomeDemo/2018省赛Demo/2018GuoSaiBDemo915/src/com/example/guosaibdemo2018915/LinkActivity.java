package com.example.guosaibdemo2018915;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * @文件名：LinkActivity.java
 * @描述：联动
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-15
 */
public class LinkActivity extends Activity {
	private Spinner sp_1;
	private Spinner sp_2;
	private Spinner sp_3;
	private Spinner sp_4;
	private EditText et_number_get_1;
	private EditText et_number_get_2;
	private boolean link_1_state = false;
	private boolean link_2_state = false;
	private CheckBox cb_1, cb_2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		et_number_get_1 = (EditText) findViewById(R.id.et_number_get_1);
		et_number_get_2 = (EditText) findViewById(R.id.et_number_get_2);
		sp_1 = (Spinner) findViewById(R.id.sp_1);
		sp_2 = (Spinner) findViewById(R.id.sp_2);
		sp_3 = (Spinner) findViewById(R.id.sp_3);
		sp_4 = (Spinner) findViewById(R.id.sp_4);
		cb_1 = (CheckBox) findViewById(R.id.cb_link_1);
		cb_2 = (CheckBox) findViewById(R.id.cb_link_2);
		// 联动1
		cb_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get_1.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入数值");
						cb_1.setChecked(false);
						link_1_state = false;
					} else {
						link_1_state = true;
					}
				} else {
					link_1_state = false;
				}
			}
		});
		// 联动2
		cb_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get_2.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入数值");
						cb_2.setChecked(false);
						link_2_state = false;
					} else {
						link_2_state = true;
					}
				} else {
					link_2_state = false;
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (link_2_state) {
				String spinner3 = sp_3.getSelectedItem().toString();
				String spinner4 = sp_4.getSelectedItem().toString();
				int number_get = Integer.valueOf(et_number_get_2.getText()
						.toString());
				if (spinner3.equals(">")) {
					if (BaseActivity.ill > number_get) {
						if (spinner4.equals("报警灯开")) {
							ControlUtils
									.control(ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "条件不满足");
						link_2_state = false;
						cb_2.setChecked(false);
					}
				}
				if (spinner3.equals("<=")) {
					if (BaseActivity.ill < number_get) {
						if (spinner4.equals("报警灯开")) {
							ControlUtils
									.control(ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "条件不满足");
						link_2_state = false;
						cb_2.setChecked(false);
					}

				}
			}
			if (link_1_state) {
				String spinner1 = sp_1.getSelectedItem().toString();
				String spinner2 = sp_2.getSelectedItem().toString();
				int number_get = Integer.valueOf(et_number_get_1.getText()
						.toString());
				if (spinner1.equals("温度")) {
					if (spinner2.equals(">")) {
						if (BaseActivity.temp > number_get) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							link_1_state = false;
							cb_1.setChecked(false);
						}
					}
					if (spinner2.equals("<=")) {
						if (BaseActivity.temp < number_get) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							link_1_state = false;
							cb_1.setChecked(false);
						}

					}
				}
				if (spinner1.equals("湿度")) {
					if (spinner2.equals(">")) {
						if (BaseActivity.hum > number_get) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							link_1_state = false;
							cb_1.setChecked(false);
						}
					}
					if (spinner2.equals("<=")) {
						if (BaseActivity.hum < number_get) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							DiyToast.showToast(getApplicationContext(), "条件不满足");
							link_1_state = false;
							cb_1.setChecked(false);
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
}
