package com.example.shengsaicdemo916;

import java.text.DecimalFormat;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

/*
 * @文件名：BaseActivity.java
 * @描述：数据采集、设备控制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-16
 */
public class BaseActivity extends Fragment {
	private TextView tv_temp;// 温度
	private TextView tv_hum;// 湿度
	private TextView tv_gas;// 燃气
	private TextView tv_press;// 气压
	private TextView tv_ill;// 光照
	private TextView tv_co;// Co2
	private TextView tv_pm;// PM2.5
	private TextView tv_per;// 人体红外
	private TextView tv_smo;// 烟雾
	private Switch sw_warm;// 报警灯
	private Switch sw_door;// 门控制
	private Switch sw_fan;// 风扇
	private Switch sw_lamp;// 射灯
	private Switch sw_cur;// 窗帘
	public static float temp, hum, ill, pm, co, press, per, smo, gas;
	private Random random = new Random();
	private LinearLayout index_data;// 数据采集
	private LinearLayout index_control;// 设备控制
	private RadioButton ra_base_open;// 采集
	private RadioButton ra_control_open;// 控制
	private Button btn_sned;// 红外发射
	private EditText et_send;// 红外发射

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base_index, container,
				false);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		btn_sned = (Button) view.findViewById(R.id.btn_send);
		et_send = (EditText) view.findViewById(R.id.et_send);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		sw_cur = (Switch) view.findViewById(R.id.sw_cur);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		index_data = (LinearLayout) view.findViewById(R.id.index_data);
		index_control = (LinearLayout) view.findViewById(R.id.index_control);
		ra_base_open = (RadioButton) view.findViewById(R.id.ra_base_open);
		ra_control_open = (RadioButton) view.findViewById(R.id.ra_control_open);
		index_control.setVisibility(View.GONE);
		index_data.setVisibility(View.VISIBLE);
		// 红外发射
		btn_sned.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_send.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "数值不能为空");
				} else {
					DiyToast.showToast(getActivity(), "已发送："
							+ et_send.getText().toString());
				}
			}
		});
		ra_base_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index_control.setVisibility(View.GONE);
				index_data.setVisibility(View.VISIBLE);
			}
		});
		ra_control_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index_control.setVisibility(View.VISIBLE);
				index_data.setVisibility(View.GONE);
			}
		});

		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

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
			handler.postDelayed(timeRunnable, 500);
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
