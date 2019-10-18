package com.example.shengsaiyangjuan9172018;

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

public class UpdataActivity extends Fragment {
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// ����
	private Button btn_updata_con;

	private EditText et_updata_newpass;
	private EditText et_updata_oldpass;
	private EditText et_updata_user;
	private EditText et_updata_repass;

	private String updata_newpass, updata_oldpass, updata_user, repass;

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
		et_updata_user = (EditText) view.findViewById(R.id.et_updata_user);
		btn_updata_con = (Button) view.findViewById(R.id.btn_updata_con);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_updata_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// �޸�����ȷ��
				updata_newpass = et_updata_newpass.getText().toString();// ������
				updata_oldpass = et_updata_oldpass.getText().toString();// ������
				updata_user = et_updata_user.getText().toString();
				if (updata_user.equals("")) {// ����û���Ϊ��
					DiyToast.showToast(getActivity(), "�������û���");
				} else if (updata_oldpass.equals("")) {// ���������Ϊ��
					DiyToast.showToast(getActivity(), "�����������");
				} else if (updata_newpass.equals("")) {// ���������Ϊ��
					DiyToast.showToast(getActivity(), "������������");
				} else {
					if (!updata_newpass.equals(updata_oldpass)) {

						Cursor cur_updata_user = db.rawQuery(
								"select * from user where username = ?",
								new String[] { updata_user });// �½����ݿ�ָ��
						cur_updata_user.moveToFirst();
						String get_passString = cur_updata_user
								.getString(cur_updata_user
										.getColumnIndex("passward"));
						if (get_passString.equals(updata_oldpass)) {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { updata_newpass, updata_user });// �������ݿ�
							DiyToast.showToast(getActivity(), "�����޸ĳɹ�");
						} else {
							DiyToast.showToast(getActivity(), "�������������");
						}
					} else {
						DiyToast.showToast(getActivity(), "�¾����벻��һ��");
					}
				}
			}
		});
		return view;
	}
}
