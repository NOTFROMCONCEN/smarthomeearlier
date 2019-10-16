package com.example.guosaiddemo924;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

/*
 * @文件名：ControlActivity.java
 * @描述：设备控制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-24
 */
public class ControlActivity extends Activity {
	private ToggleButton tg_lamp;// 射灯
	private ToggleButton tg_door;// 门禁
	private ToggleButton tg_fan;// 风扇
	private ToggleButton tg_warm;// 报警灯
	private TextView tv_shebeikongzhi_time;
	private Button btn_tv_open;// 电视机开
	private Button btn_dvd_open;// DVD开
	private Button btn_kongtiao_open;// 空调开
	private Button btn_tv_cls;// 电视机关
	private Button btn_dvd_cls;// DVD关
	private Button btn_kongtiao_cls;// 空调关
	private Button btn_cur_open;// 窗帘开
	private Button btn_cur_cls;// 窗帘关
	private Button btn_cur_stop;// 窗帘停
	private ImageView iv_back_button;// 返回按钮
	private ImageView iv_back_text;// 返回文本

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controul);
		tv_shebeikongzhi_time = (TextView) findViewById(R.id.tv_shebeikongzhi_time);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		btn_cur_cls = (Button) findViewById(R.id.btn_cur_cls);
		btn_cur_open = (Button) findViewById(R.id.btn_cur_open);
		btn_cur_stop = (Button) findViewById(R.id.btn_cur_stop);
		btn_dvd_cls = (Button) findViewById(R.id.btn_dvd_play);
		btn_dvd_open = (Button) findViewById(R.id.btn_dvd_open);
		btn_kongtiao_cls = (Button) findViewById(R.id.btn_kongtiao_play);
		btn_kongtiao_open = (Button) findViewById(R.id.btn_kongtiao_open);
		btn_tv_cls = (Button) findViewById(R.id.btn_tv_play);
		btn_tv_open = (Button) findViewById(R.id.btn_tv_open);
		iv_back_button = (ImageView) findViewById(R.id.iv_back_shebeikongzhi_button);
		iv_back_text = (ImageView) findViewById(R.id.iv_back_shebeikongzhi_text);
		// 关闭界面
		iv_back_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		iv_back_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
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
		// 红外系列
		// 电视机开关
		btn_tv_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		btn_tv_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// DVD开关
		btn_dvd_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		btn_dvd_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// 空调开关
		btn_kongtiao_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		btn_kongtiao_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});

		// 窗帘控制
		btn_cur_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		btn_cur_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		btn_cur_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// 设置字体颜色
		tv_shebeikongzhi_time.setTextColor(Color.CYAN);
		// 激活进程
		handler.post(timeRunnable);

	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：获取时间
	 * 
	 * @时 间：上午8:51:13
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_shebeikongzhi_time.setText(MenuActivity.TIME);
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
