package com.example.shengsaiademo2017918;

import java.text.DecimalFormat;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

/*
 * @文件名：LinkActivity.java
 * @描述：联动
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-18
 */
public class LinkActivity extends Fragment {
	private String spinner1, spinner2;
	private boolean link_state;
	private Switch sw_warm;// 报警灯
	private Switch sw_fan;// 风扇
	private Switch sw_lamp;// 射灯
	private Switch sw_door;// 门禁
	private CountDownTimer timer;// 倒计时
	private Spinner sp_1, sp_2;// spinner
	private EditText et_number;// 数值
	private EditText et_time;// 时间
	int number_get = 0, num = 0;
	private Button btn_open;// 按钮开关
	private TextView tv_time;// 时间
	private TextView tv_time_retime;// 倒计时
	private long min, sec;// 分钟、秒

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		tv_time = (TextView) view.findViewById(R.id.tv_time_link_1);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		btn_open = (Button) view.findViewById(R.id.btn_start);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		tv_time_retime = (TextView) view.findViewById(R.id.tv_link_time);
		et_number = (EditText) view.findViewById(R.id.et_number_get);
		et_time = (EditText) view.findViewById(R.id.et_time_get);
		// 设备控制
		sw_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {

					}
				}
			}
		});
		sw_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		sw_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		sw_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		// 设置开关
		btn_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				spinner1 = sp_1.getSelectedItem().toString();
				spinner2 = sp_2.getSelectedItem().toString();
				number_get = Integer.valueOf(et_number.getText().toString());
				if (btn_open.getText().toString().equals("开启联动")) {
					if (timer != null) {
						timer.cancel();
					}
					link_state = true;
					System.out.println(BaseActivity.temp);
					System.out.println("1");
					// if (sp_1.equals("温度") && sp_2.equals(">")) {
					// if (BaseActivity.temp > number_get) {
					// System.out.println("open");
					// link_state = true;
					// btn_open.setText("停止联动");
					// }
					// }
					// if (sp_1.equals("温度") && sp_2.equals("<=")) {
					// if (BaseActivity.temp < number_get) {
					// link_state = true;
					// btn_open.setText("停止联动");
					// }
					// }
					// if (sp_1.equals("湿度") && sp_2.equals(">")) {
					// if (BaseActivity.hum > number_get) {
					// link_state = true;
					// btn_open.setText("停止联动");
					// }
					// }
					// if (sp_1.equals("湿度") && sp_2.equals("<=")) {
					// if (BaseActivity.hum < number_get) {
					// link_state = true;
					// btn_open.setText("停止联动");
					// }
					// }
					// if (sp_1.equals("光照") && sp_2.equals(">")) {
					// if (BaseActivity.ill > number_get) {
					// link_state = true;
					// btn_open.setText("停止联动");
					// }
					// }
					// if (sp_1.equals("光照") && sp_2.equals("<=")) {
					// if (BaseActivity.ill < number_get) {
					// link_state = true;
					// btn_open.setText("停止联动");
					// }
					// }
					if (link_state) {
						// 总时间输出的值等于分钟
						num = Integer.parseInt(et_time.getText().toString()) * 60 * 1000;
						timer = new CountDownTimer(num, 1000) {

							@Override
							public void onTick(long millisUntilFinished) {
								// TODO Auto-generated method stub
								// 分
								min = millisUntilFinished / 1000 / 60;
								// 秒
								sec = millisUntilFinished / 1000 % 60;
								// 设置文本
								tv_time_retime.setText("联动模式还有" + min + "分"
										+ sec + "秒");
								btn_open.setText("关闭联动");
							}

							@Override
							public void onFinish() {
								// TODO Auto-generated method stub
								link_state = false;
								btn_open.setText("开启联动");
								tv_time_retime.setText("联动模式还有X分X秒");
							}
						}.start();// 开启倒计时
					}
				} else if (btn_open.getText().toString().equals("停止联动")) {
					link_state = false;
					btn_open.setText("开启联动");
					tv_time_retime.setText("联动模式还有X分X秒");
					timer.cancel();
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间
	 * 
	 * @时 间：下午4:20:59
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_time.setText(BarActivity.time);
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
