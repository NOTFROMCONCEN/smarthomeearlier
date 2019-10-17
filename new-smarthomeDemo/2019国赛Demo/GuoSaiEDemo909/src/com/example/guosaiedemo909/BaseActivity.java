package com.example.guosaiedemo909;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @�ļ�����BaseActivity.java
 * @���������ݴ洢���豸���ơ����ݲɼ�����ת
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-9
 */
public class BaseActivity extends Activity {
	// ���ݲɼ�
	private EditText et_temp;// �¶�
	private EditText et_hum;// ʪ��
	private EditText et_smo;// ����
	private EditText et_gas;// ȼ��
	private EditText et_ill;// ����
	private EditText et_press;// ��ѹ
	private EditText et_co;// Co2
	private EditText et_pm;// PM2.5
	private EditText et_per;// �������
	// �豸����
	private ToggleButton tg_lamp;// ���
	private ToggleButton tg_cur;// ����
	private ToggleButton tg_fan;// ����
	private ToggleButton tg_warm;// ������
	private ToggleButton tg_door;// �Ž�
	private Button btn_tongdao_1;// ͨ��1
	private Button btn_tongdao_2;// ͨ��2
	private Button btn_tongdao_3;// ͨ��3
	// ����
	private LinearLayout line_intent_link;

	// float ��ֵ
	public static float temp, hum, ill, smo, per, press, co, pm, gas;

	// �����
	private Random random = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initview();
		// ���ó����¼�
		line_intent_link.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						LinkActivity.class);
				startActivity(intent);
				return false;
			}
		});
		// ��ѯ���������Ƿ�����������Dialog��ʾ
		// ��������
		ControlUtils.setUser("bizideal", "123456", MainActivity.ip_number);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String link_state) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (link_state.equals("Success")) {// ���ӳɹ�
							DiyToast.showToast(BaseActivity.this,
									"С��ʿ��ʾ���������ӳɹ�");
						} else {// ����ʧ��
							// չʾ��ʾ��
							new AlertDialog.Builder(BaseActivity.this)
									.setTitle("��ʾ")
									.setMessage("��������ʧ�ܣ��Ƿ񷵻ص�¼����")
									.setPositiveButton(
											"ȷ������",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													// ���ص�¼����
													Intent intent = new Intent(
															BaseActivity.this,
															MainActivity.class);
													startActivity(intent);
													finish();
												}
											})
									.setNegativeButton(
											"��Ҫ����",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													// �ر���ʾ��
												}
											}).show();
						}
					}
				});
			}
		});
		// ���ݲɼ�����
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(getdata.getAirPressure())) {// ��ѹ
					et_press.setText(getdata.getAirPressure());
					press = Float.parseFloat(et_press.getText().toString());
				}
				if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
					et_co.setText(getdata.getCo2());
					co = Float.parseFloat(et_co.getText().toString());
				}
				if (!TextUtils.isEmpty(getdata.getTemperature())) {// �¶�
					et_temp.setText(getdata.getTemperature());
					temp = Float.parseFloat(et_temp.getText().toString());
				}
				if (!TextUtils.isEmpty(getdata.getHumidity())) {// ʪ��
					et_hum.setText(getdata.getHumidity());
					hum = Float.parseFloat(et_hum.getText().toString());
				}
				if (!TextUtils.isEmpty(getdata.getGas())) {// ȼ��
					et_gas.setText(getdata.getGas());
					gas = Float.parseFloat(et_gas.getText().toString());
				}
				if (!TextUtils.isEmpty(getdata.getIllumination())) {// ����
					et_ill.setText(getdata.getIllumination());
					ill = Float.parseFloat(et_ill.getText().toString());
				}
				if (!TextUtils.isEmpty(getdata.getSmoke())) {// ����
					et_smo.setText(getdata.getSmoke());
					smo = Float.parseFloat(et_smo.getText().toString());
				}
				if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
					et_pm.setText(getdata.getPM25());
					pm = Float.parseFloat(et_pm.getText().toString());
				}
				if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// �������
					if (getdata.getStateHumanInfrared().equals(
							ConstantUtil.CLOSE)) {// �����ȡ��ֵΪClose
						et_per.setText("����");
						per = 1;
					} else {
						et_per.setText("����");
						per = 0;
					}
				}
			}
		});
		// �豸����
		// ����
		tg_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
			}
		});
		// �Ž�
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
				}
			}
		});
		// ����
		tg_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// ���
		tg_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// ������
		tg_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// ���ⷢ��ͨ��
		// ͨ��1
		btn_tongdao_1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// ͨ��2
		btn_tongdao_2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// ͨ��3
		btn_tongdao_3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// �������
		handler.post(timeRunnable);
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ����������
	 * 
	 * @ʱ �䣺����9:06:00
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			temp = Float.valueOf(random.nextInt(40) % (40 - 20 + 1));// �¶�
			hum = Float.valueOf(random.nextInt(40) % (120 - 10 + 1));// ʪ��
			gas = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// ȼ��
			smo = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// ����
			ill = Float.valueOf(random.nextInt(9999) % (9999 - 500 + 1));// ����
			co = Float.valueOf(random.nextInt(40) % (100 - 10 + 1));// Co2
			pm = Float.valueOf(random.nextInt(40) % (100 - 40 + 1));// pm2.5
			press = Float.valueOf(random.nextInt(1800) % (1800 - 10 + 1));// ��ѹ
			DecimalFormat df = new DecimalFormat("0");
			String get_co = df.format(co);
			String get_gas = df.format(gas);
			String get_hum = df.format(hum);
			String get_ill = df.format(ill);
			String get_pm = df.format(pm);
			String get_press = df.format(press);
			String get_smo = df.format(smo);
			String get_temp = df.format(temp);
			et_co.setText(String.valueOf(get_co));
			et_gas.setText(String.valueOf(get_gas));
			et_hum.setText(String.valueOf(get_hum));
			et_ill.setText(String.valueOf(get_ill));
			et_pm.setText(String.valueOf(get_pm));
			et_press.setText(String.valueOf(get_press));
			et_smo.setText(String.valueOf(get_smo));
			et_temp.setText(String.valueOf(get_temp));
			if (Float.valueOf(random.nextInt(10) % (10 - 1 + 1)) > 5) {
				et_per.setText("����");
				per = 1;
			} else {
				et_per.setText("����");
				per = 0;
			}
			handler.postDelayed(timeRunnable, 5000);
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
	 * @��������initview()
	 * 
	 * @�� �ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����9:55:25
	 */
	private void initview() {
		line_intent_link = (LinearLayout) findViewById(R.id.line_intent_link);
		btn_tongdao_1 = (Button) findViewById(R.id.btn_tongdao1);
		btn_tongdao_2 = (Button) findViewById(R.id.btn_tongdao2);
		btn_tongdao_3 = (Button) findViewById(R.id.btn_tongdao3);
		tg_cur = (ToggleButton) findViewById(R.id.tg_cur);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		et_co = (EditText) findViewById(R.id.et_co);
		et_gas = (EditText) findViewById(R.id.et_gas);
		et_hum = (EditText) findViewById(R.id.et_hum);
		et_ill = (EditText) findViewById(R.id.et_ill);
		et_per = (EditText) findViewById(R.id.et_per);
		et_press = (EditText) findViewById(R.id.et_press);
		et_pm = (EditText) findViewById(R.id.et_pm);
		et_smo = (EditText) findViewById(R.id.et_smo);
		et_temp = (EditText) findViewById(R.id.et_temp);
	}
}
