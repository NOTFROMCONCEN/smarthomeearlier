package com.example.c1017;

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
	ImageView imjiben,imlian,immos,imhui;
	TextView tvjiben,tvlian,tvmos,tvhui;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_xuan, container,false);
		imjiben=(ImageView)view.findViewById(R.id.imjiben);
		imlian=(ImageView)view.findViewById(R.id.Imliandong);
		immos=(ImageView)view.findViewById(R.id.Immsohi);
		imhui=(ImageView)view.findViewById(R.id.Imhui);
		tvhui=(TextView)view.findViewById(R.id.Tehuitu);
		tvjiben=(TextView)view.findViewById(R.id.tejiben);
		tvlian=(TextView)view.findViewById(R.id.Telaindong);
		tvmos=(TextView)view.findViewById(R.id.Temoshi);
		
		imjiben.setVisibility(View.VISIBLE);
		imlian.setVisibility(View.INVISIBLE);
		immos.setVisibility(View.INVISIBLE);
		imhui.setVisibility(View.INVISIBLE);
		
		tvjiben.setOnClickListener(this);
		tvlian.setOnClickListener(this);
		tvmos.setOnClickListener(this);
		tvhui.setOnClickListener(this);
		
		imjiben.setOnClickListener(this);
		imlian.setOnClickListener(this);
		immos.setOnClickListener(this);
		imhui.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tejiben:
			imjiben.setVisibility(View.VISIBLE);
			imlian.setVisibility(View.INVISIBLE);
			immos.setVisibility(View.INVISIBLE);
			imhui.setVisibility(View.INVISIBLE);
			HuaActivity.mViewPager.setCurrentItem(1);
			break;
		case R.id.Telaindong:
			imjiben.setVisibility(View.INVISIBLE);
			imlian.setVisibility(View.VISIBLE);
			immos.setVisibility(View.INVISIBLE);
			imhui.setVisibility(View.INVISIBLE);
			HuaActivity.mViewPager.setCurrentItem(2);
			break;
		case R.id.Temoshi:
			imjiben.setVisibility(View.INVISIBLE);
			imlian.setVisibility(View.INVISIBLE);
			immos.setVisibility(View.VISIBLE);
			imhui.setVisibility(View.INVISIBLE);
			HuaActivity.mViewPager.setCurrentItem(3);
			break;
		case R.id.Tehuitu:
			imjiben.setVisibility(View.INVISIBLE);
			imlian.setVisibility(View.INVISIBLE);
			immos.setVisibility(View.INVISIBLE);
			imhui.setVisibility(View.VISIBLE);
			HuaActivity.mViewPager.setCurrentItem(4);
			break;

		default:
			break;
		}
	}

}
