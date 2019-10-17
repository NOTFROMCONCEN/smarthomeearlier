package com.example.guosaihdemo927;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @�ļ�����RegActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-27
 */
public class MainActivity extends Activity {
	private ToggleButton tg_user_logo;// logoͼ��
	private ToggleButton tg_user_maril;// ��ͼ��
	private ToggleButton tg_user_gril;// Ůͼ��
	private ToggleButton tg_user_qq;// qqͼ��
	static SharedPreferences sharedPreferences;// sharedPreferences�洢
	private Button btn_reg;// ע�ᰴť
	private TextView tv_tips;// ��¼��ʾ
	private boolean maril_state = false;// ��¼״̬���û���ѡ�У�
	private boolean qq_state = false;// ��¼״̬���û���ѡ�У�
	private boolean logo_state = false;// ��¼״̬���û���ѡ�У�
	private boolean gril_state = false;// ��¼״̬���û���ѡ�У�
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// �󶨿ؼ�
		// ��������ʱ���������û�
		tg_user_gril.setVisibility(View.GONE);
		tg_user_logo.setVisibility(View.GONE);
		tg_user_maril.setVisibility(View.GONE);
		tg_user_qq.setVisibility(View.GONE);
		get_user();// ��ȡ��ע���û�
		// ע�ᰴť����¼�
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						RegActivity.class);
				startActivity(intent);
				finish();
			}
		});
		// ���á�ֻ��ѡ��һ��ͷ��Ч��
		// Ů�����
		tg_user_gril.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = true;
					maril_state = false;
					qq_state = false;
					logo_state = false;
					tg_user_gril.setChecked(true);
					// tg_user_logo.setChecked(false);
					// tg_user_maril.setChecked(false);
					// tg_user_qq.setChecked(false);
				}
			}
		});
		// �б����
		tg_user_maril.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = true;
					qq_state = false;
					logo_state = false; // tg_user_gril.setChecked(false);
					// tg_user_logo.setChecked(false);
					tg_user_maril.setChecked(true);
					// tg_user_qq.setChecked(false);
				}
			}
		});
		// qq�����
		tg_user_qq.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = false;
					qq_state = true;
					logo_state = false; // tg_user_gril.setChecked(false);
					// tg_user_logo.setChecked(false);
					// tg_user_maril.setChecked(false);
					tg_user_qq.setChecked(true);
				}
			}
		});
		// logo�����
		tg_user_logo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = false;
					qq_state = false;
					logo_state = true; // tg_user_gril.setChecked(false);
					tg_user_logo.setChecked(true);
					// tg_user_maril.setChecked(false);
					// tg_user_qq.setChecked(false);
				}
			}
		});
		// �������
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ������½����ʱ
	 * 
	 * @ʱ �䣺����8:17:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// �����ͼ���¼
			if (maril_state) {
				number++;// ������

				if (number == 1) {
					tv_tips.setText("����У���û���Ϣ������");
				}
				if (number == 3) {
					tv_tips.setText("�û���Ϣ��ȷ�����ڽ���ϵͳ������");
				}
				if (number == 5) {
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
			// ���Ů��ͼ���¼
			if (gril_state) {
				number++;// ������

				if (number == 1) {
					tv_tips.setText("����У���û���Ϣ������");
				}
				if (number == 3) {
					tv_tips.setText("�û���Ϣ��ȷ�����ڽ���ϵͳ������");
				}
				if (number == 5) {
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
			// ���logoͼ���¼
			if (logo_state) {
				number++;// ������

				if (number == 1) {
					tv_tips.setText("����У���û���Ϣ������");
				}
				if (number == 3) {
					tv_tips.setText("�û���Ϣ��ȷ�����ڽ���ϵͳ������");
				}
				if (number == 5) {
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
			// ���QQͼ���¼
			if (qq_state) {
				number++;// ������

				if (number == 1) {
					tv_tips.setText("����У���û���Ϣ������");
				}
				if (number == 3) {
					tv_tips.setText("�û���Ϣ��ȷ�����ڽ���ϵͳ������");
				}
				if (number == 5) {
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
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

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����8:08:48
	 */
	private void initView() {
		btn_reg = (Button) findViewById(R.id.btn_login);
		tg_user_gril = (ToggleButton) findViewById(R.id.tg_user_gril);
		tg_user_logo = (ToggleButton) findViewById(R.id.tg_user_logo);
		tg_user_maril = (ToggleButton) findViewById(R.id.tg_user_maril);
		tg_user_qq = (ToggleButton) findViewById(R.id.tg_user_qq);
		tv_tips = (TextView) findViewById(R.id.ttv_login_tips);
	}

	/*
	 * @��������get_user
	 * 
	 * @�� �ܣ��Ӵ洢�л�ȡ��ע���û�
	 * 
	 * @ʱ �䣺����8:09:42
	 */
	private void get_user() {
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("logo", false) == true) {// logo
				tg_user_logo.setVisibility(View.VISIBLE);
			}
			if (sharedPreferences.getBoolean("maril", false) == true) {// ��
				tg_user_maril.setVisibility(View.VISIBLE);
			}
			if (sharedPreferences.getBoolean("gril", false) == true) {// Ů
				tg_user_gril.setVisibility(View.VISIBLE);
			}
			if (sharedPreferences.getBoolean("qq", false) == true) {// QQ
				tg_user_qq.setVisibility(View.VISIBLE);
			}
		}
	}
}
