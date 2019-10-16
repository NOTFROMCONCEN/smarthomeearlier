package com.example.drawdemo53;

import lib.Json_data;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
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
	private Spinner sp_fan_1, sp_fan_big_small, sp_ill_1, sp_ill_big_small;
	private EditText et_fan, et_ill;
	private CheckBox cb_fan, cb_ill;
	private int et_num_get;
	private String spinner_fan, spinner_fan_bigsmall, spinner_ill,
			spinner_ill_big_small;
	private boolean link_fan = false, link_ill = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub.
		View view = inflater.inflate(R.layout.activity_link, container, false);
		sp_fan_1 = (Spinner) view.findViewById(R.id.sp_fan);
		sp_fan_big_small = (Spinner) view.findViewById(R.id.sp_fan_big_small);
		sp_ill_1 = (Spinner) view.findViewById(R.id.sp_ill);
		sp_ill_big_small = (Spinner) view.findViewById(R.id.sp_ill_big_small);
		et_fan = (EditText) view.findViewById(R.id.et_num_fan);
		et_ill = (EditText) view.findViewById(R.id.et_num_ill);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_open_fan);
		cb_ill = (CheckBox) view.findViewById(R.id.cb_open_ill);
		cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_fan.getText().toString().equals("")) {
						Toast.makeText(getActivity(), "请输入数值！",
								Toast.LENGTH_SHORT).show();
						cb_fan.setChecked(false);
						link_fan = false;
					} else {
						link_fan = true;
					}
				} else {
					link_fan = false;
				}
			}
		});
		cb_ill.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_ill.getText().toString().equals("")) {
						Toast.makeText(getActivity(), "请输入数值！",
								Toast.LENGTH_SHORT).show();
						cb_ill.setChecked(false);
						link_ill = false;
					} else {
						link_ill = true;
					}
				} else {
					link_ill = false;
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (link_fan) {
				if (et_fan.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "数值不能为空！", Toast.LENGTH_SHORT)
							.show();
					link_fan = false;
					cb_fan.setChecked(false);
				} else {
					et_num_get = Integer.valueOf(et_fan.getText().toString());
					spinner_fan = sp_fan_1.getSelectedItem().toString();
					spinner_fan_bigsmall = sp_fan_big_small.getSelectedItem()
							.toString();
					if (spinner_fan.equals("温度")) {
						if (spinner_fan_bigsmall.equals(">")) {
							if (BaseActivity.temp > et_num_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
								cb_fan.setChecked(false);
								link_fan = false;
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
							}
						}
						if (spinner_fan_bigsmall.equals("<")) {
							if (BaseActivity.temp < et_num_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
								cb_fan.setChecked(false);
								link_fan = false;
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
							}
						}
					}
					if (spinner_fan.equals("湿度")) {
						if (spinner_fan_bigsmall.equals(">")) {
							if (BaseActivity.hum > et_num_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
								cb_fan.setChecked(false);
								link_fan = false;
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
							}
						}
						if (spinner_fan_bigsmall.equals("<")) {
							if (BaseActivity.hum < et_num_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
								cb_fan.setChecked(false);
								link_fan = false;
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
							}
						}
					}
					if (spinner_fan.equals("烟雾")) {
						if (spinner_fan_bigsmall.equals(">")) {
							if (BaseActivity.smo > et_num_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
								cb_fan.setChecked(false);
								link_fan = false;
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
							}
						}
						if (spinner_fan_bigsmall.equals("<")) {
							if (BaseActivity.smo < et_num_get) {
								BaseActivity.Js.control(Json_data.Fan, 0, 1);
							} else {
								BaseActivity.Js.control(Json_data.Fan, 0, 0);
								cb_fan.setChecked(false);
								link_fan = false;
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
							}
						}
					}
				}
			}
			if (link_ill) {
				if (et_ill.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "数值不能为空", Toast.LENGTH_SHORT)
							.show();
					cb_ill.setChecked(false);
					link_ill = false;
				} else {
					et_num_get = Integer.valueOf(et_ill.getText().toString());
					spinner_ill = sp_ill_1.getSelectedItem().toString();
					spinner_ill_big_small = sp_ill_big_small.getSelectedItem()
							.toString();
					if (spinner_ill_big_small.equals(">")) {
						if (BaseActivity.ill > et_num_get) {
							if (spinner_ill.equals("射灯全开")) {
								BaseActivity.Js.control(Json_data.Lamp, 0, 1);
							}
							if (spinner_ill.equals("报警灯开")) {
								BaseActivity.Js.control(Json_data.WarningLight,
										0, 1);
							}
						} else {
							cb_ill.setChecked(false);
							link_ill = false;
							Toast.makeText(getActivity(), "条件不满足",
									Toast.LENGTH_SHORT).show();
							if (spinner_ill.equals("射灯全开")) {
								BaseActivity.Js.control(Json_data.Lamp, 0, 0);
							}
							if (spinner_ill.equals("报警灯开")) {
								BaseActivity.Js.control(Json_data.WarningLight,
										0, 0);
							}
						}
					}
					if (spinner_ill_big_small.equals("<")) {
						if (BaseActivity.ill < et_num_get) {
							if (spinner_ill.equals("射灯全开")) {
								BaseActivity.Js.control(Json_data.Lamp, 0, 1);
							}
							if (spinner_ill.equals("报警灯开")) {
								BaseActivity.Js.control(Json_data.WarningLight,
										0, 1);
							}
						} else {
							cb_ill.setChecked(false);
							link_ill = false;
							Toast.makeText(getActivity(), "条件不满足",
									Toast.LENGTH_SHORT).show();
							if (spinner_ill.equals("射灯全开")) {
								BaseActivity.Js.control(Json_data.Lamp, 0, 0);
							}
							if (spinner_ill.equals("报警灯开")) {
								BaseActivity.Js.control(Json_data.WarningLight,
										0, 0);
							}
						}
					}
				}
			}
			handler.postDelayed(timeRunnable, 100);
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
