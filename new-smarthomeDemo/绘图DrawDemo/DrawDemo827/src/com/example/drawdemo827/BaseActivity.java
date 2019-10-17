package com.example.drawdemo827;

import com.bizideal.smarthome.socket.SocketClient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @文件名：BaseActivity.java
 * @描述：完成界面的滑动、数据采集、设备控制功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-27
 */
public class BaseActivity extends Fragment {
	private Button btn_send;
	private EditText et_send;
	private ToggleButton tg_lamp;// 射灯
	private ToggleButton tg_door;// 门禁
	private ToggleButton tg_fan;// 风扇
	private ToggleButton tg_tv;// 电视
	private ToggleButton tg_kongtiao;// 空调
	private ToggleButton tg_dvd;// DVD
	private ToggleButton tg_warm;// 报警灯
	private TextView tv_temp;// 温度
	private TextView tv_hum;// 温度
	private TextView tv_gas;// 燃气
	private TextView tv_press;// 气压
	private TextView tv_smo;// 烟雾
	private TextView tv_ill;// 光照
	private TextView tv_co;// 二氧化碳
	private TextView tv_pm;// PM2.5
	private TextView tv_per;// 人体红外

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
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) view.findViewById(R.id.tg_dvd);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_kongtiao = (ToggleButton) view.findViewById(R.id.tg_kongtiao);
		tg_lamp = (ToggleButton) view.findViewById(R.id.tg_lamp);
		tg_tv = (ToggleButton) view.findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		return view;
	}
}