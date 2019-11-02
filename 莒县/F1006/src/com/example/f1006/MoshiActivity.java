package com.example.f1006;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MoshiActivity extends Fragment {
	ListView lvchuang,lvdx,lvkz;
	EditText etsj,etzhil;
	Button btnmoshi;
	CheckBox chmoshi;
	List<Map<String, String>> list=new ArrayList<Map<String,String>>();
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_moshi,container,false);
		etsj=(EditText)view.findViewById(R.id.edmoshi);
		etzhil=(EditText)view.findViewById(R.id.edittianj);
		btnmoshi=(Button)view.findViewById(R.id.butmoshi);
		chmoshi=(CheckBox)view.findViewById(R.id.chmoshi);
//		lvchuang=(ListView)view.findViewById(R.id.listws);
//		
//
//		SimpleAdapter adapter=new SimpleAdapter(getActivity(), list,R.layout.shuju,new String[]{"temp","hum","ill","smoke","gas","air"}, new int[]{R.id.ra1,R.id.ra2,R.id.ra3,R.id.ra4,R.id.ra5,R.id.ra6});
//		lvchuang.setAdapter(adapter);
		return view;
	}

}
