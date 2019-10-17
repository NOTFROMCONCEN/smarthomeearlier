package com.example.shengsaiyangjuan10042018.fragment;

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

import com.example.shengsaiyangjuan10042018.R;
import com.example.shengsaiyangjuan10042018.sqlite.MyDataBaseHelper;
import com.example.shengsaiyangjuan10042018.toast.DiyToast;

/*
 * @�ļ�����UpdataPassFragment.java
 * @�������޸�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-4
 */
public class UpdataPassFragment extends Fragment {
	private Button btn_updata_con;
	private EditText et_user, et_pass, et_newpass, et_repass;
	private String user, oldpass, newpass, repass;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_updata_pass, container,
				false);
		initView(view);// �󶨿ؼ�
		btn_updata_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				newpass = et_newpass.getText().toString();// ������
				oldpass = et_pass.getText().toString();// ������
				repass = et_repass.getText().toString();// ȷ������
				if (user.isEmpty() || oldpass.isEmpty() || newpass.isEmpty()
						|| repass.isEmpty()) {
					DiyToast.showToast(getActivity(), "�����пհ���");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { user });// �½����ݿ��α�
					if (cursor.moveToNext()) {// �α��ƶ�
						if (oldpass.equals(cursor.getString(cursor
								.getColumnIndex("passward")))) {
							if (newpass.equals(oldpass)) {
								DiyToast.showToast(getActivity(), "�¾����벻��һ��");
							} else {
								if (newpass.equals(repass)) {
									db.execSQL(
											"update user set passward = ? where username = ?",
											new String[] { newpass, user });
									DiyToast.showToast(getActivity(), "�޸����");
								} else {
									DiyToast.showToast(getActivity(),
											"�������ȷ�����벻һ��");
								}
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

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����10:03:20
	 */
	private void initView(View view) {
		// TODO Auto-generated method stub
		btn_updata_con = (Button) view.findViewById(R.id.btn_updata_con);
		et_newpass = (EditText) view.findViewById(R.id.et_updata_newpass);
		et_pass = (EditText) view.findViewById(R.id.et_updata_oldpass);
		et_repass = (EditText) view.findViewById(R.id.et_updata_repass);
		et_user = (EditText) view.findViewById(R.id.et_updata_user);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
