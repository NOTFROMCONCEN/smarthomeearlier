package com.example.drawdemo817;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

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
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;

/*
 * @�ļ�����LinkActivity.java
 * @���������û���ɽ��滬��������ģʽ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-17
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1;// Spinner
	private Spinner sp_2;
	private Spinner sp_3;
	private ArrayAdapter<String> mArrayAdapter;// ������
	private String[] mStringArray;
	private CheckBox check_start_link;// ��ѡ�򿪹�
	private boolean link_state;
	private EditText et_number_get;// �ı���
	private int number_gte;// ��ȡ���õ���ֵ

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		// ����
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		sp_3 = (Spinner) view.findViewById(R.id.spinner3);
		check_start_link = (CheckBox) view.findViewById(R.id.cehck_start_link);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
		// ��ȡXML�ļ����������-spinner1
		mStringArray = getResources().getStringArray(R.array.temp_ill);
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		sp_1.setAdapter(mArrayAdapter);
		// ��ȡXML�ļ����������-spinner2
		mStringArray = getResources().getStringArray(R.array.big_small);
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		sp_2.setAdapter(mArrayAdapter);
		// ��ȡXML�ļ����������-spinner3
		mStringArray = getResources().getStringArray(R.array.fan_lamp_warm);
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		sp_3.setAdapter(mArrayAdapter);
		// ��ѡ�򿪹ص���¼�
		check_start_link
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							if (et_number_get.getText().toString().equals("")) {
								Toast.makeText(getActivity(), "��ֵ����Ϊ��",
										Toast.LENGTH_SHORT).show();
								check_start_link.setChecked(false);
								link_state = false;
							} else {
								link_state = true;
							}
						} else {
							link_state = false;
						}
					}
				});
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ��������ģʽ����
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				String spinner_1 = sp_1.getSelectedItem().toString();
				String spinner_2 = sp_2.getSelectedItem().toString();
				String spinner_3 = sp_3.getSelectedItem().toString();
				number_gte = Integer
						.valueOf(et_number_get.getText().toString());
				if (spinner_1.equals("�¶�")) {
					// ���Spinner���¶�
					if (spinner_2.equals(">")) {
						// ���Spinner2�Ǵ���
						if (BaseActivity.temp > number_gte) {
							// ������������������趨��ֵ
							if (spinner_3.equals("����")) {
								// ���Spinner3�Ƿ���
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("���")) {
								// ���Spinner3�����
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("������")) {
								// ���Spinner3�Ǳ�����
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							// ������������������ʾ
							Toast.makeText(getActivity(), "����������",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_start_link.setChecked(false);
						}
					}
					if (spinner_2.equals("<")) {
						// ���Spinner2��С��
						if (BaseActivity.temp < number_gte) {
							// �������������С���趨��ֵ
							if (spinner_3.equals("����")) {
								// ���Spinner3�Ƿ���
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("���")) {
								// ���Spinner3�����
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("������")) {
								// ���Spinner3�Ǳ�����
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							// ������������������ʾ
							Toast.makeText(getActivity(), "����������",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_start_link.setChecked(false);
						}
					}
				}
				if (spinner_1.equals("����")) {
					// ���Spinner���¶�
					if (spinner_2.equals(">")) {
						// ���Spinner2�Ǵ���
						if (BaseActivity.ill > number_gte) {
							// ������������������趨��ֵ
							if (spinner_3.equals("����")) {
								// ���Spinner3�Ƿ���
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("���")) {
								// ���Spinner3�����
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("������")) {
								// ���Spinner3�Ǳ�����
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							// ������������������ʾ
							Toast.makeText(getActivity(), "����������",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_start_link.setChecked(false);
						}
					}
					if (spinner_2.equals("<")) {
						// ���Spinner2��С��
						if (BaseActivity.ill < number_gte) {
							// �������������С���趨��ֵ
							if (spinner_3.equals("����")) {
								// ���Spinner3�Ƿ���
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("���")) {
								// ���Spinner3�����
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("������")) {
								// ���Spinner3�Ǳ�����
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							// ������������������ʾ
							Toast.makeText(getActivity(), "����������",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_start_link.setChecked(false);
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
}
