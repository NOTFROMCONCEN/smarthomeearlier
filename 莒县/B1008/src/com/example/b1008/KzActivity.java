package com.example.b1008;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class KzActivity extends Fragment {
	TextView tv8101,tv8102,tv8103,tv8104,tv8201,tv8202,tv8203,tv8204,tv8301,tv8302,tv8303,tv8304,tv8401,tv8402,tv8403,tv8404;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_kz, container,false);
		tv8101=(TextView)view.findViewById(R.id.Te1);
		tv8102=(TextView)view.findViewById(R.id.Te2);
		tv8103=(TextView)view.findViewById(R.id.Te3);
		tv8104=(TextView)view.findViewById(R.id.Te4);
		tv8201=(TextView)view.findViewById(R.id.Te5);
		tv8202=(TextView)view.findViewById(R.id.Te6);
		tv8203=(TextView)view.findViewById(R.id.Te7);
		tv8204=(TextView)view.findViewById(R.id.Te8);
		tv8301=(TextView)view.findViewById(R.id.Te9);
		tv8302=(TextView)view.findViewById(R.id.Te10);
		tv8303=(TextView)view.findViewById(R.id.Te11);
		tv8304=(TextView)view.findViewById(R.id.Te12);
		tv8401=(TextView)view.findViewById(R.id.Te13);
		tv8402=(TextView)view.findViewById(R.id.Te14);
		tv8403=(TextView)view.findViewById(R.id.Te15);
		tv8404=(TextView)view.findViewById(R.id.Te16);
		
		tv8101.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			ZongActivity.mViewPager.setCurrentItem(4);
			Kz1Activity.tvkzf.setText("8101");
			}
		});
		tv8102.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8102");
			}
		});
		tv8103.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8103");
			}
		});
		tv8104.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8104");
			}
		});
		tv8201.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8201");
			}
		});
		tv8202.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8202");
			}
		});
		tv8203.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8203");
			}
		});
		tv8204.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8204");
			}
		});
		tv8301.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8301");
			}
		});
		tv8302.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8302");
			}
		});
		tv8303.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8303");
			}
		});
		tv8304.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8304");
			}
		});
		tv8401.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8401");
			}
		});
		tv8402.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8402");
			}
		});
		tv8403.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8403");
			}
		});
		tv8404.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ZongActivity.mViewPager.setCurrentItem(4);
				Kz1Activity.tvkzf.setText("8404");
			}
		});
		return view;
	}

}
