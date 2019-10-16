package com.example.drawdemo825;

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
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @文件名：BaseActivity.java
 * @描述：对用户
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-25
 */
public class BaseActivity extends Fragment {
	private TextView tv_temp;// 温度
	private TextView tv_hum;// 湿度
	private TextView tv_smo;// 烟雾
	private TextView tv_ill;// 光照
	private TextView tv_press;// 气压
	private TextView tv_gas;// 燃气
	private TextView tv_co;// Co2
	private TextView tv_pm;// pm2.5
	private TextView tv_per;// 人体红外
	private ToggleButton tg_lamp;// 射灯
	private ToggleButton tg_warm;// 报警灯
	private ToggleButton tg_fan;// 风扇
	private ToggleButton tg_tv;// 电视
	private ToggleButton tg_kongtiao;// 空调
	private ToggleButton tg_dvd;// DVD
	private ToggleButton tg_door;// 门禁
	static float temp, hum, smo, ill, press, gas, co, pm, per;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) view.findViewById(R.id.tg_dvd);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_kongtiao = (ToggleButton) view.findViewById(R.id.tg_kongtiao);
		tg_lamp = (ToggleButton) view.findViewById(R.id.tg_lamp);
		tg_tv = (ToggleButton) view.findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		// 门禁开
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub

			}
		});
		// 数据采集

		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
							tv_press.setText(getdata.getAirPressure()
									.toString());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							tv_co.setText(getdata.getCo2().toString());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							tv_gas.setText(getdata.getGas().toString());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
							tv_hum.setText(getdata.getHumidity().toString());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
							tv_ill.setText(getdata.getIllumination().toString());
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
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			Random random = new Random();

			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.post(timeRunnable);
		}
	};
}
