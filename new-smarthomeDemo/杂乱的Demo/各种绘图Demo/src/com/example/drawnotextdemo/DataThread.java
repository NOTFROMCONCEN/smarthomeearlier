package com.example.drawnotextdemo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;

public class DataThread extends Activity {
	private Random random = new Random();
	public static float temp, hum, smo, gas, ill, co, pm, press, per;
	private List<String> nowdata = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		finish();
		// �������
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			temp = Float.valueOf(random.nextInt(40) % (40 - 20 + 1));// �¶�
			hum = Float.valueOf(random.nextInt(40) % (120 - 10 + 1));// ʪ��
			gas = Float.valueOf(random.nextInt(400) % (400 - 10 + 1));// ȼ��
			smo = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// ����
			ill = Float.valueOf(random.nextInt(9999) % (9999 - 500 + 1));// ����
			co = Float.valueOf(random.nextInt(40) % (100 - 10 + 1));// Co2
			pm = Float.valueOf(random.nextInt(40) % (100 - 40 + 1));// pm2.5
			press = Float.valueOf(random.nextInt(1800) % (1800 - 10 + 1));// ��ѹ
			if (Float.valueOf(random.nextInt(10) % (10 - 1 + 1)) > 5) {
				per = 1;
			} else {
				per = 0;
			}
			nowdata.add("�¶ȣ�" + String.valueOf(temp));
			nowdata.add("ʪ�ȣ�" + String.valueOf(hum));
			nowdata.add("ȼ����" + String.valueOf(gas));
			nowdata.add("��ѹ��" + String.valueOf(press));
			nowdata.add("���գ�" + String.valueOf(ill));
			nowdata.add("����" + String.valueOf(smo));
			nowdata.add("PM2.5��" + String.valueOf(pm));
			nowdata.add("Co2��" + String.valueOf(co));
			nowdata.add("������⣺" + String.valueOf(per));
			System.out.println(nowdata);
			handler.postDelayed(timeRunnable, 50);
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

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	};
}
