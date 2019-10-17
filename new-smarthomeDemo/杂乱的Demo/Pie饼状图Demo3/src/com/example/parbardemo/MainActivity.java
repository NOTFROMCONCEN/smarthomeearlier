package com.example.parbardemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	// �󶨿ؼ�
	private EditText et_num_state, et_num2;
	static float getnum;
	private int recLen = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_num2 = (EditText) findViewById(R.id.et_num2);
		et_num_state = (EditText) findViewById(R.id.et_num_size);
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// �½�Handler�߳�
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			/**
			 * ʹ��msg.what���в���ϵ��Զ����£�
			 */
			// getnum = Integer.valueOf(msg.what);

			// �ж�EditText�ı����Ƿ�Ϊ��
			if (et_num_state.getText().toString().equals("")) {
				// ����ı���Ϊ�գ������ı���Hint��������ʾ��������getnum����Ϊ��0��
				// ���ڸ�λ����ͼ
				et_num_state.setHint("��������ֵ");
				getnum = Integer.valueOf("0");
			} else {
				// ����ı���Ϊ�գ����ı����������ֵת����float����
				// �������־������ʾ�Ƿ���ȷ�õ����ݣ�
				getnum = Integer.valueOf(et_num_state.getText().toString());
				Log.e("�õ�����ֵ", Float.toString(getnum));
			}
			handler.postDelayed(timeRunnable, 10);
		}
	};
	Runnable timeRunnable = new Runnable() {

		public void run() {
			// TODO Auto-generated method stub
			recLen = recLen + 1;
			Message msg = handler.obtainMessage();
			msg.what = recLen;
			handler.sendMessage(msg);
			// if (recLen <= 360) {
			// handler.sendMessage(msg);
			// } else {
			// handler.removeCallbacks(timeRunnable);
			// }
		}
	};
}
