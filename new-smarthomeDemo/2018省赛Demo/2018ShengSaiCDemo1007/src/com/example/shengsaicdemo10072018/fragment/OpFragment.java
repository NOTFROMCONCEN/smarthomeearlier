package com.example.shengsaicdemo10072018.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.shengsaicdemo10072018.LoginActivity;
import com.example.shengsaicdemo10072018.R;
import com.example.shengsaicdemo10072018.mysql.MyDataBaseHelper;
import com.example.shengsaicdemo10072018.toast.DiyToast;

/*
 * @�ļ�����OpFragment.java
 * @�������˻�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-7
 */
public class OpFragment extends Fragment {
	private Button btn_updata_pass;// �޸����밴ť
	private Button btn_del;// ɾ����ť
	private ListView lv_1;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	private String get_user = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_op, container, false);
		btn_del = (Button) view.findViewById(R.id.btn_del);
		btn_updata_pass = (Button) view.findViewById(R.id.btn_updata_pass);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		getdata();// ����
		// ListView���ѡ���˻�����
		lv_1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
				get_user = cursor.getString(1);
				DiyToast.showToast(getActivity(), "��ѡ���ˣ�" + get_user);

			}
		});
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
				LayoutInflater layoutInflater = LayoutInflater
						.from(getActivity());
				final View view = layoutInflater.inflate(
						R.layout.dialog_updata_pass, null, false);
				b.setView(view);
				b.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								EditText et_updata_pass = (EditText) view
										.findViewById(R.id.et_updata_pass);
								if (et_updata_pass.getText().toString()
										.isEmpty()) {
									DiyToast.showToast(getActivity(), "��ֵ����Ϊ��");
								} else {
									if (get_user.isEmpty()) {
										DiyToast.showToast(getActivity(),
												"��ѡ���û���");
									} else {
										db.execSQL(
												"update user set passward = ? where username = ?",
												new String[] {
														et_updata_pass
																.getText()
																.toString(),
														get_user });
										DiyToast.showToast(getActivity(),
												"�޸ĳɹ�");
										LoginActivity.sharedPreferences = getActivity()
												.getSharedPreferences(
														"rember",
														getActivity().MODE_WORLD_WRITEABLE);
										LoginActivity.sharedPreferences.edit()
												.putBoolean("rember", false)
												.putBoolean("autologin", false)
												.putString("user", "")
												.putString("pass", "")
												.putString("ip", "").commit();
										startActivity(new Intent(getActivity(),
												LoginActivity.class));
										getActivity().finish();
									}
								}
							}
						});
				b.show();
			}
		});
		// ɾ����ť
		btn_del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (get_user.isEmpty()) {
					DiyToast.showToast(getActivity(), "����ѡ��");
				} else {
					if (get_user.equals("bizideal")) {
						DiyToast.showToast(getActivity(), "�㲻��ɾ������Ա�˺�");
					} else {
						new AlertDialog.Builder(getActivity())
								.setTitle("����")
								.setMessage("��ȷ��Ҫɾ���˺���Ϣ��")
								.setPositiveButton("ȷ��",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												delete();
											}
										})
								.setNegativeButton("ȡ��",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

											}
										}).show();
					}
				}
			}
		});

		return view;
	}

	private void delete() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(getActivity()).setTitle("����")
				.setMessage("ȷ��Ҫɾ����")
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method
						// stub
						db.execSQL("delete * from user where username = ?",
								new String[] { get_user });
					}
				}).show();
	}

	/*
	 * @��������getdata
	 * 
	 * @�� �ܣ�����ListVioew����
	 * 
	 * @ʱ �䣺����10:11:02
	 */
	private void getdata() {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery("select * from user", null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
				R.layout.activity_op_text, c, new String[] { "username",
						"passward" }, new int[] { R.id.tv_username,
						R.id.tv_passward });
		lv_1.setAdapter(adapter);
	}
}