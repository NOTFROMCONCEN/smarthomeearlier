package com.example.drawdemo820;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*
 * @�ļ�����AdHelper.java
 * @������AdHelper�����SPinner�����˵���ʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-20
 */
public class AdHelper extends ArrayAdapter<String> {
	private Context mContext;
	private String[] mStringArray;

	public AdHelper(Context context, String[] stringArray) {
		super(context, android.R.layout.simple_spinner_item, stringArray);
		// TODO Auto-generated constructor stub
		mContext = context;
		mStringArray = stringArray;
	}

	// ����Spinner�����˵���ʽ
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(
					android.R.layout.simple_spinner_dropdown_item, parent,
					false);
		}
		// ����Spinner�����˵���ʾ��ʽ
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mStringArray[position]);
		tv.setTextColor(Color.BLACK);
		return convertView;
	}

	// ����Spinner��ʾ��ʽ
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(
					android.R.layout.simple_spinner_item, parent, false);
		}
		// ����SPinner������ʾ��ʽ
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mStringArray[position]);
		tv.setTextColor(Color.BLACK);
		return convertView;
	}
}