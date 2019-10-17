package com.example.androidtimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：完成倒计时功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-29
 * @版本：1.0
 */
public class MainActivity extends Activity {
	private Button btn_10s, btn_20s, btn_30s;// 秒
	private Button btn_con;// 自定义时间确定
	private SeekBar sk_1;// 滑动栏
	private TextView tv_sk_time;// 滑动栏时间
	private TextView tv_countdown_time;// 倒计时
	private long num, min, sec;// 数值、分钟、秒
	private CountDownTimer timer;// CountDownTimer倒计时
	private boolean time_state = false;// 倒计时状态
	private EditText et_number_get;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_10s = (Button) findViewById(R.id.btn_10s);
		btn_20s = (Button) findViewById(R.id.btn_20s);
		btn_30s = (Button) findViewById(R.id.btn_30s);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
		sk_1 = (SeekBar) findViewById(R.id.sk_time);
		tv_countdown_time = (TextView) findViewById(R.id.tv_countdown_time);
		tv_sk_time = (TextView) findViewById(R.id.tv_sk_time);
		// SeekBar
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// 停止滑动
				// TODO Auto-generated method stub
				num = Integer.valueOf(String.valueOf(seekBar.getProgress())) * 60 * 1000;
				timer = new CountDownTimer(num, 1000) {

					@Override
					public void onTick(long millisUntilFinished) {
						// TODO Auto-generated method stub
						// 分钟
						min = millisUntilFinished / 1000 / 60;
						// 秒
						sec = millisUntilFinished / 1000 % 60;
						// 设置倒计时文本
						tv_countdown_time.setText("倒计时还有" + min + "分" + sec
								+ "秒");
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						time_state = false;
						timer.cancel();
						tv_countdown_time.setText("倒计时还有" + "X" + "分" + "X"
								+ "秒");
					}
				}.start();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// 开始滑动
				// TODO Auto-generated method stub
				if (timer != null) {
					timer.cancel();
				}
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// 进度改变
				// TODO Auto-generated method stub
				tv_sk_time.setText("当前数值："
						+ String.valueOf(seekBar.getProgress()));
			}
		});
		// 自定义(分钟)
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!et_number_get.getText().toString().equals("")) {
					if (timer != null) {
						timer.cancel();
						time_state = true;
					} else {
						time_state = true;
					}
					if (time_state) {
						num = Integer.valueOf(et_number_get.getText()
								.toString()) * 60 * 1000;
						timer = new CountDownTimer(num, 1000) {

							@Override
							public void onTick(long millisUntilFinished) {
								// TODO Auto-generated method stub
								// 分钟
								min = millisUntilFinished / 1000 / 60;
								// 秒
								sec = millisUntilFinished / 1000 % 60;
								// 设置倒计时文本
								tv_countdown_time.setText("倒计时还有" + min + "分"
										+ sec + "秒");
							}

							@Override
							public void onFinish() {
								// TODO Auto-generated method stub
								time_state = false;
								timer.cancel();
								tv_countdown_time.setText("倒计时还有" + "X" + "分"
										+ "X" + "秒");
							}
						}.start();
					}
				}
			}
		});
		// 30秒
		btn_30s.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (timer != null) {
					timer.cancel();
					time_state = true;
				} else {
					time_state = true;
				}
				if (time_state) {
					num = Integer.valueOf("30") % 60 * 1000;
					timer = new CountDownTimer(num, 1000) {

						@Override
						public void onTick(long millisUntilFinished) {
							// TODO Auto-generated method stub
							// 分钟
							min = millisUntilFinished / 1000 / 60;
							// 秒
							sec = millisUntilFinished / 1000 % 60;
							// 设置倒计时文本
							tv_countdown_time.setText("倒计时还有" + min + "分" + sec
									+ "秒");
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							time_state = false;
							timer.cancel();
							tv_countdown_time.setText("倒计时还有" + "X" + "分" + "X"
									+ "秒");
						}
					}.start();
				}
			}
		});
		// 20秒
		btn_20s.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (timer != null) {
					timer.cancel();
					time_state = true;
				} else {
					time_state = true;
				}
				if (time_state) {
					num = Integer.valueOf("20") % 60 * 1000;
					timer = new CountDownTimer(num, 1000) {

						@Override
						public void onTick(long millisUntilFinished) {
							// TODO Auto-generated method stub
							// 分钟
							min = millisUntilFinished / 1000 / 60;
							// 秒
							sec = millisUntilFinished / 1000 % 60;
							// 设置倒计时文本
							tv_countdown_time.setText("倒计时还有" + min + "分" + sec
									+ "秒");
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							time_state = false;
							timer.cancel();
							tv_countdown_time.setText("倒计时还有" + "X" + "分" + "X"
									+ "秒");
						}
					}.start();
				}
			}
		});
		// 10秒
		btn_10s.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (timer != null) {
					timer.cancel();
					time_state = true;
				} else {
					time_state = true;
				}
				if (time_state) {
					num = Integer.valueOf("10") % 60 * 1000;
					timer = new CountDownTimer(num, 1000) {

						@Override
						public void onTick(long millisUntilFinished) {
							// TODO Auto-generated method stub
							// 分钟
							min = millisUntilFinished / 1000 / 60;
							// 秒
							sec = millisUntilFinished / 1000 % 60;
							// 设置倒计时文本
							tv_countdown_time.setText("倒计时还有" + min + "分" + sec
									+ "秒");
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							time_state = false;
							timer.cancel();
							tv_countdown_time.setText("倒计时还有" + "X" + "分" + "X"
									+ "秒");
						}
					}.start();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
