package com.example.disan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GuanActivity extends Fragment {
	Button btn1,btn2;
    ListView lv1;
    SQLiteDatabase db;
    Editor editor;
    EditText etupdate;
    SharedPreferences sp;
    List<Map<String, String>> list;
    Cursor cursor;
    SimpleAdapter adapter;
    int guanli=1000;
    String strname,strpwd;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_guan, container,false);
		btn1=(Button)view.findViewById(R.id.butguanlixg);
		btn2=(Button)view.findViewById(R.id.Butshanchu);
		lv1=(ListView)view.findViewById(R.id.listView1);
		
		sp=getActivity().getSharedPreferences("sql.xml", Context.MODE_PRIVATE);
		
		try {
			db=getActivity().openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.registerForContextMenu(lv1);
		
		list=new ArrayList<Map<String,String>>();
		
		cursor=db.rawQuery("select * from user", null);
		cursor.moveToFirst();
		
		while (cursor.moveToNext()) {
			Map<String, String> map=new HashMap<String, String>();
			map.put("name", cursor.getString(0));
			map.put("pwd", cursor.getString(1));
			
			list.add(map);
		}
		cursor.close();
		adapter=new SimpleAdapter(getActivity(), list, R.layout.listview, new String[]{"name","pwd"}, new int[]{R.id.te1,R.id.te2});
		lv1.setAdapter(adapter);
		
		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				guanli=arg2;
			}
		});
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					etupdate=new EditText(getActivity());
					AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
					builder.setTitle("修改密码");
					builder.setView(etupdate);
					builder.setPositiveButton("ok",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							strpwd=list.get(guanli).get("pwd");
							String us=etupdate.getText().toString();
							ContentValues cValues=new ContentValues();
							cValues.put("pwd", us);
							db.update("user", cValues, "pwd=?", new String[]{strpwd});
							
							list.get(guanli).put("pwd", us);
							adapter.notifyDataSetChanged();
							lv1.invalidate();
						}
					});
					builder.show();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!sp.getString("name", "").equals("bizideal")&&sp.getString("pwd", "").equals("123456")) {
					AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
					builder.setTitle("提示");
					builder.setMessage("非管理员用户");
					builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					builder.show();
				}else if (guanli==1000) {
					AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
					builder.setTitle("提示");
					builder.setMessage("请选择要删除的项");
					builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					builder.show();
				}else {
					AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
					builder.setTitle("提示");
					builder.setMessage("确定要删除吗?");
					builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							try {
								strname=list.get(guanli).get("name");
								db.delete("user", "name=?", new String[]{strname});
								
								list.remove(guanli);
								adapter.notifyDataSetChanged();
								lv1.invalidate();
								guanli=1000;
								} catch (Exception e) {
								// TODO: handle exception
							}
						}
					});
					builder.show();
				}
			}
		});
		return view;
	}

}
