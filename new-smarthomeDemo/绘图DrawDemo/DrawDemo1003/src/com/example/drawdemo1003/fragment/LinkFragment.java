package com.example.drawdemo1003.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.drawdemo1003.R;
import com.example.drawdemo1003.adahelper.ArrayHelper;

/*
 * @文件名：LinkFragment.java
 * @描述：联动、模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-3
 */
public class LinkFragment extends Fragment {
	private Spinner sp_1;
	private Spinner sp_2;
	private String[] mStringArray;
	private ArrayAdapter<String> mArrayAdapter;
	private CheckBox cb_anfang_mode;// 安防模式
	private CheckBox cb_temp_mode;// 温度模式
	private CheckBox cb_diy_mode;// 自定义模式
	private EditText et_number_get;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		initView(view);// 绑定
		setSpinnerView();// 设置Spinner样式
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：联动模式
	 * 
	 * @时 间：下午4:02:39
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
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
	 * @方法名：setSpinnerView
	 * 
	 * @功 能：设置Spinner下拉样式
	 * 
	 * @时 间：下午3:09:37
	 */
	private void setSpinnerView() {
		// 设置Spinner内显示内容
		// spinner1
		mStringArray = getResources().getStringArray(R.array.temp_ill);// 温度、光照
		mArrayAdapter = new ArrayHelper(getActivity(), mStringArray);
		sp_1.setAdapter(mArrayAdapter);
		// spinner2
		mStringArray = getResources().getStringArray(R.array.big_small);// 大小
		mArrayAdapter = new ArrayHelper(getActivity(), mStringArray);
		sp_2.setAdapter(mArrayAdapter);
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：下午3:10:05
	 */
	private void initView(View view) {
		// TODO Auto-generated method stub
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		cb_anfang_mode = (CheckBox) view.findViewById(R.id.cb_anfang_mode);
		cb_diy_mode = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		cb_temp_mode = (CheckBox) view.findViewById(R.id.cb_temp_mode);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
	}
}
