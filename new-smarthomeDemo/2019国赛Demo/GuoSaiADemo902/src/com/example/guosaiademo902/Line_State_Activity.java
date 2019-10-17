package com.example.guosaiademo902;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class Line_State_Activity extends Activity {
	private TextView tv_a8_state;// A8״̬
	private TextView tv_smo_state;// ����״̬
	private TextView tv_hum_state;// ʪ��״̬
	private TextView tv_press_state;// ��ѹ״̬
	private TextView tv_gas_state;// ����״̬
	private TextView tv_co_state;// Co2״̬
	private TextView tv_temp_state;// ����״̬
	private TextView tv_ill_state;// �¶�״̬
	private TextView tv_pm_state;// PM2.5״̬
	private TextView tv_per_state;// �������״̬

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_line_state);
		tv_a8_state = (TextView) findViewById(R.id.tv_a8_state);
		tv_co_state = (TextView) findViewById(R.id.tv_co_state);
		tv_gas_state = (TextView) findViewById(R.id.tv_gas_state);
		tv_hum_state = (TextView) findViewById(R.id.tv_hum_state);
		tv_ill_state = (TextView) findViewById(R.id.tv_ill_state);
		tv_per_state = (TextView) findViewById(R.id.tv_per_state);
		tv_pm_state = (TextView) findViewById(R.id.tv_pm_state);
		tv_press_state = (TextView) findViewById(R.id.tv_press_state);
		tv_smo_state = (TextView) findViewById(R.id.tv_smo_state);
		tv_temp_state = (TextView) findViewById(R.id.tv_temp_state);
		// �������
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (IndexActivity.co > 20) {
				tv_co_state.setText("����");
			} else {
				tv_co_state.setText("����");
			}
			if (IndexActivity.gas > 20) {
				tv_gas_state.setText("����");
			} else {
				tv_gas_state.setText("����");
			}
			if (IndexActivity.hum > 20) {
				tv_hum_state.setText("����");
			} else {
				tv_hum_state.setText("����");
			}
			if (IndexActivity.ill > 20) {
				tv_ill_state.setText("����");
			} else {
				tv_ill_state.setText("����");
			}
			if (IndexActivity.per > 0) {
				tv_per_state.setText("����");
			} else {
				tv_per_state.setText("����");
			}
			if (IndexActivity.pm > 20) {
				tv_pm_state.setText("����");
			} else {
				tv_pm_state.setText("����");
			}
			if (IndexActivity.press > 20) {
				tv_press_state.setText("����");
			} else {
				tv_press_state.setText("����");
			}
			if (IndexActivity.smo > 20) {
				tv_smo_state.setText("����");
			} else {
				tv_smo_state.setText("����");
			}
			if (IndexActivity.temp > 20) {
				tv_temp_state.setText("����");
			} else {
				tv_temp_state.setText("����");
			}
			tv_a8_state.setText("����");
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
