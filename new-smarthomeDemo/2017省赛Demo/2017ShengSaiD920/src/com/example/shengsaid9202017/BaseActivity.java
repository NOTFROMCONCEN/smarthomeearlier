package com.example.shengsaid9202017;

import java.text.DecimalFormat;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @文件名：BaseActivity.java
 * @描述：完成数据采集设备控制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-29
 */
public class BaseActivity extends Fragment {
	static float temp, hum, gas, smo, ill, co, pm, per, press;// 公共float数值
	private Random random = new Random();// 随机数
	private EditText tv_temp;// 温度
	private EditText tv_hum;// 湿度
	private EditText tv_smo;// 烟雾
	private EditText tv_gas;// 燃气
	private EditText tv_ill;// 光照
	private EditText tv_co;// Co2
	private EditText tv_press;// 气压
	private EditText tv_pm;// PM2.5
	private EditText tv_per;// 人体红外
	private ToggleButton tg_cur;// 窗帘
	private ToggleButton tg_warm;// 报警灯
	private ToggleButton tg_lamp1;// 射灯1
	private ToggleButton tg_lamp2;// 射灯2
	private ToggleButton tg_fan;// 风扇
	private ToggleButton tg_door;// 门禁

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		tv_co = (EditText) view.findViewById(R.id.tv_co);
		tv_gas = (EditText) view.findViewById(R.id.tv_gas);
		tv_hum = (EditText) view.findViewById(R.id.tv_hum);
		tv_ill = (EditText) view.findViewById(R.id.tv_ill);
		tv_per = (EditText) view.findViewById(R.id.tv_per);
		tv_pm = (EditText) view.findViewById(R.id.tv_pm);
		tv_press = (EditText) view.findViewById(R.id.tv_press);
		tv_smo = (EditText) view.findViewById(R.id.tv_smo);
		tv_temp = (EditText) view.findViewById(R.id.tv_temp);
		tg_cur = (ToggleButton) view.findViewById(R.id.tg_cur);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_lamp1 = (ToggleButton) view.findViewById(R.id.tg_lamp1);
		tg_lamp2 = (ToggleButton) view.findViewById(R.id.tg_lamp2);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		// 设备控制
		// 窗帘
		tg_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);// 开窗帘
				} else {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);// 关窗帘
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
		// 射灯1
		tg_lamp1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		// 射灯2
		tg_lamp2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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

		// 数据采集
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
							// tv_per.setText(getdata.getAirPressure());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// 二氧化碳
							// tv_co.setText(getdata.getCo2());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							// tv_gas.setText(getdata.getGas());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
							// tv_hum.setText(getdata.getHumidity());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
							// tv_ill.setText(getdata.getIllumination());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							// tv_pm.setText(getdata.getPM25());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
							// tv_smo.setText(getdata.getSmoke());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// 温度
							// tv_temp.setText(getdata.getTemperature());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// 人体红外
							// tv_per.setText(getdata.getStateHumanInfrared());
						}
					}
				});
			}
		});
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：加载随机数
	 * 
	 * @时 间：下午3:49:11
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			temp = Float.valueOf(random.nextInt(40) % (40 - 20));// 温度
			hum = Float.valueOf(random.nextInt(120) % (120 - 10));// 湿度
			gas = Float.valueOf(random.nextInt(100) % (100 - 10));// 燃气
			smo = Float.valueOf(random.nextInt(400) % (400 - 10));// 烟雾
			ill = Float.valueOf(random.nextInt(3000) % (3000 - 1));// 光照
			co = Float.valueOf(random.nextInt(300) % (300 - 10));// Co2
			pm = Float.valueOf(random.nextInt(500) % (500 - 40));// pm2.5
			press = Float.valueOf(random.nextInt(1800) % (1800 - 10));// 气压
			tv_co.setText(String.valueOf(co));
			tv_gas.setText(String.valueOf(gas));
			tv_hum.setText(String.valueOf(hum));
			tv_ill.setText(String.valueOf(ill));
			tv_pm.setText(String.valueOf(pm));
			tv_press.setText(String.valueOf(press));
			tv_smo.setText(String.valueOf(smo));
			tv_temp.setText(String.valueOf(temp));
			if (Float.valueOf(random.nextInt(10) % (10 - 1 + 1)) > 5) {
				tv_per.setText("有人");
				per = 1;
			} else {
				tv_per.setText("无人");
				per = 0;
			}
			handler.postDelayed(timeRunnable, 10);
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