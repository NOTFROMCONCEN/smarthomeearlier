package com.example.guosaihdemo927;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @文件名：IndexActivity.java
 * @描述：登录后主页，完成数据采集、设备控制、联动、绘图
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-27
 */
public class IndexActivity extends Activity {
	// 数据采集
	private TextView tv_temp;// 温度
	private TextView tv_hum;// 湿度
	private TextView tv_ill;// 光照
	private TextView tv_smo;// 烟雾
	private TextView tv_co;// Co2
	private TextView tv_press;// 气压
	private TextView tv_gas;// 燃气
	private TextView tv_pm;// PM2.5
	private TextView tv_per;// 人体红外
	// 设备控制
	private ToggleButton tg_warm;// 报警灯
	private ToggleButton tg_lamp;// 射灯
	private ToggleButton tg_fan;// 风扇
	private ToggleButton tg_cur;// 窗帘
	private ToggleButton tg_door;// 门禁
	// 红外发射
	private Button btn_send;
	private EditText et_send;
	public static float temp, hum, ill, smo, co, press, gas, pm, per;// float
	private Random random = new Random();// 随机数
	// 模式
	private RadioButton ra_fangda_Mode;// 防盗模式
	private RadioButton ra_day_Mode;// 白天模式
	private RadioButton ra_lijia_Mode;// 离家模式
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		initView();// 绑定控件
		// 激活进程
		handler.post(timeRunnable);
		// 数据采集
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {
							tv_press.setText(getdata.getAirPressure());
							press = Integer.valueOf(tv_press.getText()
									.toString());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {
							tv_co.setText(getdata.getCo2());
							co = Integer.valueOf(tv_co.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {
							tv_gas.setText(getdata.getGas());
							gas = Integer.valueOf(tv_gas.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {
							tv_hum.setText(getdata.getHumidity());
							hum = Integer.valueOf(tv_hum.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {
							tv_ill.setText(getdata.getIllumination());
							ill = Integer.valueOf(tv_ill.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {
							tv_pm.setText(getdata.getPM25());
							pm = Integer.valueOf(tv_pm.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {
							tv_smo.setText(getdata.getSmoke());
							smo = Integer.valueOf(tv_smo.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {
							tv_temp.setText(getdata.getTemperature());
							temp = Integer
									.valueOf(tv_temp.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							if (getdata.getStateHumanInfrared().toString()
									.equals(ConstantUtil.CLOSE)) {
								tv_per.setText("无人");
								per = 0;
							} else {
								tv_per.setText("有人");
								per = 1;
							}
						}
					}
				});
			}
		});
		// 网络连接
		ControlUtils.setUser("bizideal", "123456", "17.1.10.2");
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String arg0) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (arg0.equals("Success")) {
							DiyToast.showToast(getApplicationContext(),
									"网络连接成功");
						} else {
							DiyToast.showToast(getApplicationContext(),
									"网络连接失败");
						}
					}
				});
			}
		});
		// 设备控制
		// 窗帘
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
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
			}
		});
		// 门禁
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
		// 报警灯
		tg_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		// 射灯
		tg_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		// 风扇
		tg_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		// 红外发射
		btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_send.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入数值");
				} else {
					DiyToast.showToast(getApplicationContext(), "已发送："
							+ et_send.getText().toString());
					if (et_send.getText().toString().equals("1")) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
					if (et_send.getText().toString().equals("2")) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
					if (et_send.getText().toString().equals("3")) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					}
				}
			}
		});
		// 模式功能“只能选择一个"效果
		// 白天模式
		ra_day_Mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// ra_day_Mode.setChecked(false);
					ra_fangda_Mode.setChecked(false);
					ra_lijia_Mode.setChecked(false);
				}
			}
		});
		// 防盗模式
		ra_fangda_Mode
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							ra_day_Mode.setChecked(false);
							// ra_fangda_Mode.setChecked(false);
							ra_lijia_Mode.setChecked(false);
						}
					}
				});
		// 离家模式
		ra_lijia_Mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ra_day_Mode.setChecked(false);
					ra_fangda_Mode.setChecked(false);
					// ra_lijia_Mode.setChecked(false);
					number = 0;
				}
			}
		});

	}

	private void initView() {
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		btn_send = (Button) findViewById(R.id.btn_sned);
		et_send = (EditText) findViewById(R.id.et_send);
		tg_cur = (ToggleButton) findViewById(R.id.tg_cur);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		ra_day_Mode = (RadioButton) findViewById(R.id.ra_day_mode);
		ra_fangda_Mode = (RadioButton) findViewById(R.id.ra_fangdao_mode);
		ra_lijia_Mode = (RadioButton) findViewById(R.id.ra_lijia_mode);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新随机数、模式、绘图传输数据
	 * 
	 * @时 间：上午8:53:18
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			/**
			 * 模式
			 */
			if (ra_day_Mode.isChecked()) {
				// 白天模式
				// 光照大于1200Lx时，打开窗帘，光照小于300Lx时，关闭窗帘并射灯全开。
				if (ill > 1200) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
				if (ill < 300) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
			if (ra_fangda_Mode.isChecked()) {
				// 防盗模式
				// 如果人体红外感应出有人，则报警灯开，射灯全开
				if (per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (ra_lijia_Mode.isChecked()) {
				// 离家模式
				// 射灯全关，窗帘开，如果PM2.5大于75则换气扇开
				number++;
				System.out.println("------" + number);
				if (number < 2) {
					System.out.println("开1");
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
				if (number > 2 && number < 4) {
					System.out.println("开2");
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				if (pm > 75) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
			/**
			 * 随机数
			 */
			temp = Float.valueOf(random.nextInt(40) % (40 - 20 + 1));// 温度
			hum = Float.valueOf(random.nextInt(40) % (120 - 10 + 1));// 湿度
			gas = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// 燃气
			smo = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// 烟雾
			ill = Float.valueOf(random.nextInt(9999) % (9999 - 500 + 1));// 光照
			co = Float.valueOf(random.nextInt(40) % (100 - 10 + 1));// Co2
			pm = Float.valueOf(random.nextInt(40) % (100 - 40 + 1));// pm2.5
			press = Float.valueOf(random.nextInt(1800) % (1800 - 10 + 1));// 气压
			DecimalFormat df = new DecimalFormat("0");
			String get_co = df.format(co);
			String get_gas = df.format(gas);
			String get_hum = df.format(hum);
			String get_ill = df.format(ill);
			String get_pm = df.format(pm);
			String get_press = df.format(press);
			String get_smo = df.format(smo);
			String get_temp = df.format(temp);
			tv_co.setText(String.valueOf(get_co));
			tv_gas.setText(String.valueOf(get_gas));
			tv_hum.setText(String.valueOf(get_hum));
			tv_ill.setText(String.valueOf(get_ill));
			tv_pm.setText(String.valueOf(get_pm));
			tv_press.setText(String.valueOf(get_press));
			tv_smo.setText(String.valueOf(get_smo));
			tv_temp.setText(String.valueOf(get_temp));
			if (Float.valueOf(random.nextInt(10) % (10 - 1 + 1)) > 5) {
				tv_per.setText("有人");
				per = 1;
			} else {
				tv_per.setText("无人");
				per = 0;
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
