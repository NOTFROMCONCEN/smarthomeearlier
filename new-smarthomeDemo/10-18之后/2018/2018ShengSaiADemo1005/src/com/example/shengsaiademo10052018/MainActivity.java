package com.example.shengsaiademo10052018;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @����������������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-5
 */
public class MainActivity extends Activity {
	private ProgressBar progressBar1;
	private TextView tv_loading_number;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		// tv_1 = (TextView) findViewById(R.id.tv_loading_number);
		autoFindView();
		// �������
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			progressBar1.setProgress(msg.what);
			tv_loading_number.setText("���ڼ��ء�����" + String.valueOf(msg.what)
					+ "%");
			if (msg.what == 100) {
				startActivity(new Intent(MainActivity.this, LoginActivity.class));
				finish();
			}
			handler.postDelayed(timeRunnable, 50);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

	// �Զ�findViewById(���뱣֤�����ļ��е�id�������һ��)
	private void autoFindView() {
		Class mClass = this.getClass();
		while (true) {
			Field[] fields = mClass.getDeclaredFields();
			for (Field field : fields) {
				if (View.class.isAssignableFrom(field.getType())) {
					String fieldName = field.getName();
					View view = findViewById(getResources().getIdentifier(
							fieldName, "id", getPackageName()));
					if (view != null) {
						field.setAccessible(true);
						try {
							field.set(this, view);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
			mClass = mClass.getSuperclass();
			// tip�����޸�Activity.class.getName()������ָ����Ѱ��������
			if (mClass.getName().equals(Activity.class.getName()))
				break;
		}
	}
}
