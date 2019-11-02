package com.example.b1008;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Hui1Activity extends Fragment {
	public static TextView tvhuif;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_hui1, container,false);
		tvhuif=(TextView)view.findViewById(R.id.tvhuif);
		return view;
	}
}
