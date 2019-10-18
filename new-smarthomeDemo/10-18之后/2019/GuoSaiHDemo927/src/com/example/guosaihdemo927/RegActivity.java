package com.example.guosaihdemo927;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @�ļ�����RegActivity.java
 * @������ע���˻�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-27
 */
public class RegActivity extends Activity {
	private ToggleButton tg_reg_logo;// logoͼ��
	private ToggleButton tg_reg_maril;// ��ͼ��
	private ToggleButton tg_reg_gril;// Ůͼ��
	private ToggleButton tg_reg_qq;// qqͼ��
	private Button btn_con;// ע��ȷ����ť
	private Button btn_cls;// ע��رհ�ť
	private TextView tv_tips;// ע����ʾ
	private boolean maril_state = false;// ע��״̬���û���ѡ�У�
	private boolean qq_state = false;// ע��״̬���û���ѡ�У�
	private boolean logo_state = false;// ע��״̬���û���ѡ�У�
	private boolean gril_state = false;// ע��״̬���û���ѡ�У�
	private boolean reg_state = false;// ע��״̬
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		// �󶨿ؼ�
		initView();
		// ע�ᰴť
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tg_reg_gril.isChecked() || tg_reg_logo.isChecked()
						|| tg_reg_maril.isChecked() || tg_reg_qq.isChecked()) {
					reg_state = true;
				} else {
					reg_state = false;
					DiyToast.showToast(getApplicationContext(), "��ѡ��Ҫע����û�ͷ��");
				}
			}
		});
		// �رհ�ť
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		// ���á�ֻ��ѡ��һ��ͷ��Ч��
		// Ů�����
		tg_reg_gril.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = true;
					maril_state = false;
					qq_state = false;
					logo_state = false;
					tg_reg_gril.setChecked(true);
					tg_reg_logo.setChecked(false);
					tg_reg_maril.setChecked(false);
					tg_reg_qq.setChecked(false);
				}
			}
		});
		// �б����
		tg_reg_maril.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = true;
					qq_state = false;
					logo_state = false;
					tg_reg_gril.setChecked(false);
					tg_reg_logo.setChecked(false);
					tg_reg_maril.setChecked(true);
					tg_reg_qq.setChecked(false);
				}
			}
		});
		// qq�����
		tg_reg_qq.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = false;
					qq_state = true;
					logo_state = false;
					tg_reg_gril.setChecked(false);
					tg_reg_logo.setChecked(false);
					tg_reg_maril.setChecked(false);
					tg_reg_qq.setChecked(true);
				}
			}
		});
		// logo�����
		tg_reg_logo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = false;
					qq_state = false;
					logo_state = true;
					tg_reg_gril.setChecked(false);
					tg_reg_logo.setChecked(true);
					tg_reg_maril.setChecked(false);
					tg_reg_qq.setChecked(false);
				}
			}
		});
		// �������
		handler.post(timeRunnable);
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
			if (reg_state) {
				// �����ͼ���¼
				if (maril_state) {
					number++;// ������
					System.out.println(number);
					if (number == 1) {
						tv_tips.setText("����ע���û���Ϣ");
					}
					if (number == 3) {
						tv_tips.setText("�û���Ϣע��ɹ���2��󷵻ص�¼���档");
					}
					if (number == 5) {
						MainActivity.sharedPreferences.edit()
								.putBoolean("maril", true).commit();
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					}
				}
				// ���Ů��ͼ���¼
				if (gril_state) {
					number++;// ������
					System.out.println(number);
					if (number == 1) {
						tv_tips.setText("����ע���û���Ϣ");
					}
					if (number == 3) {
						tv_tips.setText("�û���Ϣע��ɹ���2��󷵻ص�¼���档");
					}
					if (number == 5) {
						MainActivity.sharedPreferences.edit()
								.putBoolean("gril", true).commit();
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					}
				}
				// ���logoͼ���¼
				if (logo_state) {
					number++;// ������
					System.out.println(number);
					if (number == 1) {
						tv_tips.setText("����ע���û���Ϣ");
					}
					if (number == 3) {
						tv_tips.setText("�û���Ϣע��ɹ���2��󷵻ص�¼���档");
					}
					if (number == 5) {
						MainActivity.sharedPreferences.edit()
								.putBoolean("logo", true).commit();
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					}
				}
				// ���QQͼ���¼
				if (qq_state) {
					number++;// ������
					System.out.println(number);
					if (number == 1) {
						tv_tips.setText("����ע���û���Ϣ");
					}
					if (number == 4) {
						tv_tips.setText("�û���Ϣע��ɹ���2��󷵻ص�¼���档");
					}
					if (number == 6) {
						MainActivity.sharedPreferences.edit()
								.putBoolean("qq", true).commit();
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					}
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
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		tg_reg_gril = (ToggleButton) findViewById(R.id.tg_reg_gril);
		tg_reg_logo = (ToggleButton) findViewById(R.id.tg_reg_logo);
		tg_reg_maril = (ToggleButton) findViewById(R.id.tg_reg_maril);
		tg_reg_qq = (ToggleButton) findViewById(R.id.tg_reg_qq);
		tv_tips = (TextView) findViewById(R.id.tv_reg_tips);
	}
}
