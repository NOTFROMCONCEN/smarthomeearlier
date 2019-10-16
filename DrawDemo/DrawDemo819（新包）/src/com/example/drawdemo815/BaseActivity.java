package com.example.drawdemo815;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @文件名：BaseActivity.java
 * @描述：对用户滑动四部分之一，完成数据采集、设备控制功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-14
 */
public class BaseActivity extends Fragment {
	/**
	 * 定义控件
	 */
	private TextView tv_temp;// 温度
	private TextView tv_hum;// 湿度
	private TextView tv_gas;// 燃气
	private TextView tv_press;// 气压
	private TextView tv_smo;// 烟雾
	private TextView tv_ill;// 光照
	private TextView tv_co;// Co2
	private TextView tv_pm;// PM2.5
	private TextView tv_per;// 人体红外
	private ToggleButton tg_lamp;// 射灯
	private ToggleButton tg_fan;// 风扇
	private ToggleButton tg_door;// 门禁
	private ToggleButton tg_tv;// 电视
	private ToggleButton tg_kongtiao;// 空调
	private ToggleButton tg_dvd;// DVD
	private ToggleButton tg_warm;// 报警灯
	private RadioButton ra_open, ra_cls, ra_stop;// 窗帘开、关、停
	static float temp, hum, gas, press, smo, ill, co, pm, per;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		/**
		 * 绑定控件
		 */
		// 绑定文本控件
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		// 窗帘控制
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
		// 绑定开关按钮
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) view.findViewById(R.id.tg_dvd);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_kongtiao = (ToggleButton) view.findViewById(R.id.tg_kongtiao);
		tg_lamp = (ToggleButton) view.findViewById(R.id.tg_lamp);
		tg_tv = (ToggleButton) view.findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		// 激活“数据采集”方法
		web_getdata();
		/**
		 * @设备控制
		 * @ControlUtils.control(ConstantUtil.设备名, ConstantUtil.通道1\2\3,
		 *                                         ConstantUtil.开\关);
		 */
		// 门禁开关功能
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {// 被打开
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {// 被关闭
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// DVD开关功能
		tg_dvd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {// 被打开
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				} else {// 被关闭
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				}
			}
		});
		// 风扇开关功能
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
		// 空调开关功能
		tg_kongtiao.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
				}
			}
		});
		// 射灯开关功能
		tg_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// 电视开关功能
		tg_tv.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}
			}
		});
		// 报警灯开关功能
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
		ra_cls.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
			}
		});
		// 窗帘开
		ra_open.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
			}
		});
		// 窗帘停
		ra_stop.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
			}
		});

		return view;
	}

	/*
	 * @方法名：ControlUtils.getData();
	 * 
	 * @功 能：传感器数据获取
	 * 
	 * @参 数：String
	 * 
	 * @返回值：onResult(final DeviceBean.Device bean)
	 */
	private void web_getdata() {
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// 如果服务器数据不为空，则获取并释放在TextView上
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
							tv_press.setText(getdata.getAirPressure());
							press = Float.parseFloat(tv_press.getText()
									.toString());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// 二氧化碳
							tv_co.setText(getdata.getCo2());
							co = Float.parseFloat(tv_co.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							tv_gas.setText(getdata.getGas());
							gas = Float.parseFloat(tv_gas.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
							tv_hum.setText(getdata.getHumidity());
							hum = Float.parseFloat(tv_hum.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
							tv_ill.setText(getdata.getIllumination());
							ill = Float.parseFloat(tv_ill.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							tv_pm.setText(getdata.getPM25());
							pm = Float.parseFloat(tv_pm.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
							tv_smo.setText(getdata.getSmoke());
							smo = Float.parseFloat(tv_smo.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// 温度
							tv_temp.setText(getdata.getTemperature());
							temp = Float.parseFloat(tv_temp.getText()
									.toString());
						}
						// 人体红外
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {
							if (getdata.getStateHumanInfrared().equals(
									ConstantUtil.CLOSE)) {
								tv_per.setText("无人");
								per = Float.parseFloat("0");
							} else {
								tv_per.setText("有人");
								per = Float.parseFloat("1");
							}
						}
					}
				});
			}
		});
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}
