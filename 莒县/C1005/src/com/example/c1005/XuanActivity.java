package com.example.c1005;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class XuanActivity extends Fragment implements OnClickListener{
	TextView tvjiben,tvlian,tvmoshi,tvhui;
	ImageView imjiben,imlian,immoshi,imhui;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_xuan, container,false);
		tvhui=(TextView)view.findViewById(R.id.Texthui);
		tvjiben=(TextView)view.findViewById(R.id.textjiben);
		tvlian=(TextView)view.findViewById(R.id.Textliandong);
		tvmoshi=(TextView)view.findViewById(R.id.Textmsohi);
		imjiben=(ImageView)view.findViewById(R.id.imjiben);
		imlian=(ImageView)view.findViewById(R.id.Imlian);
		immoshi=(ImageView)view.findViewById(R.id.Immoshi);
		imhui=(ImageView)view.findViewById(R.id.Imhui);
		
		tvjiben.setOnClickListener(this); 
		tvlian.setOnClickListener(this); 
		tvmoshi.setOnClickListener(this); 
		tvhui.setOnClickListener(this); 
		
		imjiben.setVisibility(View.VISIBLE);
		imlian.setVisibility(View.INVISIBLE);
		immoshi.setVisibility(View.INVISIBLE);
		imhui.setVisibility(View.INVISIBLE);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.textjiben:
			imjiben.setVisibility(View.VISIBLE);
			imlian.setVisibility(View.INVISIBLE);
			immoshi.setVisibility(View.INVISIBLE);
			imhui.setVisibility(View.INVISIBLE);
			ZongActivity.mViewPager.setCurrentItem(1);
			break;
		case R.id.Textliandong:
			imjiben.setVisibility(View.INVISIBLE);
			imlian.setVisibility(View.VISIBLE);
			immoshi.setVisibility(View.INVISIBLE);
			imhui.setVisibility(View.INVISIBLE);
			ZongActivity.mViewPager.setCurrentItem(2);
			break;
		case R.id.Textmsohi:
			imjiben.setVisibility(View.INVISIBLE);
			imlian.setVisibility(View.INVISIBLE);
			immoshi.setVisibility(View.VISIBLE);
			imhui.setVisibility(View.INVISIBLE);
			ZongActivity.mViewPager.setCurrentItem(3);
			break;
		case R.id.Texthui:
			imjiben.setVisibility(View.INVISIBLE);
			imlian.setVisibility(View.INVISIBLE);
			immoshi.setVisibility(View.INVISIBLE);
			imhui.setVisibility(View.VISIBLE);
			ZongActivity.mViewPager.setCurrentItem(4);
			break;

		default:
			break;
		}
	}


}
