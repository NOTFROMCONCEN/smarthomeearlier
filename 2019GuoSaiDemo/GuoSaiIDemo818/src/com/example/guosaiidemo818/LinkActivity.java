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
 * @文件名：LinkActivity.java
 * @描述：对用户
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-19
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