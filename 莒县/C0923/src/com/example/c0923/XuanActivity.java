package com.example.c0923;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class XuanActivity extends Fragment implements OnClickListener{
	ImageView imjiben,imliandong,immoshi,imhui;
	TextView tvjiben,tvliandong,tvmoshi,tvhui;
 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_xuan,container,false);
		imjiben=(ImageView)view.findViewById(R.id.imjiben);
		imliandong=(ImageView)view.findViewById(R.id.Imliandong);
		immoshi=(ImageView)view.findViewById(R.id.Immoshi);
		imhui=(ImageView)view.findViewById(R.id.Imhuitu);
		tvhui=(TextView)view.findViewById(R.id.Tehuitu);
		tvliandong=(TextView)view.findViewById(R.id.Textliandong);
		tvjiben=(TextView)view.findViewById(R.id.textjiben);
		tvmoshi=(TextView)view.findViewById(R.id.Temoshi);
		
		tvjiben.setOnClickListener(this);
		tvliandong.setOnClickListener(this);
		tvmoshi.setOnClickListener(this);
		tvhui.setOnClickListener(this);
		
		imjiben.setVisibility(View.VISIBLE);
		imliandong.setVisibility(View.INVISIBLE);
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
			imliandong.setVisibility(View.INVISIBLE);
			immoshi.setVisibility(View.INVISIBLE);
			imhui.setVisibility(View.INVISIBLE);
			ZongActivity.mViewPager.setCurrentItem(1);
			break;
		case R.id.Textliandong:
			imjiben.setVisibility(View.INVISIBLE);
			imliandong.setVisibility(View.VISIBLE);
			immoshi.setVisibility(View.INVISIBLE);
			imhui.setVisibility(View.INVISIBLE);
			ZongActivity.mViewPager.setCurrentItem(2);
			break;
		case R.id.Temoshi:
			imjiben.setVisibility(View.INVISIBLE);
			imliandong.setVisibility(View.INVISIBLE);
			immoshi.setVisibility(View.VISIBLE);
			imhui.setVisibility(View.INVISIBLE);
			ZongActivity.mViewPager.setCurrentItem(3);
			break;
		case R.id.Tehuitu:
			imjiben.setVisibility(View.INVISIBLE);
			imliandong.setVisibility(View.INVISIBLE);
			immoshi.setVisibility(View.INVISIBLE);
			imhui.setVisibility(View.VISIBLE);
			ZongActivity.mViewPager.setCurrentItem(4);
			break;

		default:
			break;
		}
	}

}
