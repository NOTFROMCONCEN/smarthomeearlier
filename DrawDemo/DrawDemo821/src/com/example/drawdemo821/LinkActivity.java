package com.example.drawdemo821;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/*
 * @�ļ�����LinkActivity.java
 * @����������豸������ģʽ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-20
 */
public class LinkActivity extends Fragment {
	private Spinner spinner1, spinner2, spinner3;
	private EditText et_number;
	private CheckBox check_mode_start;
	private RadioButton ra_temp_mode, ra_fangdao_mode, ra_lijia_mode;
	private RadioGroup rg_mode_check;
	private boolean link_state = false;// ����ģʽ
	private boolean temp_mode = false;// �¶�ģʽ
	private boolean fangdao_mode = false;// ����ģʽ
	private boolean lijia_mode = false;// ���ģʽ
	private ArrayAdapter<String> mArrayAdapter;// ������
	private String[] mStringArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		spinner1 = (Spinner) view.findViewById(R.id.spinner1);
		spinner2 = (Spinner) view.findViewById(R.id.spinner2);
		spinner3 = (Spinner) view.findViewById(R.id.spinner3);
		et_number = (EditText) view.findViewById(R.id.et_number);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_lijia_mode = (RadioButton) view.findViewById(R.id.ra_lijia_mode);
		ra_temp_mode = (RadioButton) view.findViewById(R.id.ra_temp_mode);
		check_mode_start = (CheckBox) view.findViewById(R.id.check_start);
		// ��ȡXML�ļ��������
		mStringArray = getResources().getStringArray(R.array.temp_hum);
		mArrayAdapter = new AdHelper(getActivity(), mStringArray);
		spinner1.setAdapter(mArrayAdapter);// spinner1
		mStringArray = getResources().getStringArray(R.array.big_small);
		mArrayAdapter = new AdHelper(getActivity(), mStringArray);
		spinner2.setAdapter(mArrayAdapter);// spinner2
		mStringArray = getResources().getStringArray(R.array.fan_lamp);
		mArrayAdapter = new AdHelper(getActivity(), mStringArray);
		spinner3.setAdapter(mArrayAdapter);// spinner3
		// ���á�������ť����¼�
		check_mode_start
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							if (et_number.getText().toString().equals("")) {
								// �����ֵΪ��
								Toast.makeText(getActivity(), "��ֵ����Ϊ��",
										Toast.LENGTH_SHORT).show();
								check_mode_start.setChecked(false);
								link_state = false;
							} else {
								link_state = true;
							}
						} else {
							link_state = false;
						}
					}
				});
		// �����߳�
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����link_state�����Ƿ񱻼���
	 * 
	 * @ʱ �䣺����11:31:59
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				if (et_number.getText().toString().equals("")) {
					// ����������ȡ��link�ļ��ȡ����ѡ��Ĺ�ѡ
					link_state = false;
					Toast.makeText(getActivity(), "��ֵ����Ϊ��", Toast.LENGTH_SHORT)
							.show();
					check_mode_start.setChecked(false);
				} else {
					int get_number;
					String sp_1, sp_2, sp_3;
					sp_1 = spinner1.getSelectedItem().toString();
					sp_2 = spinner2.getSelectedItem().toString();
					sp_3 = spinner3.getSelectedItem().toString();
					get_number = Integer
							.valueOf(et_number.getText().toString());
					if (sp_1.equals("�¶�")) {
						if (sp_2.equals(">")) {
							if (BaseActivity.temp > get_number) {
								if (sp_3.equals("���")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("����")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								Toast.makeText(getActivity(), "����������",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								check_mode_start.setChecked(false);
							}
						}
						if (sp_2.equals("<")) {
							if (BaseActivity.temp < get_number) {
								if (sp_3.equals("���")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("����")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								Toast.makeText(getActivity(), "����������",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								check_mode_start.setChecked(false);
							}
						}
					}
					if (sp_1.equals("ʪ��")) {
						if (sp_2.equals(">")) {
							if (BaseActivity.hum > get_number) {
								if (sp_3.equals("���")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("����")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								Toast.makeText(getActivity(), "����������",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								check_mode_start.setChecked(false);
							}
						}
						if (sp_2.equals("<")) {
							if (BaseActivity.hum > get_number) {
								if (sp_3.equals("���")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("����")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								Toast.makeText(getActivity(), "����������",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								check_mode_start.setChecked(false);
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
}