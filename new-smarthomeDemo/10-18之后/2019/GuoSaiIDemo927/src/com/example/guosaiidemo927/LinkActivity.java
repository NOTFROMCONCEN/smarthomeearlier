package com.example.guosaiidemo927;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * @文件名：LinkActivity.java
 * @描述：联动模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-27
 */
public class LinkActivity extends Fragment {
	private Button btn_show_chose;// 执行选择器件按钮
	private CheckBox cb_link_state;// 联动模式
	private Spinner sp_1, sp_2;
	private EditText et_number_get;// 获取参数
	public static boolean cur_state;// 窗帘
	public static boolean warm_state;// 报警灯
	public static boolean lamp_state;// 射灯
	public static boolean fan_state;// 换气扇

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		initView(view);
		// 选择设备
		btn_show_chose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				show_Dialog();
			}
		});
		// 联动开关
		cb_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "请输入数值");
						cb_link_state.setChecked(false);
					}
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
	 * @功 能：联动功能
	 * 
	 * @时 间：下午4:22:42
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (cb_link_state.isChecked()) {
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
					cb_link_state.setChecked(false);
				} else {
					String spinner1 = sp_1.getSelectedItem().toString();
					String spinner2 = sp_2.getSelectedItem().toString();
					int number_get = Integer.valueOf(et_number_get.getText()
							.toString());
					if (spinner1.equals("温度")) {
						if (spinner2.equals(">")) {
							if (BaseActivity.temp > number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开窗帘
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开风扇
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开射灯
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开报警灯
								}
							} else {
								DiyToast.showToast(getActivity(), "条件不满足");
								cb_link_state.setChecked(false);
							}
						}
						if (spinner2.equals("<")) {
							if (BaseActivity.temp < number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开窗帘
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开风扇
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开射灯
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开报警灯
								}
							} else {
								DiyToast.showToast(getActivity(), "条件不满足");
								cb_link_state.setChecked(false);
							}
						}
					}
					if (spinner1.equals("湿度")) {
						if (spinner2.equals(">")) {
							if (BaseActivity.hum > number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开窗帘
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开风扇
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开射灯
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开报警灯
								}
							} else {
								DiyToast.showToast(getActivity(), "条件不满足");
								cb_link_state.setChecked(false);
							}
						}
						if (spinner2.equals("<")) {
							if (BaseActivity.hum < number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开窗帘
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开风扇
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开射灯
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开报警灯
								}
							} else {
								DiyToast.showToast(getActivity(), "条件不满足");
								cb_link_state.setChecked(false);
							}
						}
					}
					if (spinner1.equals("气压")) {
						if (spinner2.equals(">")) {
							if (BaseActivity.press > number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开窗帘
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开风扇
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开射灯
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开报警灯
								}
							} else {
								DiyToast.showToast(getActivity(), "条件不满足");
								cb_link_state.setChecked(false);
							}
						}
						if (spinner2.equals("<")) {
							if (BaseActivity.press < number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开窗帘
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开风扇
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开射灯
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// 开报警灯
								}
							} else {
								DiyToast.showToast(getActivity(), "条件不满足");
								cb_link_state.setChecked(false);
							}
						}
					}
				}
			}
			handler.postDelayed(timeRunnable, 1000);
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

	/*
	 * @方法名：initView()
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：下午4:21:33
	 */
	private void initView(View view) {
		btn_show_chose = (Button) view.findViewById(R.id.btn_link_chose);
		cb_link_state = (CheckBox) view.findViewById(R.id.cb_link_state);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
	}

	/*
	 * @方法名：show_Dialog
	 * 
	 * @功 能：显示对话框
	 * 
	 * @时 间：下午4:21:15
	 */
	private void show_Dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater layout = LayoutInflater.from(getActivity());
		View view = layout.inflate(R.layout.activity_link_check, null, false);
		builder.setView(view);
		final CheckBox cb_fan, cb_warm, cb_lamp, cb_cur;
		cb_cur = (CheckBox) view.findViewById(R.id.cb_link_cur);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_link_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_link_lamp);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_link_warm);
		cb_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cur_state = true;
				} else {
					cur_state = false;
				}
			}
		});
		cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					fan_state = true;
				} else {
					fan_state = false;
				}
			}
		});
		cb_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					lamp_state = true;
				} else {
					lamp_state = false;
				}
			}
		});
		cb_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					warm_state = true;
				} else {
					warm_state = false;
				}
			}
		});
		if (cur_state) {
			cb_cur.setChecked(true);
		}
		if (fan_state) {
			cb_fan.setChecked(true);
		}
		if (lamp_state) {
			cb_lamp.setChecked(true);
		}
		if (warm_state) {
			cb_warm.setChecked(true);
		}
		builder.show();
	}
}
