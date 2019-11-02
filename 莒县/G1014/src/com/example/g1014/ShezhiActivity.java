package com.example.g1014;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ShezhiActivity extends Fragment {
	EditText etnew,etold;
	Button btnxg;
	ImageView imtui;
	SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_shezhi, container,false);
		etold=(EditText)view.findViewById(R.id.Edold);
		etnew=(EditText)view.findViewById(R.id.Ednew);
		btnxg=(Button)view.findViewById(R.id.buxg);
		imtui=(ImageView)view.findViewById(R.id.imfan);

		try {
			db=getActivity().openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		imtui.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getActivity().finish();	
			}
		});
		btnxg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] arg=new String[]{etold.getText().toString()};
				Cursor cursor=db.rawQuery("select * from user where pwd=?", arg);
				if (cursor.getCount()<=0) {
					Toast.makeText(getActivity(),"¾ÉÃÜÂë´íÎó",Toast.LENGTH_LONG).show();
				}else {
					ContentValues cvValues=new ContentValues();
					cvValues.put("name", "bizideal");
					cvValues.put("pwd", etnew.getText().toString());
					int i=db.update("user",cvValues,"name=?",new String[]{"bizideal"});
					if (i>0) {
						Toast.makeText(getActivity(), "ÃÜÂëÐÞ¸Ä³É¹¦", 0).show();
					}
				}
			}
		});
		return view;
	}

}
