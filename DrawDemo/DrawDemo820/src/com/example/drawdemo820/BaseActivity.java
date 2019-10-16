package com.example.drawdemo820;

import android.os.Bundle;
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
 * @描述：完成数据采集、设备控制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-20
 */
public class BaseActivity extends Fragment {
	private Button btn_send;// 红外发射按钮
	private EditText et_send;// 红外发射文本框
	// 数据采集
	private TextView tv_temp, tv_hum, tv_gas, tv_press, tv_smo, tv_ill, tv_co,
			tv_pm, tv_per;
	static float temp, hum, gas, press, smo, ill, co, pm, per;

	// 设备控制
	private ToggleButton tg_lamp, tg_door, tg_fan, tg_tv, tg_kongtiao, tg_dvd,
			tg_warm;// 开关
	private RadioButton ra_open, ra_cls, ra_stop;// 窗帘

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) view.findViewById(R.id.tg_dvd);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_kongtiao = (ToggleButton) view.findViewById(R.id.tg_kongtiao);
		tg_lamp = (ToggleButton) view.findViewById(R.id.tg_lamp);
		tg_tv = (ToggleButton) view.findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		btn_send = (Button) view.findViewById(R.id.btn_send);
		et_send = (EditText) view.findViewById(R.id.et_send);
		// 门禁开关
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
		// 门禁开关
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
		// DVD开关
		tg_dvd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
			}
		});
		// 空调开关
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
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
			}
		});
		// 电视开关
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
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
			}
		});
		// 风扇开关
		tg_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				}
			}
		});
		// 射灯开关
		tg_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				}
			}
		});
		// 报警灯开关
		tg_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				}
			}
		});
		// 窗帘关
		ra_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// 窗帘开
		ra_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// 停
		ra_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});

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
							tv_press.setText(getdata.getAirPressure());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// 二氧化碳
							tv_co.setText(getdata.getCo2());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							tv_gas.setText(getdata.getGas());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
							tv_hum.setText(getdata.getHumidity());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
							tv_ill.setText(getdata.getIllumination());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							tv_pm.setText(getdata.getCo2());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
							tv_smo.setText(getdata.getSmoke());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// 温度
							tv_temp.setText(getdata.getTemperature());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// 人体红外
							if (getdata.getStateHumanInfrared().equals(
									ConstantUtil.CLOSE)) {
								tv_per.setText("无人");
							} else {
								tv_per.setText("有人");
							}
						}
					}
				});
			}
		});
		return view;
	}
}