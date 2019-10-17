package com.example.drawdemo50;

import lib.Json_data;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

public class LinkActivity extends Fragment {
	private CheckBox cb_open_fan, cb_open_ill;
	private Spinner sp_fan, sp_fan_big_small;
	private Spinner sp_ill, sp_ill_big_small;
	private EditText et_fan_number, et_ill_number;
	private int et_number_get;
	private String spinner_fan, spinner_fan_big_small, spinner_ill,
			spinner_ill_big_small;
	private boolean link_1_state = false, link_2_state = false;;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		cb_open_fan = (CheckBox) view.findViewById(R.id.cb_open_fan);
		cb_open_ill = (CheckBox) view.findViewById(R.id.cb_open_ill);
		sp_fan = (Spinner) view.findViewById(R.id.sp_fan);
		sp_fan_big_small = (Spinner) view.findViewById(R.id.sp_fan_big_small);
		sp_ill = (Spinner) view.findViewById(R.id.sp_ill);
		sp_ill_big_small = (Spinner) view.findViewById(R.id.sp_ill_big_small);
		et_fan_number = (EditText) view.findViewById(R.id.et_num_fan);
		et_ill_number = (EditText) view.findViewById(R.id.et_num_ill);
		cb_open_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_fan_number.getText().toString().equals("")) {
						Toast.makeText(getActivity(), "请输入数值！",
								Toast.LENGTH_SHORT).show();
						cb_open_fan.setChecked(false);
						link_1_state = false;
					} else {
						link_1_state = true;
					}
				} else {
					link_1_state = false;
				}
			}
		});
		cb_open_ill.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_ill_number.getText().toString().equals("")) {
						Toast.makeText(getActivity(), "请输入数值！",
								Toast.LENGTH_SHORT).show();
						link_2_state = false;
						cb_open_ill.setChecked(false);
					} else {
						link_2_state = true;
					}
				} else {
					link_2_state = false;
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (link_1_state) {
				Log.e("联动模式", "link_1_state激活");
				if (et_fan_number.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "数值不能为空！", Toast.LENGTH_SHORT)
							.show();
					cb_open_fan.setChecked(false);
					link_1_state = false;
				} else {
					spinner_fan = sp_fan.getSelectedItem().toString();
					spinner_fan_big_small = sp_fan_big_small.getSelectedItem()
							.toString();
					et_number_get = Integer.valueOf(et_fan_number.getText()
							.toString());
					if (spinner_fan.equals("温度")) {
						if (spinner_fan_big_small.equals(">")) {
							if (BaseActivity.temp > et_number_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_1_state = false;
								cb_open_fan.setChecked(false);
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
							}
						}
						if (spinner_fan_big_small.equals("<")) {
							if (BaseActivity.temp < et_number_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_1_state = false;
								cb_open_fan.setChecked(false);
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
							}
						}
					}
					if (spinner_fan.equals("湿度")) {
						if (spinner_fan_big_small.equals(">")) {
							if (BaseActivity.hum > et_number_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_1_state = false;
								cb_open_fan.setChecked(false);
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
							}
						}
						if (spinner_fan_big_small.equals("<")) {
							if (BaseActivity.hum < et_number_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_1_state = false;
								cb_open_fan.setChecked(false);
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
							}
						}
					}
					if (spinner_fan.equals("烟雾")) {
						if (spinner_fan_big_small.equals(">")) {
							if (BaseActivity.smo > et_number_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_1_state = false;
								cb_open_fan.setChecked(false);
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
							}
						}
						if (spinner_fan_big_small.equals("<")) {
							if (BaseActivity.smo < et_number_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_1_state = false;
								cb_open_fan.setChecked(false);
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
							}
						}
					}
				}
			}
			if (link_2_state) {
				Log.e("联动模式", "link_2_state激活");
				if (et_ill_number.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "请输入数值！", Toast.LENGTH_SHORT)
							.show();
					link_2_state = false;
					cb_open_ill.setChecked(false);
				} else {
					spinner_ill = sp_ill.getSelectedItem().toString();
					spinner_ill_big_small = sp_ill_big_small.getSelectedItem()
							.toString();
					et_number_get = Integer.valueOf(et_ill_number.getText()
							.toString());
					if (spinner_ill_big_small.equals(">")) {
						if (spinner_ill.equals("射灯全开")) {
							if (BaseActivity.ill > et_number_get) {
								BaseActivity.Js.control(Json_data.Lamp, 0, 1);
							} else {
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_2_state = false;
								cb_open_ill.setChecked(false);
								BaseActivity.Js.control(Json_data.Lamp, 0, 0);
							}
						}
						if (spinner_fan.equals("报警灯开")) {
							if (BaseActivity.ill > et_number_get) {
								BaseActivity.Js.control(Json_data.WarningLight,
										0, 1);
							} else {
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_2_state = false;
								cb_open_ill.setChecked(false);
								BaseActivity.Js.control(Json_data.WarningLight,
										0, 0);
							}
						}
					}
					if (spinner_ill_big_small.equals("<")) {
						if (spinner_ill.equals("射灯全开")) {
							if (BaseActivity.ill < et_number_get) {
								BaseActivity.Js.control(Json_data.Lamp, 0, 1);
							} else {
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_2_state = false;
								cb_open_ill.setChecked(false);
								BaseActivity.Js.control(Json_data.Lamp, 0, 0);
							}
						}
						if (spinner_fan.equals("报警灯开")) {
							if (BaseActivity.ill < et_number_get) {
								BaseActivity.Js.control(Json_data.WarningLight,
										0, 1);
							} else {
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_2_state = false;
								cb_open_ill.setChecked(false);
								BaseActivity.Js.control(Json_data.WarningLight,
										0, 0);
							}
						}
					}
				}
			}
			handler.postDelayed(timeRunnable, 5000);
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