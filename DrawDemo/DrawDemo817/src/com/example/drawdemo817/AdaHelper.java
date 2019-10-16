package com.example.drawdemo817;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*
 * @文件名：AdaHelper.java
 * @描述：设置Spinner下拉菜单样式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-17
 * @author Administrator
 */
public class AdaHelper extends ArrayAdapter<String> {
	private Context mContext;
	private String[] mStringArray;

	public AdaHelper(Context context, String[] stringArrya) {
		super(context, android.R.layout.simple_spinner_item, stringArrya);
		// TODO Auto-generated constructor stub
		mContext = context;
		mStringArray = stringArrya;
	}

	// 设置SPinner下拉菜单字体样式
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(
					android.R.layout.simple_spinner_dropdown_item, parent,
					false);
		}
		// 设置SPinner显示样式
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mStringArray[position]);
		tv.setTextColor(Color.BLACK);
		return convertView;
	}

	// 设置SPinner内显示字体样式
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(
					android.R.layout.simple_spinner_item, parent, false);
		}
		// /设置Spinner显示出来的样式
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		tv.setText(mStringArray[position]);
		tv.setTextColor(Color.BLACK);
		return convertView;
	}

}
