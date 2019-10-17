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
	// 绑定控件
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

	// 新建Handler线程
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			/**
			 * 使用msg.what进行不间断的自动更新：
			 */
			// getnum = Integer.valueOf(msg.what);

			// 判断EditText文本框是否为空
			if (et_num_state.getText().toString().equals("")) {
				// 如果文本框为空，设置文本框Hint（用于提示）；并把getnum设置为“0“
				// 用于复位扇形图
				et_num_state.setHint("请输入数值");
				getnum = Integer.valueOf("0");
			} else {
				// 如果文本框不为空，则将文本框输入的数值转换成float函数
				// 并输出日志用于提示是否正确得到数据；
				getnum = Integer.valueOf(et_num_state.getText().toString());
				Log.e("得到的数值", Float.toString(getnum));
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
