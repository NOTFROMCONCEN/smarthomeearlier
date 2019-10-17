package com.example.guosaicdemo831;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class LinkFragment extends Fragment {
	private Spinner sp_1, sp_2;// Spinner
	private EditText et_number_get;// ��ֵ�ı���
	private EditText et_time_get;// ʱ���ı���
	private Button btn_start;// ����������ť
	private Switch sw_warm, sw_fan, sw_lamp, sw_door;// ����
	private TextView tv_link_time;// ����ʱ
	private ArrayAdapter<String> mArrayAdapter;
	private String[] mStringArray;
	private boolean link_state = false;
	private String spinner1, spinner2;
	private int number_get;
	private long num, min, sec;
	private CountDownTimer timer;
	private TextView tv_link_get_time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		btn_start = (Button) view.findViewById(R.id.btn_start);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
		et_time_get = (EditText) view.findViewById(R.id.et_time_get);
		tv_link_time = (TextView) view.findViewById(R.id.tv_link_time);
		tv_link_get_time = (TextView) view.findViewById(R.id.tv_link_get_time);
		// ����Spinner�����˵�XML�ļ�
		mStringArray = getResources().getStringArray(R.array.big_small);// ��С
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		sp_2.setAdapter(mArrayAdapter);
		mStringArray = getResources().getStringArray(R.array.temp_hum_ill);// �¶ȡ�ʪ��
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		sp_1.setAdapter(mArrayAdapter);
		return view;
	}
}
