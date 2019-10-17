package com.example.guosaiademo921;

import java.text.DecimalFormat;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/*
 * @文件名：LinkStateActivity.java
 * @描述：连接状态
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-21
 */
public class LinkStateActivity extends Activity {
	private TextView tv_a8_state;// A8
	private TextView tv_smo_state;// 烟雾
	private TextView tv_press_state;// 气压
	private TextView tv_gas_state;// 火焰
	private TextView tv_co_state;// Co2
	private TextView tv_temp_state;// 温度
	private TextView tv_hum_state;// 湿度
	private TextView tv_ill_state;// 光照
	private TextView tv_pm_state;// PM2.5
	private TextView tv_per_state;// 人体红外
	private String online = "在线";
	private String offonline = "离线";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link_state);
		tv_a8_state = (TextView) findViewById(R.id.tv_a8_state);
		tv_co_state = (TextView) findViewById(R.id.tv_co_state);
		tv_gas_state = (TextView) findViewById(R.id.tv_gas_state);
		tv_hum_state = (TextView) findViewById(R.id.tv_hum_state);
		tv_ill_state = (TextView) findViewById(R.id.tv_ill_state);
		tv_per_state = (TextView) findViewById(R.id.tv_per_state);
		tv_press_state = (TextView) findViewById(R.id.tv_press_state);
		tv_smo_state = (TextView) findViewById(R.id.tv_smo_state);
		tv_temp_state = (TextView) findViewById(R.id.tv_temp_state);
		tv_pm_state = (TextView) findViewById(R.id.tv_pm_state);
		// 激活进程
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (IndexActivity.temp <= 0) {
				tv_temp_state.setText(offonline);
			} else {
				tv_temp_state.setText(online);
			}
			if (IndexActivity.smo <= 0) {
				tv_smo_state.setText(offonline);
			} else {
				tv_smo_state.setText(online);
			}
			if (IndexActivity.hum <= 0) {
				tv_hum_state.setText(offonline);
			} else {
				tv_hum_state.setText(online);
			}
			if (IndexActivity.press <= 0) {
				tv_press_state.setText(offonline);
			} else {
				tv_press_state.setText(online);
			}
			if (IndexActivity.gas <= 0) {
				tv_gas_state.setText(offonline);
			} else {
				tv_gas_state.setText(online);
			}
			if (IndexActivity.co <= 0) {
				tv_co_state.setText(offonline);
			} else {
				tv_co_state.setText(online);
			}
			if (IndexActivity.ill <= 0) {
				tv_ill_state.setText(offonline);
			} else {
				tv_ill_state.setText(online);
			}
			if (IndexActivity.pm <= 0) {
				tv_pm_state.setText(offonline);
			} else {
				tv_pm_state.setText(online);
			}
			if (IndexActivity.per <= 0) {
				tv_per_state.setText(offonline);
			} else {
				tv_per_state.setText(online);
			}

			if (IndexActivity.web_state) {
				tv_a8_state.setText("离线");
			} else {
				tv_a8_state.setText("在线");
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
