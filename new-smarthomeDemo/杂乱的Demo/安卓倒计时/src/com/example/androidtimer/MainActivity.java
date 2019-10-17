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
 * @�ļ�����MainActivity.java
 * @��������ɵ���ʱ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-29
 * @�汾��1.0
 */
public class MainActivity extends Activity {
	private Button btn_10s, btn_20s, btn_30s;// ��
	private Button btn_con;// �Զ���ʱ��ȷ��
	private SeekBar sk_1;// ������
	private TextView tv_sk_time;// ������ʱ��
	private TextView tv_countdown_time;// ����ʱ
	private long num, min, sec;// ��ֵ�����ӡ���
	private CountDownTimer timer;// CountDownTimer����ʱ
	private boolean time_state = false;// ����ʱ״̬
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
			public void onStopTrackingTouch(SeekBar seekBar) {// ֹͣ����
				// TODO Auto-generated method stub
				num = Integer.valueOf(String.valueOf(seekBar.getProgress())) * 60 * 1000;
				timer = new CountDownTimer(num, 1000) {

					@Override
					public void onTick(long millisUntilFinished) {
						// TODO Auto-generated method stub
						// ����
						min = millisUntilFinished / 1000 / 60;
						// ��
						sec = millisUntilFinished / 1000 % 60;
						// ���õ���ʱ�ı�
						tv_countdown_time.setText("����ʱ����" + min + "��" + sec
								+ "��");
					}

					@Override
					public void onFinish() {
						// TODO Auto-generated method stub
						time_state = false;
						timer.cancel();
						tv_countdown_time.setText("����ʱ����" + "X" + "��" + "X"
								+ "��");
					}
				}.start();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// ��ʼ����
				// TODO Auto-generated method stub
				if (timer != null) {
					timer.cancel();
				}
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// ���ȸı�
				// TODO Auto-generated method stub
				tv_sk_time.setText("��ǰ��ֵ��"
						+ String.valueOf(seekBar.getProgress()));
			}
		});
		// �Զ���(����)
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
								// ����
								min = millisUntilFinished / 1000 / 60;
								// ��
								sec = millisUntilFinished / 1000 % 60;
								// ���õ���ʱ�ı�
								tv_countdown_time.setText("����ʱ����" + min + "��"
										+ sec + "��");
							}

							@Override
							public void onFinish() {
								// TODO Auto-generated method stub
								time_state = false;
								timer.cancel();
								tv_countdown_time.setText("����ʱ����" + "X" + "��"
										+ "X" + "��");
							}
						}.start();
					}
				}
			}
		});
		// 30��
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
							// ����
							min = millisUntilFinished / 1000 / 60;
							// ��
							sec = millisUntilFinished / 1000 % 60;
							// ���õ���ʱ�ı�
							tv_countdown_time.setText("����ʱ����" + min + "��" + sec
									+ "��");
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							time_state = false;
							timer.cancel();
							tv_countdown_time.setText("����ʱ����" + "X" + "��" + "X"
									+ "��");
						}
					}.start();
				}
			}
		});
		// 20��
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
							// ����
							min = millisUntilFinished / 1000 / 60;
							// ��
							sec = millisUntilFinished / 1000 % 60;
							// ���õ���ʱ�ı�
							tv_countdown_time.setText("����ʱ����" + min + "��" + sec
									+ "��");
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							time_state = false;
							timer.cancel();
							tv_countdown_time.setText("����ʱ����" + "X" + "��" + "X"
									+ "��");
						}
					}.start();
				}
			}
		});
		// 10��
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
							// ����
							min = millisUntilFinished / 1000 / 60;
							// ��
							sec = millisUntilFinished / 1000 % 60;
							// ���õ���ʱ�ı�
							tv_countdown_time.setText("����ʱ����" + min + "��" + sec
									+ "��");
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							time_state = false;
							timer.cancel();
							tv_countdown_time.setText("����ʱ����" + "X" + "��" + "X"
									+ "��");
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
