package com.example.drawdemo928;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @�ļ�����UnLockActivity.java
 * @����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-28
 */
public class UnLockActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unlock);
		SeekBar sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() < 99) {
					DiyToast.showTaost(getApplicationContext(), "����ɻ�������");
					seekBar.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					web_state();
					DiyToast.showTaost(getApplicationContext(), "�����ɹ�");
					for (int i = 0; i < 400; i++) {
						if (i == 399) {
							Intent intent = new Intent(UnLockActivity.this,
									BarActivity.class);
							startActivity(intent);
							finish();
						}
					}
				}
			}
		});
	}

	private void web_state() {
		ControlUtils.setUser("bizideal", "123456", MainActivity.ip);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String state) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (state.equals("Success")) {
							DiyToast.showTaost(getApplicationContext(),
									"�������ӳɹ�");
						} else {
							DiyToast.showTaost(getApplicationContext(),
									"��������ʧ��");
						}
					}
				});
			}
		});
	}
}
