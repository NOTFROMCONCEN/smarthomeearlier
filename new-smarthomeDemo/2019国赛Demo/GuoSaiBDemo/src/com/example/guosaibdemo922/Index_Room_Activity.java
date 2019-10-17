package com.example.guosaibdemo922;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

public class Index_Room_Activity extends Activity implements OnClickListener {
	private Button btn_room_guanli;// 房间管理
	private Button btn_room_control;// 房间控制
	private Button btn_room_link;// 联动管理
	private Button btn_room_temp;// 温度趋势
	private LinearLayout line_8101;// 8101
	private LinearLayout line_8102;// 8102
	private LinearLayout line_8103;// 8103
	private LinearLayout line_8104;// 8104
	private LinearLayout line_8201;// 8201
	private LinearLayout line_8202;// 8202
	private LinearLayout line_8203;// 8203
	private LinearLayout line_8204;// 8204
	private LinearLayout line_8301;// 8301
	private LinearLayout line_8302;// 8302
	private LinearLayout line_8303;// 8303
	private LinearLayout line_8304;// 8304
	private LinearLayout line_8401;// 8401
	private LinearLayout line_8402;// 8402
	private LinearLayout line_8403;// 8403
	private LinearLayout line_8404;// 8404
	private View view_1;// view1
	private View view_2;// view2
	private View view_3;// view3
	private View view_4;// view4
	// 网络连接状态
	boolean web_state = true;
	// 房间号
	public static String NUMBER_FOR_ROOM;
	// boolean
	private boolean room_guanli = false;// 房间管理
	private boolean room_control = false;// 房间控制
	private boolean room_link = false;// 联动管理
	private boolean room_temp = false;// 温度趋势

	// 数据采集
	static TextView tv_ill;
	static TextView tv_co;
	static TextView tv_temp;
	static TextView tv_pm;
	static TextView tv_hum;
	static TextView tv_gas;
	static TextView tv_smo;
	static TextView tv_per;
	static TextView tv_press;

	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// 随机数
	private Random random = new Random();

	// 数据传输
	public static float temp, hum, ill, smo, per, press, pm, co, gas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		initView();
		get_room_state();
		// 房间控制被点击
		btn_room_control.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setVisibility(View.INVISIBLE);
				view_2.setVisibility(View.VISIBLE);
				view_3.setVisibility(View.INVISIBLE);
				view_4.setVisibility(View.INVISIBLE);
				room_guanli = false;// 房间管理
				room_control = true;// 房间控制
				room_link = false;// 联动管理
				room_temp = false;// 温度趋势
			}
		});
		// 房间管理被点击
		btn_room_guanli.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setVisibility(View.VISIBLE);
				view_2.setVisibility(View.INVISIBLE);
				view_3.setVisibility(View.INVISIBLE);
				view_4.setVisibility(View.INVISIBLE);
				room_guanli = true;// 房间管理
				room_control = false;// 房间控制
				room_link = false;// 联动管理
				room_temp = false;// 温度趋势
			}
		});
		// 联动管理被点击
		btn_room_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setVisibility(View.INVISIBLE);
				view_2.setVisibility(View.INVISIBLE);
				view_3.setVisibility(View.VISIBLE);
				view_4.setVisibility(View.INVISIBLE);
				room_guanli = false;// 房间管理
				room_control = false;// 房间控制
				room_link = true;// 联动管理
				room_temp = false;// 温度趋势
			}
		});
		// 温度趋势被点击
		btn_room_temp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setVisibility(View.INVISIBLE);
				view_2.setVisibility(View.INVISIBLE);
				view_3.setVisibility(View.INVISIBLE);
				view_4.setVisibility(View.VISIBLE);
				room_guanli = false;// 房间管理
				room_control = false;// 房间控制
				room_link = false;// 联动管理
				room_temp = true;// 温度趋势
			}
		});
		// 激活进程
		handler.post(timeRunnable);

	}

	/*
	 * @方法名：initView()
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午8:14:25
	 */
	private void initView() {
		line_8101 = (LinearLayout) findViewById(R.id.line_8101);
		line_8102 = (LinearLayout) findViewById(R.id.line_8102);
		line_8103 = (LinearLayout) findViewById(R.id.line_8103);
		line_8104 = (LinearLayout) findViewById(R.id.line_8104);
		line_8201 = (LinearLayout) findViewById(R.id.line_8201);
		line_8202 = (LinearLayout) findViewById(R.id.line_8202);
		line_8203 = (LinearLayout) findViewById(R.id.line_8203);
		line_8204 = (LinearLayout) findViewById(R.id.line_8204);
		line_8301 = (LinearLayout) findViewById(R.id.line_8301);
		line_8302 = (LinearLayout) findViewById(R.id.line_8302);
		line_8303 = (LinearLayout) findViewById(R.id.line_8303);
		line_8304 = (LinearLayout) findViewById(R.id.line_8304);
		line_8401 = (LinearLayout) findViewById(R.id.line_8401);
		line_8402 = (LinearLayout) findViewById(R.id.line_8402);
		line_8403 = (LinearLayout) findViewById(R.id.line_8403);
		line_8404 = (LinearLayout) findViewById(R.id.line_8404);
		view_1 = (View) findViewById(R.id.view_1);
		view_2 = (View) findViewById(R.id.view_2);
		view_3 = (View) findViewById(R.id.view_3);
		view_4 = (View) findViewById(R.id.view_4);
		view_1.setBackgroundColor(Color.CYAN);
		view_2.setBackgroundColor(Color.CYAN);
		view_3.setBackgroundColor(Color.CYAN);
		view_4.setBackgroundColor(Color.CYAN);
		view_1.setVisibility(View.VISIBLE);
		room_guanli = true;
		view_2.setVisibility(View.INVISIBLE);
		view_3.setVisibility(View.INVISIBLE);
		view_4.setVisibility(View.INVISIBLE);
		btn_room_control = (Button) findViewById(R.id.btn_room_kongzhi);
		btn_room_guanli = (Button) findViewById(R.id.btn_room_guanli);
		btn_room_link = (Button) findViewById(R.id.btn_room_link);
		btn_room_temp = (Button) findViewById(R.id.btn_room_temp);
		line_8101.setOnClickListener(this);
		line_8102.setOnClickListener(this);
		line_8103.setOnClickListener(this);
		line_8104.setOnClickListener(this);
		line_8201.setOnClickListener(this);
		line_8202.setOnClickListener(this);
		line_8203.setOnClickListener(this);
		line_8204.setOnClickListener(this);
		line_8301.setOnClickListener(this);
		line_8302.setOnClickListener(this);
		line_8303.setOnClickListener(this);
		line_8304.setOnClickListener(this);
		line_8401.setOnClickListener(this);
		line_8402.setOnClickListener(this);
		line_8403.setOnClickListener(this);
		line_8404.setOnClickListener(this);

	}

	/*
	 * @方法名：onclick
	 * 
	 * @功 能：响应点击事件
	 * 
	 * @时 间：上午8:33:07
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_8101:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8101";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8101";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8101";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8101";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8102:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8102";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8102";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8102";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8102";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8103:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8103";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8103";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8103";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8103";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8104:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8104";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8104";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8104";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8104";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8201:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8201";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8201";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8201";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8201";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8202:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8202";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8202";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8202";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8202";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8203:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8203";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8203";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8203";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8203";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8204:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8204";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8204";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8204";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8204";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8301:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8301";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8301";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8301";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8301";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8302:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8302";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8302";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8302";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8302";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8303:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8303";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8303";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8303";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8303";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8304:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8304";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8304";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8304";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8304";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8401:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8401";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8401";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8401";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8401";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8402:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8402";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8402";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8402";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8402";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8403:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8403";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8403";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8403";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8403";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8404:
			if (room_guanli) {
				NUMBER_FOR_ROOM = "8404";
				showDialog();
			}
			if (room_control) {
				NUMBER_FOR_ROOM = "8404";
				Intent intent = new Intent(getApplicationContext(),
						RoomColtrol.class);
				startActivity(intent);
			}
			if (room_link) {
				NUMBER_FOR_ROOM = "8404";
				Intent intent = new Intent(getApplicationContext(),
						RoomLink.class);
				startActivity(intent);
			}
			if (room_temp) {
				NUMBER_FOR_ROOM = "8404";
				Intent intent = new Intent(getApplicationContext(),
						RoomTemp.class);
				startActivity(intent);
			}
			break;

		default:
			break;
		}
	}

	/*
	 * @方法名：showDialog
	 * 
	 * @功 能：自定义Dialog完成房间管理
	 * 
	 * @时 间：上午8:33:01
	 */
	private void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				Index_Room_Activity.this);
		builder.setTitle("房间管理");
		LayoutInflater layoutInflater = LayoutInflater
				.from(Index_Room_Activity.this);
		View view = layoutInflater.inflate(R.layout.activity_show_title, null,
				false);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_press = (TextView) view.findViewById(R.id.tv_press);

		tv_co.setText(String.valueOf(co));
		tv_gas.setText(String.valueOf(gas));
		tv_hum.setText(String.valueOf(hum));
		tv_ill.setText(String.valueOf(ill));
		tv_per.setText(String.valueOf(per));
		tv_pm.setText(String.valueOf(pm));
		tv_press.setText(String.valueOf(press));
		tv_smo.setText(String.valueOf(smo));
		tv_temp.setText(String.valueOf(temp));

		builder.setView(view);
		builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		final TextView tv_dialog_room_number = (TextView) view
				.findViewById(R.id.tv_room_number);
		tv_dialog_room_number.setText("房间号：" + NUMBER_FOR_ROOM);
		final Button btn_yiruzhu = (Button) view.findViewById(R.id.btn_yiruzhu);
		final Button btn_weidasao = (Button) view
				.findViewById(R.id.btn_weidasao);
		final Button btn_weiruzhu = (Button) view
				.findViewById(R.id.btn_weiruzhu);
		Index_Room_Activity.this.invalidateOptionsMenu();

		btn_weidasao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("update room" + NUMBER_FOR_ROOM
						+ " set roomstate = 2");
				get_room_state();
			}
		});
		btn_weiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("update room" + NUMBER_FOR_ROOM
						+ " set roomstate = 3");
				get_room_state();
			}
		});
		btn_yiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("update room" + NUMBER_FOR_ROOM
						+ " set roomstate = 1");
				get_room_state();
			}
		});
		builder.show();
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：网络连接
	 * 
	 * @时 间：上午8:58:24
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			temp = Float.valueOf(random.nextInt(40) % (40 - 1));
			hum = Float.valueOf(random.nextInt(40) % (40 - 10));
			ill = Float.valueOf(random.nextInt(40) % (40 - 10));
			co = Float.valueOf(random.nextInt(40) % (40 - 10));
			pm = Float.valueOf(random.nextInt(40) % (40 - 10));
			per = Float.valueOf(random.nextInt(40) % (40 - 10));
			smo = Float.valueOf(random.nextInt(40) % (40 - 10));
			press = Float.valueOf(random.nextInt(40) % (40 - 10));
			gas = Float.valueOf(random.nextInt(40) % (40 - 10));

			if (web_state) {

				ControlUtils.setUser("admin", "123456", MainActivity.IP_NUMBER);
				SocketClient.getInstance().creatConnect();
				SocketClient.getInstance().login(new LoginCallback() {

					@Override
					public void onEvent(final String link_state) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (link_state.equals("Success")) {
									web_state = false;
									DiyToast.showTasot(getApplicationContext(),
											"组网成功");
									web_state = false;
								} else {
									DiyToast.showTasot(getApplicationContext(),
											"组网失败");
								}
							}
						});
					}
				});
			}
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
								tv_press.setText(getdata.getAirPressure());
								press = Float.valueOf(tv_press.getText()
										.toString());
							}
							if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
								tv_co.setText(getdata.getCo2());
								co = Float.valueOf(tv_co.getText().toString());
							}
							if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
								tv_gas.setText(getdata.getGas());
								gas = Float
										.valueOf(tv_gas.getText().toString());
							}
							if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
								tv_hum.setText(getdata.getHumidity());
								hum = Float
										.valueOf(tv_hum.getText().toString());
							}
							if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
								tv_ill.setText(getdata.getIllumination());
								ill = Float
										.valueOf(tv_ill.getText().toString());
							}
							if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
								tv_pm.setText(getdata.getPM25());
								pm = Float.valueOf(tv_pm.getText().toString());
							}
							if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
								tv_smo.setText(getdata.getSmoke());
								smo = Float
										.valueOf(tv_smo.getText().toString());
							}
							if (!TextUtils.isEmpty(getdata.getTemperature())) {// 温度
								tv_temp.setText(getdata.getTemperature());
								temp = Float.valueOf(tv_temp.getText()
										.toString());
							}
							if (!TextUtils.isEmpty(getdata
									.getStateHumanInfrared())) {// 人体红外
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
	 * @方法名：get_room_state
	 * 
	 * @功 能：获取房间状态
	 * 
	 * @时 间：上午9:07:33
	 */
	private void get_room_state() {
		// 程序启动时读取房间状态
		// 810X
		for (int i = 1; i < 5; i++) {
			Cursor cursor = db.rawQuery(
					"select * from room810" + String.valueOf(i), null);
			if (i == 1) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8101.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8101.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8101.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 2) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8102.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8102.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8102.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 3) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8103.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8103.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8103.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 4) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8104.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8104.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8104.setBackgroundColor(Color.GREEN);
				}
			}
		}

		// 820X
		for (int i = 1; i < 5; i++) {
			Cursor cursor = db.rawQuery(
					"select * from room820" + String.valueOf(i), null);
			if (i == 1) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8201.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8201.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8201.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 2) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8202.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8202.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8202.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 3) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8203.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8203.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8203.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 4) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8204.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8204.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8204.setBackgroundColor(Color.GREEN);
				}
			}
		}

		// 830X
		for (int i = 1; i < 5; i++) {
			Cursor cursor = db.rawQuery(
					"select * from room830" + String.valueOf(i), null);
			if (i == 1) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8301.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8301.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8301.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 2) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8302.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8302.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8302.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 3) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8303.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8303.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8303.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 4) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8304.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8304.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8304.setBackgroundColor(Color.GREEN);
				}
			}
		}

		// 840X
		for (int i = 1; i < 5; i++) {
			Cursor cursor = db.rawQuery(
					"select * from room840" + String.valueOf(i), null);
			if (i == 1) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8401.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8401.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8401.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 2) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8402.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8402.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8402.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 3) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8403.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8403.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8403.setBackgroundColor(Color.GREEN);
				}
			}
			if (i == 4) {
				cursor.moveToLast();
				String get_room_state = cursor.getString(cursor
						.getColumnIndex("roomstate"));
				if (get_room_state.equals("1")) {
					line_8404.setBackgroundColor(Color.RED);
				}
				if (get_room_state.equals("2")) {
					line_8404.setBackgroundColor(Color.GRAY);
				}
				if (get_room_state.equals("3")) {
					line_8404.setBackgroundColor(Color.GREEN);
				}
			}
		}
	}
}