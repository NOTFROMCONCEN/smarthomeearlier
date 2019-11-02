package com.example.b091002;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WqActivity extends Fragment {
	public static TextView tvwfang;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view=inflater.inflate(R.layout.activity_wq, container,false);
	tvwfang=(TextView)view.findViewById(R.id.tvwfang);
	return view;
}

}
