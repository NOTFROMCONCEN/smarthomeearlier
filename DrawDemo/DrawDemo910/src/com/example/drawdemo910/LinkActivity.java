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
 * @�ļ�����LinkActivity.java
 * @����������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-10
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1, sp_2, sp_3;
	private EditText et_number_get;
	private RadioButton ra_temp_mode;// �¶�ģʽ
	private RadioButton ra_night_mode;// ҹ��ģʽ
	private RadioButton ra_anfang_mode;// ����ģʽ
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
