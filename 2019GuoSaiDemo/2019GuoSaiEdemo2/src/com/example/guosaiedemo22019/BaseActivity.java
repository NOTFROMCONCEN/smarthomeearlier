package com.example.guosaiedemo22019;

import org.json.JSONException;

import lib.Json_data;
import lib.SocketThread;
import lib.Updata_activity;
import lib.json_dispose;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

public class BaseActivity extends Activity {
	// 定义控件
	private EditText et_temp, et_hum, et_smo, et_gas, et_ill, et_press, et_co,
			et_pm, et_per;
	private ToggleButton tg_lamp, tg_cur, tg_fan, tg_warm, tg_door;
	private Button btn_tongdao1, btn_tongdao2, btn_tongdao3;
	private int count;
	private Thread UpdataThread;
	private boolean net_state = false;
	private LinearLayout line_intent;
	static float temp, hum, smo, gas, ill, press, co, pm, per;
	static json_dispose Js = new json_dispose();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		// 绑定控件
		et_co = (EditText) findViewById(R.id.et_co2);
		et_gas = (EditText) findViewById(R.id.et_gas);
		et_hum = (EditText) findViewById(R.id.et_hum);
		et_ill = (EditText) findViewById(R.id.et_ill);
		et_per = (EditText) findViewById(R.id.et_per);
		et_pm = (EditText) findViewById(R.id.et_pm);
		et_press = (EditText) findViewById(R.id.et_press);
		et_smo = (EditText) findViewById(R.id.et_smo);
		et_temp = (EditText) findViewById(R.id.et_temp);
		tg_cur = (ToggleButton) findViewById(R.id.tg_cur);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		line_intent = (LinearLayout) findViewById(R.id.line_intent);
		btn_tongdao1 = (Button) findViewById(R.id.btn_tongdao1);
		btn_tongdao2 = (Button) findViewById(R.id.btn_tongdao2);
		btn_tongdao3 = (Button) findViewById(R.id.btn_tongdao3);
		count = 1;
		network();
		updata();
		handler.post(timeRunnable);
		line_intent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(BaseActivity.this,
						ModeActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	// 监测网络连接功能实现
	private void network() {
		SocketThread.mHandlerSocketState = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				Bundle b = msg.getData();
				if (count == 1) {
					// 判断Bundle得到的值是否为error，如果是，激活下面的进程
					if (b.getString("SocketThread_State") == "error") {
						net_state = true;
						Log.e("网络连接检测", "网络连接失败");
					} else {
						// 如果Bundle得到的值不为error，则提示网络连接成功，但不排除BUG
						count = 0;
						Toast.makeText(BaseActivity.this, "小贴士：网络连接成功",
								Toast.LENGTH_SHORT).show();
						net_state = false;
					}
				}
			}
		};
	}

	// 数据采集进程实现
	private void updata() {
		UpdataThread = new Thread(new Updata_activity());
		UpdataThread.start();
		Updata_activity.updatahandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				try {
					Js.receive();
					et_co.setText(Js.receive_data.get(Json_data.Co2).toString());
					et_gas.setText(Js.receive_data.get(Json_data.Gas)
							.toString());
					et_hum.setText(Js.receive_data.get(Json_data.Humidity)
							.toString());
					et_ill.setText(Js.receive_data.get(Json_data.Illumination)
							.toString());
					et_pm.setText(Js.receive_data.get(Json_data.PM25)
							.toString());
					et_smo.setText(Js.receive_data.get(Json_data.Smoke)
							.toString());
					et_temp.setText(Js.receive_data.get(Json_data.Temp)
							.toString());
					et_press.setText(Js.receive_data.get(Json_data.AirPressure)
							.toString());
					if (Js.receive_data.get(Json_data.StateHumanInfrared)
							.toString().equals("1")) {
						et_per.setText("有人");
					} else {
						et_per.setText("无人");
					}
				} catch (JSONException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		};
	}

	// Dialog弹出提示框实现代码
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (net_state) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						BaseActivity.this);
				builder.setTitle("网络连接");
				builder.setMessage("网络连接失败，是否返回登录界面");
				// 点击返回登录，退出程序并打开登录界面
				builder.setPositiveButton("返回登录",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(BaseActivity.this,
										MainActivity.class);
								startActivity(intent);
								count = 0;
								net_state = false;
								finish();
							}
						});
				// 点击取消，等待下次执行
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
							}
						});
				builder.show();
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
}
