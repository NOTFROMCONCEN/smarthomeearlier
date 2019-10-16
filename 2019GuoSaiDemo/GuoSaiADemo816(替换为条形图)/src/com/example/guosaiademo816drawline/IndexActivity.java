package com.example.guosaiademo816drawline;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/*
 * @�ļ�����IndexActivity.java
 * @���������û���¼�����ҳ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-16
 */
public class IndexActivity extends Activity {
	// ����
	private ArrayAdapter<String> mArrayAdapter;// ������
	private String[] mStringArray;
	private Spinner sp_1, sp_2, sp_3;// spinner�����˵�
	private EditText et_number_get;// �ı���
	private int number_get;
	private CheckBox cb_lvyou_mode;// ����ģʽ
	private CheckBox cb_temp_mode;// �¶�ģʽ
	private CheckBox cb_anfang_mode;// ����ģʽ
	private CheckBox cb_diy_mode;// �Զ���ģʽ
	private boolean lvyou_mode = false, temp_mode = false, anfang_mode = false,
			diy_mode = false;
	int i = 0;

	// �豸����
	private ToggleButton tg_lamp, tg_door, tg_fan, tg_warm;// ����Ž����ȱ�����
	private Button btn_kongtiao, btn_dvd, btn_tv;// ����
	private Button btn_cur_stop, btn_cur_cls, btn_cur_open;// ����

	// ���ݲɼ�
	private TextView tv_tmep, tv_hum, tv_gas, tv_ill, tv_pm, tv_press, tv_smo,
			tv_co, tv_per;// ����������ֵ���ı�
	static float temp, hum, gas, ill, pm, press, smo, co, per;
	// ����
	private boolean web_state = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		handler.post(timeRunnable);
		// ȷ����ǰIP��ַ
		Toast.makeText(IndexActivity.this, "��ǰIPΪ��" + LoginActivity.ip,
				Toast.LENGTH_SHORT).show();
		// ���ݲɼ�
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_tmep = (TextView) findViewById(R.id.tv_temp);
		// ����
		cb_anfang_mode = (CheckBox) findViewById(R.id.cb_anfang_mode);
		cb_diy_mode = (CheckBox) findViewById(R.id.cb_diy_mode);
		cb_lvyou_mode = (CheckBox) findViewById(R.id.cb_lvyou_mode);
		cb_temp_mode = (CheckBox) findViewById(R.id.cb_temp_mode);
		// �豸����
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		btn_cur_cls = (Button) findViewById(R.id.btn_cur_cls);
		btn_cur_open = (Button) findViewById(R.id.btn_cur_open);
		btn_cur_stop = (Button) findViewById(R.id.btn_cur_stop);
		btn_dvd = (Button) findViewById(R.id.btn_dvd);
		btn_kongtiao = (Button) findViewById(R.id.btn_kongtiao);
		btn_tv = (Button) findViewById(R.id.btn_tv);
		// ��������ģʽѡ��ֻ�ܴ���һ��������
		// ����ģʽ
		cb_anfang_mode
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							cb_anfang_mode.setChecked(true);
							cb_diy_mode.setChecked(false);
							cb_lvyou_mode.setChecked(false);
							cb_temp_mode.setChecked(false);
							lvyou_mode = false;// ����ģʽ
							temp_mode = false;// �¶�ģʽ
							anfang_mode = true;// ����ģʽ
							diy_mode = false;// DIYģʽ
						} else {
							anfang_mode = false;// ����ģʽ
						}
					}
				});
		// �Զ���ģʽ
		cb_diy_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					cb_diy_mode.setChecked(true);
					cb_lvyou_mode.setChecked(false);
					cb_temp_mode.setChecked(false);
					lvyou_mode = false;// ����ģʽ
					temp_mode = false;// �¶�ģʽ
					anfang_mode = false;// ����ģʽ
					diy_mode = true;// DIYģʽ
				} else {
					diy_mode = false;// ����ģʽ
				}
			}
		});
		// ����ģʽ
		cb_lvyou_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					cb_lvyou_mode.setChecked(true);
					cb_temp_mode.setChecked(false);
					lvyou_mode = true;// ����ģʽ
					temp_mode = false;// �¶�ģʽ
					anfang_mode = false;// ����ģʽ
					diy_mode = false;// DIYģʽ
					i = 0;
				} else {
					lvyou_mode = false;// ����ģʽ
				}
			}
		});
		// �¶�ģʽ
		cb_temp_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					cb_lvyou_mode.setChecked(false);
					cb_temp_mode.setChecked(true);
					lvyou_mode = false;// ����ģʽ
					temp_mode = true;// �¶�ģʽ
					anfang_mode = false;// ����ģʽ
					diy_mode = false;// DIYģʽ
				} else {
					temp_mode = false;// ����ģʽ
				}
			}
		});

		// ����
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		cb_anfang_mode = (CheckBox) findViewById(R.id.cb_anfang_mode);
		cb_diy_mode = (CheckBox) findViewById(R.id.cb_diy_mode);
		cb_lvyou_mode = (CheckBox) findViewById(R.id.cb_lvyou_mode);
		cb_temp_mode = (CheckBox) findViewById(R.id.cb_temp_mode);
		// ��ȡXML�ļ����������-spinner1
		mStringArray = getResources().getStringArray(R.array.temp_ill);
		mArrayAdapter = new AdaHelper(IndexActivity.this, mStringArray);
		sp_1.setAdapter(mArrayAdapter);
		// ��ȡXML�ļ����������-spinner2
		mStringArray = getResources().getStringArray(R.array.big_small);
		mArrayAdapter = new AdaHelper(IndexActivity.this, mStringArray);
		sp_2.setAdapter(mArrayAdapter);
		// ��ȡXML�ļ����������-spinner3
		mStringArray = getResources().getStringArray(R.array.fan_lamp);
		mArrayAdapter = new AdaHelper(IndexActivity.this, mStringArray);
		sp_3.setAdapter(mArrayAdapter);
		// �豸���ƹ���ʵ�ִ���
		// �Ž�
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
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
		tg_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		// ������
		btn_cur_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// ������
		btn_cur_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// ����ͣ
		btn_cur_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// ���ⷢ��
		// DVD
		btn_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// �յ�
		btn_kongtiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// ����
		btn_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// ���ݲɼ�����
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("���ݿ�ʼ�ɼ�");
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// ��ѹ
							tv_press.setText(getdata.getAirPressure());
							press = Float
									.valueOf(tv_press.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// ������̼
							tv_co.setText(getdata.getCo2());
							co = Float.valueOf(tv_co.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// ȼ��
							tv_gas.setText(getdata.getGas());
							gas = Float.valueOf(tv_gas.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// ʪ��
							tv_hum.setText(getdata.getHumidity());
							hum = Float.valueOf(tv_hum.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// ����
							tv_ill.setText(getdata.getIllumination());
							ill = Float.valueOf(tv_ill.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							tv_pm.setText(getdata.getPM25());
							pm = Float.valueOf(tv_pm.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// ����
							tv_smo.setText(getdata.getSmoke());
							smo = Float.valueOf(tv_smo.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// �¶�
							tv_tmep.setText(getdata.getTemperature());
							temp = Float.valueOf(tv_tmep.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							if (getdata.getStateHumanInfrared().equals(
									ConstantUtil.CLOSE)) {
								tv_per.setText("����");
								press = Float.valueOf(0);
							} else {
								tv_per.setText("����");
								press = Float.valueOf(1);
							}
						}
					}
				});
			}
		});
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����������Ӻ����������ģʽ����
	 * 
	 * @����ֵ��true false
	 * 
	 * @ʱ �䣺����7:56:12
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (anfang_mode) {
				// ����ģʽ
				// ��������ģʽ�������������Ӧ�����ˣ��򱨾��ƿ������ȫ����
				if (per == 1) {
					// �����⵽����
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// �����ƿ�
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// ��ƿ�
				} else {
					// ����ȫ��
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (lvyou_mode) {
				i++;
				// ����ģʽ
				// ��������ģʽ���ر���ơ��򿪴�������PM2.5����75ʱ�����������ȡ�
				switch (i) {
				case 1:
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// �ر����
					break;
				case 2:
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// �򿪴���
					break;
				default:
					break;
				}
				if (pm > 75) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// �򿪻�����
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (temp_mode) {
				// �¶�ģʽ
				// �����¶�ģʽ���򿪵��ӻ���DVD���յ��������ơ������ȡ���ơ�
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				ControlUtils.control(ConstantUtil.Fan,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}
			if (diy_mode) {
				// �Զ���ģʽ
			}
			if (web_state) {
				// �������ӽ���
				ControlUtils.setUser("bizideal", "123456", LoginActivity.ip);
				SocketClient.getInstance().creatConnect();
				SocketClient.getInstance().login(new LoginCallback() {

					@Override
					public void onEvent(final String web_state) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (web_state.equals("Success")) {
									// ���������ֵΪ��Success������ʾ���������ӳɹ�
									Toast.makeText(IndexActivity.this,
											"���������ӳɹ�", Toast.LENGTH_SHORT)
											.show();
									IndexActivity.this.web_state = false;
									System.out.println("�������ӳɹ�");
								} else {
									// ������ʾ���������ӳɹ�
									Toast.makeText(IndexActivity.this,
											"����������ʧ��", Toast.LENGTH_SHORT)
											.show();
									IndexActivity.this.web_state = true;
									System.out.println("��������ʧ��");
								}
							}
						});
					}
				});
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
