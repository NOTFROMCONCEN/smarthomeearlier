package com.example.shengsaie920217;

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

public class UpdataPassActivity extends Fragment {
	private EditText et_updata_user;// �û���
	private EditText et_updata_oldpass;// ������
	private EditText et_updata_newpass;// ������
	private EditText et_updata_repass;// ȷ������
	private Button btn_updata_con;// ȷ��
	private String updata_user, updata_newpass, updata_oldpass, updata_repass;
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_updata_pass, container,
				false);
		et_updata_newpass = (EditText) view
				.findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) view
				.findViewById(R.id.et_updata_oldpass);
		et_updata_repass = (EditText) view.findViewById(R.id.et_updata_repass);
		et_updata_user = (EditText) view.findViewById(R.id.et_updata_user);
		btn_updata_con = (Button) view.findViewById(R.id.btn_updata_con);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_updata_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updata_newpass = et_updata_newpass.getText().toString();// ������
				updata_oldpass = et_updata_oldpass.getText().toString();// ������
				updata_user = et_updata_user.getText().toString();// �û���
				updata_repass = et_updata_repass.getText().toString();// ȷ������
				if (updata_user.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getActivity(), "�û�������Ϊ��");
				} else if (updata_oldpass.isEmpty()) {// ������Ϊ��
					DiyToast.showToast(getActivity(), "�����벻��Ϊ��");
				} else if (updata_newpass.isEmpty()) {// ������Ϊ��
					DiyToast.showToast(getActivity(), "�����벻��Ϊ��");
				} else if (updata_repass.isEmpty()) {// ȷ������Ϊ��
					DiyToast.showToast(getActivity(), "ȷ�����벻��Ϊ��");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });// �½����ݿ��α�
					if (cursor.moveToNext()) {
						String get_old_pasString = cursor.getString(cursor
								.getColumnIndex("passward"));// ��������
						if (updata_oldpass.equals(get_old_pasString)) {
							if (updata_newpass.equals(updata_repass)) {
								if (updata_newpass.equals(updata_oldpass)) {
									DiyToast.showToast(getActivity(),
											"�¾����벻��һ��");
								} else {
									db.execSQL(
											"update user set passward = ? where username = ?",
											new String[] { updata_newpass,
													updata_user });// ��������
									DiyToast.showToast(getActivity(), "���³ɹ�");
									Intent intent = new Intent(getActivity(),
											MainActivity.class);
									startActivity(intent);
									getActivity().finish();
								}
							} else {
								DiyToast.showToast(getActivity(), "�������ȷ�����벻һ��");
							}
						} else {
							DiyToast.showToast(getActivity(), "�������������");
						}
					} else {
						DiyToast.showToast(getActivity(), "�û���������");
					}
				}
			}
		});
		return view;
	}
}