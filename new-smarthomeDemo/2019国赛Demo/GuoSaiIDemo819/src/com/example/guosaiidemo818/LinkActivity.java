package com.example.guosaiidemo818;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/*
 * @�ļ�����LinkActivity.java
 * @���������û�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-19
 */
public class LinkActivity extends Fragment {
	private Button btn_chose_link;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_link, container, false);
		btn_chose_link = (Button) view.findViewById(R.id.btn_chose_link);
		btn_chose_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chose_link();
			}
		});
		return view;
	}

	private void chose_link() {
	}
}