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
 * @�ļ�����LinkFragment.java
 * @������������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-3
 */
public class LinkFragment extends Fragment {
	private Spinner sp_1;
	private Spinner sp_2;
	private String[] mStringArray;
	private ArrayAdapter<String> mArrayAdapter;
	private CheckBox cb_anfang_mode;// ����ģʽ
	private CheckBox cb_temp_mode;// �¶�ģʽ
	private CheckBox cb_diy_mode;// �Զ���ģʽ
	private EditText et_number_get;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		initView(view);// ��
		setSpinnerView();// ����Spinner��ʽ
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ģʽ
	 * 
	 * @ʱ �䣺����4:02:39
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
	 * @��������setSpinnerView
	 * 
	 * @�� �ܣ�����Spinner������ʽ
	 * 
	 * @ʱ �䣺����3:09:37
	 */
	private void setSpinnerView() {
		// ����Spinner����ʾ����
		// spinner1
		mStringArray = getResources().getStringArray(R.array.temp_ill);// �¶ȡ�����
		mArrayAdapter = new ArrayHelper(getActivity(), mStringArray);
		sp_1.setAdapter(mArrayAdapter);
		// spinner2
		mStringArray = getResources().getStringArray(R.array.big_small);// ��С
		mArrayAdapter = new ArrayHelper(getActivity(), mStringArray);
		sp_2.setAdapter(mArrayAdapter);
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����3:10:05
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
