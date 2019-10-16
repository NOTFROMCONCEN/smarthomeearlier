package com.example.drawdemo910;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

/*
 * @文件名：LinkActivity.java
 * @描述：联动模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-10
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1, sp_2, sp_3;
	private EditText et_number_get;
	private RadioButton ra_temp_mode;// 温度模式
	private RadioButton ra_night_mode;// 夜晚模式
	private RadioButton ra_anfang_mode;// 安防模式
	private boolean temp_mode = false;
	private boolean night_mode = false;
	private boolean anfang_mode = false;
	private CheckBox cb_check_state;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);

		return view;
	}
}
