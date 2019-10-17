package com.example.guosaiademo902;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @文件名：IndexActivity.java
 * @描述：完成数据采集、设备控制、联动、模式功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-2
 */
public class IndexActivity extends Activity {
	private boolean web_state = true;// 网络连接
	// 联动
	private ArrayAdapter<String> mArrayAdapter;// 辅助类
	private String[] mStringArray;
	private Spinner sp_1, sp_2, sp_3;// spinner下拉菜单
	private EditText et_number_get;// 文本框
	private int number_get;
	private CheckBox cb_lvyou_mode;// 旅游模式
	private CheckBox cb_temp_mode;// 温度模式
	private CheckBox cb_anfang_mode;// 安防模式
	private CheckBox cb_diy_mode;// 自定义模式
	private int i = 0;
	// 设备控制
	private ToggleButton tg_lamp, tg_door, tg_fan, tg_warm;// 射灯门禁风扇报警灯
	private Button btn_kongtiao, btn_dvd, btn_tv;// 红外
	private Button btn_cur_stop, btn_cur_cls, btn_cur_open;// 窗帘
	// 数据采集
	private TextView tv_temp, tv_hum, tv_gas, tv_ill, tv_pm, tv_press, tv_smo,
			tv_co, tv_per;// 用于设置数值的文本
	static float temp, hum, gas, ill, pm, press, smo, co, per;
	// 随机数
	private Random random = new Random();
	// 跳转到设备连接状态
	private Button btn_systemlink_state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		cb_anfang_mode = (CheckBox) findViewById(R.id.cb_anfang_mode);
		cb_diy_mode = (CheckBox) findViewById(R.id.cb_diy_mode);
		cb_lvyou_mode = (CheckBox) findViewById(R.id.cb_lvyou_mode);
		cb_temp_mode = (CheckBox) findViewById(R.id.cb_temp_mode);
		btn_systemlink_state = (Button) findViewById(R.id.btn_systemlink_state);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
		btn_cur_cls = (Button) findViewById(R.id.btn_cur_cls);
		btn_cur_open = (Button) findViewById(R.id.btn_cur_open);
		btn_cur_stop = (Button) findViewById(R.id.btn_cur_stop);
		btn_dvd = (Button) findViewById(R.id.btn_dvd);
		btn_kongtiao = (Button) findViewById(R.id.btn_kongtiao);
		btn_tv = (Button) findViewById(R.id.btn_tv);
		// 跳转到设备连接状态页面
		btn_systemlink_state.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(IndexActivity.this,
						Line_State_Activity.class);
				startActivity(intent);
			}
		});
		// 从XML文件中取出文字样式
		mArrayAdapter = new AdaHelper(IndexActivity.this, getResources()
				.getStringArray(R.array.big_small));
		sp_2.setAdapter(mArrayAdapter);
		mArrayAdapter = new AdaHelper(IndexActivity.this, getResources()
				.getStringArray(R.array.temp_ill));
		sp_1.setAdapter(mArrayAdapter);
		mArrayAdapter = new AdaHelper(IndexActivity.this, getResources()
				.getStringArray(R.array.lamp_fan));
		sp_3.setAdapter(mArrayAdapter);
		// 联动模式控件设置为只可选中一项
		// 安防模式
		cb_anfang_mode
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							// cb_anfang_mode.setChecked(false);
							cb_diy_mode.setChecked(false);
							cb_lvyou_mode.setChecked(false);
							cb_temp_mode.setChecked(false);
						}
					}
				});
		// 温度模式
		cb_temp_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					cb_lvyou_mode.setChecked(false);
					// cb_temp_mode.setChecked(false);
				}
			}
		});
		// 旅游模式
		cb_lvyou_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					// cb_lvyou_mode.setChecked(false);
					cb_temp_mode.setChecked(false);
				}
			}
		});
		// 自定义模式
		cb_diy_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					// cb_diy_mode.setChecked(false);
					cb_lvyou_mode.setChecked(false);
					cb_temp_mode.setChecked(false);
					if (et_number_get.getText().toString().equals("")) {
						cb_diy_mode.setChecked(false);
						DiyToast.showToast(IndexActivity.this, "请输入数值");
					} else {
						cb_diy_mode.setChecked(true);
					}
				}
			}
		});
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
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
							// tv_press.setText(getdata.getAirPressure());
							// press = tv_press.getText().toString();
						}
						// 下略......
					}
				});
			}
		});
		// 设备控制
		// 红外
		// DVD
		btn_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// 电视
		btn_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// 空调
		btn_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// 窗帘
		// 窗帘关
		btn_cur_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// 窗帘开
		btn_cur_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// 窗帘停
		btn_cur_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：完成联动、模式、网络连接检测功能
	 * 
	 * @时 间：上午9:07:50
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 安防模式
			if (cb_anfang_mode.isChecked()) {
				// 单击安防模式，如果人体红外感应出有人，则报警灯开，射灯全开。
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
			// 温度模式
			if (cb_temp_mode.isChecked()) {
				// 单击温度模式，打开电视机、DVD、空调、报警灯、换气扇、射灯。
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
				tg_warm.setChecked(true);
				tg_fan.setChecked(true);
				tg_lamp.setChecked(true);
			}

			// 旅游模式
			if (cb_lvyou_mode.isChecked()) {
				// 关闭射灯、打开窗帘。当PM2.5大于75时，开启换气扇。
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				tg_lamp.setChecked(true);
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_2,
						ConstantUtil.CLOSE);
				System.out.println(pm);
				System.out.println("激活旅游模式");
				if (pm > 75) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
			// 自定义模式
			if (cb_diy_mode.isChecked()) {
				String spinner_1;
				String spinner_2;
				String spinner_3;
				number_get = Integer
						.valueOf(et_number_get.getText().toString());
				spinner_1 = sp_1.getSelectedItem().toString();
				spinner_2 = sp_2.getSelectedItem().toString();
				spinner_3 = sp_3.getSelectedItem().toString();
				if (spinner_1.equals("温度")) {
					if (spinner_2.equals(">")) {
						if (temp > number_get) {
							if (spinner_3.equals("射灯打开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("风扇打开")) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							cb_diy_mode.setChecked(false);
							DiyToast.showToast(IndexActivity.this, "条件不满足");
						}
					}
					if (spinner_2.equals("<=")) {
						if (temp < number_get) {
							if (spinner_3.equals("射灯打开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("风扇打开")) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							cb_diy_mode.setChecked(false);
							DiyToast.showToast(IndexActivity.this, "条件不满足");
						}
					}
				}
				if (spinner_1.equals("光照")) {
					if (spinner_2.equals(">")) {
						if (ill > number_get) {
							if (spinner_3.equals("射灯打开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("风扇打开")) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							cb_diy_mode.setChecked(false);
							DiyToast.showToast(IndexActivity.this, "条件不满足");
						}
					}
					if (spinner_2.equals("<=")) {
						if (ill < number_get) {
							if (spinner_3.equals("射灯打开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("风扇打开")) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							cb_diy_mode.setChecked(false);
							DiyToast.showToast(IndexActivity.this, "条件不满足");
						}
					}
				}
			}
			// 网络连接
			if (web_state) {
				ControlUtils.setUser("bizideal", "123456",
						LoginActivity.ip_number);
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
									DiyToast.showToast(IndexActivity.this,
											"网络连接成功");
									IndexActivity.this.web_state = false;
								} else {
									// DiyToast.showToast(IndexActivity.this,
									// "网络连接失败");
								}
							}
						});
					}
				});
			}
			// 随机数更新
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
