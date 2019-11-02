package com.example.b091002;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class KongzhiActivity extends Fragment {
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_kongzhi, container,false);
		tv1=(TextView)view.findViewById(R.id.tv1);
		tv2=(TextView)view.findViewById(R.id.tv2);
		tv3=(TextView)view.findViewById(R.id.tv3);
		tv4=(TextView)view.findViewById(R.id.tv4);
		tv5=(TextView)view.findViewById(R.id.tv5);
		tv6=(TextView)view.findViewById(R.id.tv6);
		tv7=(TextView)view.findViewById(R.id.tv7);
		tv8=(TextView)view.findViewById(R.id.tv8);
		tv9=(TextView)view.findViewById(R.id.tv9);
		tv10=(TextView)view.findViewById(R.id.tv10);
		tv11=(TextView)view.findViewById(R.id.tv11);
		tv12=(TextView)view.findViewById(R.id.tv12);
		tv13=(TextView)view.findViewById(R.id.tv13);
		tv14=(TextView)view.findViewById(R.id.tv14);
		tv15=(TextView)view.findViewById(R.id.tv15);
		tv16=(TextView)view.findViewById(R.id.tv16);
		
		tv1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			ZongActivity.mViewPager.setCurrentItem(4);
			DianActivity.tvfjh.setText("房间号：8101");
			}
		});
		tv2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8102");
			}
		});
		tv3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8103");
			}
		});
		tv4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8104");
			}
		});
		tv5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8201");
			}
		});
		tv6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8202");
			}
		});
		tv7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8203");
			}
		});
		tv8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8204");
			}
		});
		tv9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8301");
			}
		});
		tv10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8302");
			}
		});
		tv11.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8303");
			}
		});
		tv12.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8304");
			}
		});
		tv13.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8401");
			}
		});
		tv14.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8402");
			}
		});
		tv15.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8403");
			}
		});
		tv16.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				DianActivity.tvfjh.setText("房间号：8404");
			}
		});
		
		return view;
	}


}
