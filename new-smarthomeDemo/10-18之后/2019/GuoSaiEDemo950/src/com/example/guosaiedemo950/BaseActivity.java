package com.example.guosaiedemo950;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @文件名：BaseActivity.java
 * @描述：数据采集、设备控制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-25
 */
public class BaseActivity extends Activity {
	private boolean frist_open_activity_webserver = true;// 第一次打开此互动时联网测试
	// 随机数
	private Random random = new Random();
	// float
	public static float temp, hum, gas, ill, press, pm, co, per, smo;

	// 布局
	private LinearLayout line_intent;

	// 数据采集
	private EditText tv_temp;// 温度
	private EditText tv_hum;// 湿度
	private EditText tv_smo;// 烟雾
	private EditText tv_gas;// 燃气
	private EditText tv_ill;// 光照
	private EditText tv_press;// 气压
	private EditText tv_co;// Co2
	private EditText tv_pm;// PM2.5
	private EditText tv_per;// 人体红外
	// 设备控制
	private ToggleButton tg_lamp;// 射灯
	private ToggleButton tg_cur;// 窗帘
	private ToggleButton tg_fan;// 风扇
	private ToggleButton tg_warm;// 报警灯
	private ToggleButton tg_door;// 门禁
	// 红外发射
	private Button btn_tomgdao_1;// 通道1
	private Button btn_tomgdao_2;// 通道2
	private Button btn_tomgdao_3;// 通道3

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		line_intent = (LinearLayout) findViewById(R.id.line_intent);
		tv_co = (EditText) findViewById(R.id.et_co);
		tv_gas = (EditText) findViewById(R.id.et_gas);
		tv_hum = (EditText) findViewById(R.id.et_hum);
		tv_ill = (EditText) findViewById(R.id.et_ill);
		tv_per = (EditText) findViewById(R.id.et_per);
		tv_pm = (EditText) findViewById(R.id.et_pm);
		tv_press = (EditText) findViewById(R.id.et_press);
		tv_smo = (EditText) findViewById(R.id.et_smo);
		tv_temp = (EditText) findViewById(R.id.et_temp);
		btn_tomgdao_1 = (Button) findViewById(R.id.btn_tongdao_1);
		btn_tomgdao_2 = (Button) findViewById(R.id.btn_tongdao_2);
		btn_tomgdao_3 = (Button) findViewById(R.id.btn_tongdao_3);
		tg_cur = (ToggleButton) findViewById(R.id.tg_cur);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		// 跳转
		line_intent.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						LinkActivity.class);
				startActivity(intent);
				DiyToast.showTaost(getApplicationContext(), "长按");
				return false;
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
		// 红外发射设置
		// 通道1
		btn_tomgdao_1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// 通道2
		btn_tomgdao_2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// 通道3
		btn_tomgdao_3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// 激活进程
		handler.post(timeRunnable);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：联网、随机数
	 * 
	 * @时 间：上午8:41:33
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (frist_open_activity_webserver) {
				frist_open_activity_webserver = false;
				ControlUtils.setUser("bizideal", "123456",
						MainActivity.IP_NUMBER);
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
									DiyToast.showTaost(getApplicationContext(),
											"小贴士提示：网络连接成功");
									frist_open_activity_webserver = false;
								} else {
									new AlertDialog.Builder(BaseActivity.this)
											.setMessage(
													"\n" + "网络连接失败，是否返回登录界面"
															+ "\n")
											.setNegativeButton(
													"取消",
													new DialogInterface.OnClickListener() {

														@Override
														public void onClick(
																DialogInterface dialog,
																int which) {
															// TODO
															// Auto-generated
															// method stub
															frist_open_activity_webserver = false;
														}
													})
											.setPositiveButton(
													"返回",
													new DialogInterface.OnClickListener() {

														@Override
														public void onClick(
																DialogInterface dialog,
																int which) {
															// TODO
															// Auto-generated
															// method stub
															Intent intent = new Intent(
																	getApplicationContext(),
																	MainActivity.class);
															startActivity(intent);
															finish();
														}
													}).show();
								}
							}
						});
					}
				});
			}
			// 随机数刷新部分
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
