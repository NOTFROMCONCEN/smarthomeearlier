package com.example.updatabackinjavaclass;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ImageView iv_1;
	private int number = 0;
	private Button btn_select;
	private EditText et_number_get;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_1 = (ImageView) findViewById(R.id.imageView1);
		et_number_get = (EditText) findViewById(R.id.et_get_number);
		btn_select = (Button) findViewById(R.id.btn_select);
		btn_select.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_number_get.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "请输入数值",
							Toast.LENGTH_SHORT).show();
				} else {
					int number_get = Integer.valueOf(et_number_get.getText()
							.toString());

					if (number_get > 9) {
						Toast.makeText(getApplicationContext(), "数值溢出（1-10）",
								Toast.LENGTH_SHORT).show();
					} else if (number_get < 0) {
						Toast.makeText(getApplicationContext(), "数值溢出（1-10）",
								Toast.LENGTH_SHORT).show();
					} else {
						switch (number_get) {
						case 1:
							iv_1.setBackgroundResource(R.drawable.one);
							break;
						case 2:
							iv_1.setBackgroundResource(R.drawable.two);
							break;
						case 3:
							iv_1.setBackgroundResource(R.drawable.three);
							break;
						case 4:
							iv_1.setBackgroundResource(R.drawable.four);
							break;
						case 5:
							iv_1.setBackgroundResource(R.drawable.frive);
							break;
						case 6:
							iv_1.setBackgroundResource(R.drawable.six);
							break;
						case 7:
							iv_1.setBackgroundResource(R.drawable.seven);
							break;
						case 8:
							iv_1.setBackgroundResource(R.drawable.eight);
							break;
						case 9:
							iv_1.setBackgroundResource(R.drawable.nine);
							break;
						case 0:
							iv_1.setBackgroundResource(R.drawable.zero);
							break;
						default:
							break;
						}
					}
				}
			}
		});
		// 激活进程
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
			switch (msg.what) {
			case 10:
				iv_1.setBackgroundResource(R.drawable.nine);
				break;
			case 20:
				iv_1.setBackgroundResource(R.drawable.eight);
				break;
			case 30:
				iv_1.setBackgroundResource(R.drawable.seven);
				break;
			case 40:
				iv_1.setBackgroundResource(R.drawable.six);
				break;
			case 50:
				iv_1.setBackgroundResource(R.drawable.frive);
				break;
			case 60:
				iv_1.setBackgroundResource(R.drawable.four);
				break;
			case 70:
				iv_1.setBackgroundResource(R.drawable.three);
				break;
			case 80:
				iv_1.setBackgroundResource(R.drawable.two);
				break;
			case 90:
				iv_1.setBackgroundResource(R.drawable.one);
				break;
			case 100:
				iv_1.setBackgroundResource(R.drawable.zero);
				break;
			default:
				break;
			}
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number += 5;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

}
