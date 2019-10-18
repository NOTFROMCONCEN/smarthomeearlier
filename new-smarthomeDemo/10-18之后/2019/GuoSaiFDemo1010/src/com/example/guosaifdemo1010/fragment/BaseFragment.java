package com.example.guosaifdemo1010.fragment;

import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.guosaifdemo1010.R;
import com.example.guosaifdemo1010.toast.DiyToast;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 数据采集、设备控制、绘图
 * @package_name com.example.guosaifdemo1010.fragment
 * @project_name GuoSaiFDemo1010
 * @file_name BaseFragment.java
 */
public class BaseFragment extends Fragment {
	public static float temp, hum, ill, press, pm, co, per, gas, smo;
	private Random random = new Random();
	private RadioButton ra_open, ra_cls, ra_stop;// 开关停按钮
	private View myView01;
	private View myView02;
	private RadioButton ra_tv;// 电视
	private RadioButton ra_dvd;// DVD
	private RadioButton ra_kongtiao;// 空调
	private RadioButton ra_lamp_left;// 射灯左
	private RadioButton ra_lamp_right;// 射灯右
	private RadioButton ra_cur;// 窗帘
	private RadioButton ra_door;// 门禁
	private RadioButton ra_fan;// 风扇
	private TextView tv_left_number;
	private TextView tv_ritght_number;
	private SeekBar sk_1;
	public static float getdata;
	public static String num;
	public static boolean draw_state = false;
	private RelativeLayout re_open_dialog;
	private boolean temp_state = false;// 温度
	private boolean hum_state = false;// 湿度
	private boolean gas_state = false;// 燃气
	private boolean press_state = false;// 气压
	private boolean smo_state = false;// 烟雾
	private boolean ill_state = false;// 光照
	private boolean co_state = false;// Co2
	private boolean pm_state = false;// PM2.5
	private boolean per_state = false;// 人体红外
	private RadioButton ra_temp, ra_hum, ra_ill, ra_gas, ra_press, ra_smo,
			ra_pm, ra_co, ra_per, ra_test_number;
	private boolean MyView1_state = false, MyView2_state = false;
	String string_number = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		re_open_dialog = (RelativeLayout) view
				.findViewById(R.id.re_open_dialog);
		myView01 = (View) view.findViewById(R.id.myview01);
		myView02 = (View) view.findViewById(R.id.myview02);
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
		ra_cur = (RadioButton) view.findViewById(R.id.ra_cur);
		ra_door = (RadioButton) view.findViewById(R.id.ra_door);
		ra_dvd = (RadioButton) view.findViewById(R.id.ra_dvd);
		ra_fan = (RadioButton) view.findViewById(R.id.ra_fan);
		ra_kongtiao = (RadioButton) view.findViewById(R.id.ra_kongtiao);
		ra_lamp_left = (RadioButton) view.findViewById(R.id.ra_lamp_left);
		ra_lamp_right = (RadioButton) view.findViewById(R.id.ra_lamp_right);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
		ra_tv = (RadioButton) view.findViewById(R.id.ra_tv);
		sk_1 = (SeekBar) view.findViewById(R.id.seekBar1);
		tv_left_number = (TextView) view.findViewById(R.id.tv_left_number);
		tv_ritght_number = (TextView) view.findViewById(R.id.tv_right_number);
		re_open_dialog = (RelativeLayout) view
				.findViewById(R.id.re_open_dialog);
		sk_1.setProgress(1);
		sk_1.setMax(100);
		myView01.setVisibility(View.VISIBLE);
		myView02.setVisibility(View.GONE);
		myView01.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				myView01.setVisibility(View.GONE);
				myView02.setVisibility(View.VISIBLE);
				MyView1_state = false;
				MyView2_state = true;
				return false;
			}
		});
		myView02.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				myView01.setVisibility(View.VISIBLE);
				myView02.setVisibility(View.GONE);
				MyView1_state = true;
				MyView2_state = false;
				return false;
			}
		});
		re_open_dialog.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				if (MyView1_state) {
					myView01.setVisibility(View.GONE);
					myView02.setVisibility(View.VISIBLE);
				}
				if (MyView2_state) {
					myView01.setVisibility(View.VISIBLE);
					myView02.setVisibility(View.GONE);
				}
				return false;
			}
		});
		myView01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				radiobutton_state();
			}
		});
		myView02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				radiobutton_state();
			}
		});
		re_open_dialog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				radiobutton_state();
			}
		});
		// 开
		ra_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 电视
				if (ra_tv.isChecked()) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
				// 空调
				if (ra_kongtiao.isChecked()) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
				// DVD
				if (ra_dvd.isChecked()) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
				// 射灯左
				if (ra_lamp_left.isChecked()) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
				// 射灯右
				if (ra_lamp_right.isChecked()) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
				// 窗帘
				if (ra_cur.isChecked()) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
				// 门禁
				if (ra_door.isChecked()) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
				// 风扇
				if (ra_fan.isChecked()) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
		});
		sk_1.setMax(100);
		sk_1.setProgress(1);
		num = "10";
		tv_left_number.setText("0");
		tv_ritght_number.setText("100");
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					tv_left_number.setText("100");
					tv_ritght_number.setText("200");
					sk_1.setProgress(1);
				}
				if (seekBar.getProgress() == 0) {
					tv_left_number.setText("0");
					tv_ritght_number.setText("100");
					sk_1.setProgress(1);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				DiyToast.showToast(getActivity(),
						String.valueOf(seekBar.getProgress()));
				num = String.valueOf(seekBar.getProgress());
			}
		});
		// 关
		ra_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 电视
				if (ra_tv.isChecked()) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
				// 空调
				if (ra_kongtiao.isChecked()) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
				// DVD
				if (ra_dvd.isChecked()) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
				// 射灯左
				if (ra_lamp_left.isChecked()) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				// 射灯右
				if (ra_lamp_right.isChecked()) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				// 窗帘
				if (ra_cur.isChecked()) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
				// 门禁
				if (ra_door.isChecked()) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				// 风扇
				if (ra_fan.isChecked()) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// 停
		ra_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 窗帘
				if (ra_cur.isChecked()) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
			}
		});
		// 激活进程
		sqliteupdata.post(timeRunnable);
		/**
		 * 数据采集
		 */
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// 温度
							temp = Float.valueOf(getdata.getTemperature());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
							hum = Float.valueOf(getdata.getHumidity());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							gas = Float.valueOf(getdata.getGas());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
							ill = Float.valueOf(getdata.getIllumination());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
							smo = Float.valueOf(getdata.getSmoke());
						}
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
							press = Float.valueOf(getdata.getAirPressure());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							co = Float.valueOf(getdata.getCo2());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							pm = Float.valueOf(getdata.getPM25());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// 人体红外
							if (getdata.getStateHumanInfrared().toString()
									.equals(ConstantUtil.CLOSE)) {
								per = 0;
							} else {
								per = 1;
							}
						}
					}
				});
			}
		});
		return view;
	}

	private void radiobutton_state() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				getActivity());
		LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
		final View view = layoutInflater.inflate(R.layout.radio_state, null);
		builder.setView(view);
		final RadioButton ra_temp_dialog = (RadioButton) view
				.findViewById(R.id.ra_temp_dialog);
		final RadioButton ra_hum_dialog = (RadioButton) view
				.findViewById(R.id.ra_hum_dialog);
		final RadioButton ra_ill_dialog = (RadioButton) view
				.findViewById(R.id.ra_ill_dialog);
		final RadioButton ra_gas_dialog = (RadioButton) view
				.findViewById(R.id.ra_gas_dialog);
		final RadioButton ra_press_dialog = (RadioButton) view
				.findViewById(R.id.ra_press_dialog);
		final RadioButton ra_smo_dialog = (RadioButton) view
				.findViewById(R.id.ra_smo_dialog);
		final RadioButton ra_pm_dialog = (RadioButton) view
				.findViewById(R.id.ra_pm_dialog);
		final RadioButton ra_co_dialog = (RadioButton) view
				.findViewById(R.id.ra_co_dialog);
		final RadioButton ra_per_dialog = (RadioButton) view
				.findViewById(R.id.ra_per_dialog);
		ra_temp_dialog.setChecked(true);
		if (temp_state) {
			ra_temp_dialog.setChecked(true);
		}
		if (hum_state) {
			ra_hum_dialog.setChecked(true);
		}
		if (ill_state) {
			ra_ill_dialog.setChecked(true);
		}
		if (gas_state) {
			ra_gas_dialog.setChecked(true);
		}
		if (press_state) {
			ra_press_dialog.setChecked(true);
		}
		if (smo_state) {
			ra_smo_dialog.setChecked(true);
		}
		if (pm_state) {
			ra_pm_dialog.setChecked(true);
		}
		if (co_state) {
			ra_co_dialog.setChecked(true);
		}
		if (per_state) {
			ra_per_dialog.setChecked(true);
		}
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (ra_co_dialog.isChecked()) {
					temp_state = false;
					hum_state = false;
					smo_state = false;
					gas_state = false;
					ill_state = false;
					pm_state = false;
					co_state = true;
					per_state = false;
					press_state = false;
				}
				if (ra_temp_dialog.isChecked()) {
					temp_state = true;
					hum_state = false;
					smo_state = false;
					gas_state = false;
					ill_state = false;
					pm_state = false;
					co_state = false;
					per_state = false;
					press_state = false;

				}
				if (ra_hum_dialog.isChecked()) {
					temp_state = false;
					hum_state = true;
					smo_state = false;
					gas_state = false;
					ill_state = false;
					pm_state = false;
					co_state = false;
					per_state = false;
					press_state = false;

				}
				if (ra_gas_dialog.isChecked()) {
					temp_state = false;
					hum_state = false;
					smo_state = false;
					gas_state = true;
					ill_state = false;
					pm_state = false;
					co_state = false;
					per_state = false;
					press_state = false;

				}
				if (ra_ill_dialog.isChecked()) {
					temp_state = false;
					hum_state = false;
					smo_state = false;
					gas_state = false;
					ill_state = true;
					pm_state = false;
					co_state = false;
					per_state = false;
					press_state = false;
				}
				if (ra_per_dialog.isChecked()) {
					temp_state = false;
					hum_state = false;
					smo_state = false;
					gas_state = false;
					ill_state = false;
					pm_state = false;
					co_state = false;
					per_state = true;
					press_state = false;
				}
				if (ra_pm_dialog.isChecked()) {
					temp_state = false;
					hum_state = false;
					smo_state = false;
					gas_state = false;
					ill_state = false;
					pm_state = true;
					co_state = false;
					per_state = false;
					press_state = false;
				}
				if (ra_press_dialog.isChecked()) {
					temp_state = false;
					hum_state = false;
					smo_state = false;
					gas_state = false;
					ill_state = false;
					pm_state = false;
					co_state = false;
					per_state = false;
					press_state = true;

				}
				if (ra_smo_dialog.isChecked()) {
					temp_state = false;
					hum_state = false;
					smo_state = true;
					gas_state = false;
					ill_state = false;
					pm_state = false;
					co_state = false;
					per_state = false;
					press_state = false;
				}
			}
		});
		builder.show();
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：绘图传输数据、绘图其他功能
	 * 
	 * @时 间：上午9:12:24
	 */
	Handler sqliteupdata = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (temp_state) {
				getdata = temp;
			}
			if (hum_state) {
				getdata = hum;
			}
			if (ill_state) {
				getdata = ill;
			}
			if (gas_state) {
				getdata = gas;
			}
			if (press_state) {
				getdata = press;
			}
			if (smo_state) {
				getdata = smo;
			}
			if (pm_state) {
				getdata = pm;
			}
			if (co_state) {
				getdata = co;
			}
			if (per_state) {
				getdata = per;
			}
			temp = Float.valueOf(random.nextInt(40) % (40 - 20 + 1));// 温度
			hum = Float.valueOf(random.nextInt(40) % (120 - 10 + 1));// 湿度
			gas = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// 燃气
			smo = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// 烟雾
			ill = Float.valueOf(random.nextInt(9999) % (9999 - 500 + 1));// 光照
			co = Float.valueOf(random.nextInt(40) % (100 - 10 + 1));// Co2
			pm = Float.valueOf(random.nextInt(40) % (100 - 40 + 1));// pm2.5
			press = Float.valueOf(random.nextInt(1800) % (1800 - 10 + 1));// 气压
			if (Float.valueOf(random.nextInt(10) % (10 - 1 + 1)) > 5) {
				per = 1;
			} else {
				per = 0;
			}
			sqliteupdata.postDelayed(timeRunnable, 1000);
		}
	};

	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = sqliteupdata.obtainMessage();
			sqliteupdata.sendMessage(msg);
		}
	};
}
