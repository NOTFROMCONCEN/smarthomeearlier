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
 * @文件名：IndexActivity.java
 * @描述：对用户登录后的主页面
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-16
 */
public class IndexActivity extends Activity {
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
	private boolean lvyou_mode = false, temp_mode = false, anfang_mode = false,
			diy_mode = false;
	int i = 0;

	// 设备控制
	private ToggleButton tg_lamp, tg_door, tg_fan, tg_warm;// 射灯门禁风扇报警灯
	private Button btn_kongtiao, btn_dvd, btn_tv;// 红外
	private Button btn_cur_stop, btn_cur_cls, btn_cur_open;// 窗帘

	// 数据采集
	private TextView tv_tmep, tv_hum, tv_gas, tv_ill, tv_pm, tv_press, tv_smo,
			tv_co, tv_per;// 用于设置数值的文本
	static float temp, hum, gas, ill, pm, press, smo, co, per;
	// 其他
	private boolean web_state = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		handler.post(timeRunnable);
		// 确定当前IP地址
		Toast.makeText(IndexActivity.this, "当前IP为：" + LoginActivity.ip,
				Toast.LENGTH_SHORT).show();
		// 数据采集
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_tmep = (TextView) findViewById(R.id.tv_temp);
		// 联动
		cb_anfang_mode = (CheckBox) findViewById(R.id.cb_anfang_mode);
		cb_diy_mode = (CheckBox) findViewById(R.id.cb_diy_mode);
		cb_lvyou_mode = (CheckBox) findViewById(R.id.cb_lvyou_mode);
		cb_temp_mode = (CheckBox) findViewById(R.id.cb_temp_mode);
		// 设备控制
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
		// 四种联动模式选择只能存在一个被激活
		// 安防模式
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
							lvyou_mode = false;// 旅游模式
							temp_mode = false;// 温度模式
							anfang_mode = true;// 安防模式
							diy_mode = false;// DIY模式
						} else {
							anfang_mode = false;// 安防模式
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
					cb_diy_mode.setChecked(true);
					cb_lvyou_mode.setChecked(false);
					cb_temp_mode.setChecked(false);
					lvyou_mode = false;// 旅游模式
					temp_mode = false;// 温度模式
					anfang_mode = false;// 安防模式
					diy_mode = true;// DIY模式
				} else {
					diy_mode = false;// 安防模式
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
					cb_lvyou_mode.setChecked(true);
					cb_temp_mode.setChecked(false);
					lvyou_mode = true;// 旅游模式
					temp_mode = false;// 温度模式
					anfang_mode = false;// 安防模式
					diy_mode = false;// DIY模式
					i = 0;
				} else {
					lvyou_mode = false;// 安防模式
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
					cb_temp_mode.setChecked(true);
					lvyou_mode = false;// 旅游模式
					temp_mode = true;// 温度模式
					anfang_mode = false;// 安防模式
					diy_mode = false;// DIY模式
				} else {
					temp_mode = false;// 安防模式
				}
			}
		});

		// 联动
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		cb_anfang_mode = (CheckBox) findViewById(R.id.cb_anfang_mode);
		cb_diy_mode = (CheckBox) findViewById(R.id.cb_diy_mode);
		cb_lvyou_mode = (CheckBox) findViewById(R.id.cb_lvyou_mode);
		cb_temp_mode = (CheckBox) findViewById(R.id.cb_temp_mode);
		// 获取XML文件里面的内容-spinner1
		mStringArray = getResources().getStringArray(R.array.temp_ill);
		mArrayAdapter = new AdaHelper(IndexActivity.this, mStringArray);
		sp_1.setAdapter(mArrayAdapter);
		// 获取XML文件里面的内容-spinner2
		mStringArray = getResources().getStringArray(R.array.big_small);
		mArrayAdapter = new AdaHelper(IndexActivity.this, mStringArray);
		sp_2.setAdapter(mArrayAdapter);
		// 获取XML文件里面的内容-spinner3
		mStringArray = getResources().getStringArray(R.array.fan_lamp);
		mArrayAdapter = new AdaHelper(IndexActivity.this, mStringArray);
		sp_3.setAdapter(mArrayAdapter);
		// 设备控制功能实现代码
		// 门禁
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
		// 风扇
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
		// 射灯
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
		// 报警灯
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
		btn_cur_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// 红外发射
		// DVD
		btn_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// 空调
		btn_kongtiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// 电视
		btn_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// 数据采集进程
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						System.out.println("数据开始采集");
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
							tv_press.setText(getdata.getAirPressure());
							press = Float
									.valueOf(tv_press.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// 二氧化碳
							tv_co.setText(getdata.getCo2());
							co = Float.valueOf(tv_co.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							tv_gas.setText(getdata.getGas());
							gas = Float.valueOf(tv_gas.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
							tv_hum.setText(getdata.getHumidity());
							hum = Float.valueOf(tv_hum.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
							tv_ill.setText(getdata.getIllumination());
							ill = Float.valueOf(tv_ill.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							tv_pm.setText(getdata.getPM25());
							pm = Float.valueOf(tv_pm.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
							tv_smo.setText(getdata.getSmoke());
							smo = Float.valueOf(tv_smo.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// 温度
							tv_tmep.setText(getdata.getTemperature());
							temp = Float.valueOf(tv_tmep.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							if (getdata.getStateHumanInfrared().equals(
									ConstantUtil.CLOSE)) {
								tv_per.setText("无人");
								press = Float.valueOf(0);
							} else {
								tv_per.setText("有人");
								press = Float.valueOf(1);
							}
						}
					}
				});
			}
		});
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：检测网络连接和完成联动、模式功能
	 * 
	 * @返回值：true false
	 * 
	 * @时 间：下午7:56:12
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (anfang_mode) {
				// 安防模式
				// 单击安防模式，如果人体红外感应出有人，则报警灯开，射灯全开。
				if (per == 1) {
					// 如果检测到有人
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 警报灯开
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 射灯开
				} else {
					// 否则全关
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (lvyou_mode) {
				i++;
				// 旅游模式
				// 单击旅游模式，关闭射灯、打开窗帘。当PM2.5大于75时，开启换气扇。
				switch (i) {
				case 1:
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// 关闭射灯
					break;
				case 2:
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 打开窗帘
					break;
				default:
					break;
				}
				if (pm > 75) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 打开换气扇
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (temp_mode) {
				// 温度模式
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
			}
			if (diy_mode) {
				// 自定义模式
			}
			if (web_state) {
				// 网络连接进程
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
									// 如果返回数值为“Success”，提示服务器连接成功
									Toast.makeText(IndexActivity.this,
											"服务器连接成功", Toast.LENGTH_SHORT)
											.show();
									IndexActivity.this.web_state = false;
									System.out.println("网络连接成功");
								} else {
									// 否则提示服务器连接成功
									Toast.makeText(IndexActivity.this,
											"服务器连接失败", Toast.LENGTH_SHORT)
											.show();
									IndexActivity.this.web_state = true;
									System.out.println("网络连接失败");
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
