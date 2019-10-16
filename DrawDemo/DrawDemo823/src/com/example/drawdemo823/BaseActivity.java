package com.example.drawdemo823;

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
 * @描述：完成信息采集、设备控制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-23
 */
public class BaseActivity extends Fragment {

	private Button btn_send;// 红外发射按钮
	private EditText et_send;// 红外发射文本框
	private ToggleButton tg_lamp, tg_door, tg_fan, tg_tv, tg_kongtiao, tg_dvd,
			tg_warm;// 开关
	private RadioButton ra_open, ra_cls, ra_stop;// 窗帘控制
	private TextView tv_temp;// 温度
	private TextView tv_hum;// 湿度
	private TextView tv_gas;// 燃气
	private TextView tv_smo;// 烟雾
	private TextView tv_ill;// 光照
	private TextView tv_co;// Co2
	private TextView tv_pm;// PM2.5
	private TextView tv_per;// 人体红外
	private TextView tv_press;// 气压
	static float temp, hum, gas, smo, ill, co, pm, per, press;// 公共float数值

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		btn_send = (Button) view.findViewById(R.id.btn_send);
		et_send = (EditText) view.findViewById(R.id.et_send);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) view.findViewById(R.id.tg_dvd);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_kongtiao = (ToggleButton) view.findViewById(R.id.tg_kongtiao);
		tg_lamp = (ToggleButton) view.findViewById(R.id.tg_lamp);
		tg_tv = (ToggleButton) view.findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
		btn_send = (Button) view.findViewById(R.id.btn_send);
		et_send = (EditText) view.findViewById(R.id.et_send);
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
		// DVD
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
		// 空调
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
		// 电视机
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
		// 窗帘停
		ra_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
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
							tv_press.setText(getdata.getAirPressure());
							press = Float.parseFloat(tv_press.getText()
									.toString());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							tv_co.setText(getdata.getCo2());
							co = Float.parseFloat(tv_co.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							tv_gas.setText(getdata.getCo2());
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
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// 人体红外
							if (getdata.getStateHumanInfrared().equals(
									ConstantUtil.CLOSE)) {
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
		return view;
	}
}