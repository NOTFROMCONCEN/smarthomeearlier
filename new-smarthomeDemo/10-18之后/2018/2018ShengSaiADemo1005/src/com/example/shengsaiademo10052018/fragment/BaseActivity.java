package com.example.shengsaiademo10052018.fragment;

import java.text.DecimalFormat;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.shengsaiademo10052018.R;
import com.example.shengsaiademo10052018.toast.DiyToast;

/*
 * @文件名：BaseActivity.java
 * @描述：数据采集、设备控制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-5
 */
public class BaseActivity extends Fragment {
	private Button btn_send;// 发射
	private EditText et_sned;// 发射
	private TextView tv_temp;// 温度
	private TextView tv_hum;// 湿度
	private TextView tv_co;// Co2
	private TextView tv_ill;// 光照
	private TextView tv_smo;// 烟雾探测
	private TextView tv_press;// 气压
	private TextView tv_pm;// PM2.5
	private TextView tv_gas;// 燃气
	private TextView tv_per;// 人体红外
	private ToggleButton tg_warm;// 报警灯
	private ToggleButton tg_fan;// 风扇
	private ToggleButton tg_cur;// 窗帘
	private ToggleButton tg_door;// 门禁
	private ToggleButton tg_lamp;// 射灯
	public static float temp, hum, ill, co, smo, press, pm, gas, per;
	private Random random = new Random();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		initView(view);
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
							tv_temp.setText(getdata.getTemperature());
							temp = Float.valueOf(getdata.getTemperature());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
							tv_hum.setText(getdata.getHumidity());
							hum = Float.valueOf(getdata.getHumidity());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							tv_gas.setText(getdata.getGas());
							gas = Float.valueOf(getdata.getGas());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
							tv_ill.setText(getdata.getIllumination());
							ill = Float.valueOf(getdata.getIllumination());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
							tv_smo.setText(getdata.getSmoke());
							smo = Float.valueOf(getdata.getSmoke());
						}
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
							tv_press.setText(getdata.getAirPressure());
							press = Float.valueOf(getdata.getAirPressure());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							tv_co.setText(getdata.getCo2());
							co = Float.valueOf(getdata.getCo2());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							tv_pm.setText(getdata.getPM25());
							pm = Float.valueOf(getdata.getPM25());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// 人体红外
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
		/**
		 * 设备控制
		 */
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
		/**
		 * 红外发射
		 */
		btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_sned.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
				} else {
					if (Integer.valueOf(et_sned.getText().toString()) > 3) {
						DiyToast.showToast(getActivity(), "数值溢出");
					}
					if (Integer.valueOf(et_sned.getText().toString()) < 0) {
						DiyToast.showToast(getActivity(), "数值溢出");
					}
					if (et_sned.getText().toString().equals("1")) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
					if (et_sned.getText().toString().equals("2")) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
					if (et_sned.getText().toString().equals("3")) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					}
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午9:41:35
	 */
	private void initView(View view) {
		// TODO Auto-generated method stub
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		tg_cur = (ToggleButton) view.findViewById(R.id.tg_cur);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) view.findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		btn_send = (Button) view.findViewById(R.id.btn_send);
		et_sned = (EditText) view.findViewById(R.id.et_send);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新随机数模拟
	 * 
	 * @时 间：上午9:27:10
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
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

			handler.postDelayed(timeRunnable, 2560);
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
