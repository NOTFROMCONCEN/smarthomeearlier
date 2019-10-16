package com.example.guosaiademo921;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*
 * @文件名：AdaHelper.java
 * @描述：Spinner下拉框自定义容器
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-21
 */
public class AdaHelper extends ArrayAdapter<String> {
	Context mContext;
	String[] mStringArray;

	public AdaHelper(Context context, String[] stringArray) {
		super(context, android.R.layout.simple_spinner_item, stringArray);
		// TODO Auto-generated constructor stub
		mContext = context;
		mStringArray = stringArray;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);
			convertView = layoutInflater.inflate(
					android.R.layout.simple_spinner_dropdown_item, null, false);
		}
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mStringArray[position]);
		tv.setTextColor(Color.WHITE);
		return convertView;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater layoutInflater = LayoutInflater.from(mContext);
			convertView = layoutInflater.inflate(
					android.R.layout.simple_spinner_item, null, false);
		}
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mStringArray[position]);
		tv.setTextColor(Color.BLACK);
		return convertView;
	}

}
