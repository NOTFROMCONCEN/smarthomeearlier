package com.example.guosaijdemo926;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/*
 * @文件名：DrawActivity.java
 * @描述：绘图、模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-29
 */
public class DrawActivity extends Activity {
	private CheckBox cb_inclass_mode;// 上班模式
	private CheckBox cb_outclass_mode;// 下班模式
	private CheckBox cb_seelp_mode;// 睡眠模式
	private View myview;
	public static boolean draw_state = true;
	public static float getdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw);
		cb_inclass_mode = (CheckBox) findViewById(R.id.cb_shangban_mode);
		cb_outclass_mode = (CheckBox) findViewById(R.id.cb_xiaban_mode);
		cb_seelp_mode = (CheckBox) findViewById(R.id.cb_shuimian_mode);
		myview = (View) findViewById(R.id.myview);
		cb_inclass_mode
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							// cb_inclass_mode.setChecked(false);
							cb_outclass_mode.setChecked(false);
							cb_seelp_mode.setChecked(false);
							myview.setVisibility(View.INVISIBLE);
							draw_state = false;
						} else {
							if (cb_inclass_mode.isChecked() == false
									&& cb_outclass_mode.isChecked() == false
									&& cb_seelp_mode.isChecked() == false) {
								myview.setVisibility(View.VISIBLE);
								draw_state = true;
							}
						}
					}
				});
		cb_outclass_mode
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							cb_inclass_mode.setChecked(false);
							// cb_outclass_mode.setChecked(false);
							draw_state = false;

							myview.setVisibility(View.INVISIBLE);
						} else {
							if (cb_inclass_mode.isChecked() == false
									&& cb_outclass_mode.isChecked() == false
									&& cb_seelp_mode.isChecked() == false) {
								myview.setVisibility(View.VISIBLE);
								draw_state = true;
							}
						}
					}
				});
		cb_seelp_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_inclass_mode.setChecked(false);
					draw_state = false;
					// cb_seelp_mode.setChecked(false);
					myview.setVisibility(View.INVISIBLE);
				} else {
					if (cb_inclass_mode.isChecked() == false
							&& cb_outclass_mode.isChecked() == false
							&& cb_seelp_mode.isChecked() == false) {
						myview.setVisibility(View.VISIBLE);
						draw_state = true;
					}
				}
			}
		});
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			getdata = BaseActivity.gas;
			if (cb_inclass_mode.isChecked()) {
				// 上班

			}
			if (cb_outclass_mode.isChecked()) {
				// 下班

			}
			if (cb_seelp_mode.isChecked()) {
				// 睡眠

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
