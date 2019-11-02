package com.example.disan;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class QIngActivity extends Fragment {
	CheckBox chaf,chlj,chzdy,ch1,ch2,ch3,ch4;
	Switch sw1;
	Spinner sp1;
	EditText et1;
	Boolean kg=false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_qing, container,false);
		return view;
	}

}
