package com.example.shengsaiedemo9172018;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/*
 * @�ļ�����OpActivity.java
 * @�������˻�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-16
 */
public class OpActivity extends Fragment {
	private Button btn_delete;// ɾ��
	private Button btn_updata_pass;// �޸�����
	private ListView lv_1;
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	String get_user, get_pass;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_op, container, false);
		System.out.println("Fragment��������OP 3��");
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		btn_delete = (Button) view.findViewById(R.id.btn_delete);
		btn_updata_pass = (Button) view.findViewById(R.id.btn_updata_pass);
		lv_1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				LayoutInflater layoutInflater = LayoutInflater
						.from(getActivity());
				final View view = layoutInflater.inflate(R.layout.dialog_text,
						null, false);
				builder.setView(view);
				builder.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								final EditText et_updata_pass_get = (EditText) view
										.findViewById(R.id.et_updata_pass_get);
								db.execSQL(
										"update user set passward = ? where username = ?",
										new String[] {
												et_updata_pass_get.getText()
														.toString(), get_user });
								DiyToast.showToast(getActivity(), "�޸ĳɹ�");
								LoginActivity.sharedPreferences.edit()
										.putBoolean("autologin", false)
										.putBoolean("rember", false)
										.putString("user", "")
										.putString("pass", "")
										.putString("ip", "").commit();
								getActivity().finish();
							}
						});
				builder.setTitle("�޸�����");
				builder.show();
			}
		});
		if (BarActivity.ra_op.isChecked()) {
			if (LoginActivity.login_op.equals("�û�")) {
				new AlertDialog.Builder(getActivity())
						.setTitle("��ʾ")
						.setMessage("��ǹ���Ա�˺Ų��ܽ��иò���")
						.setPositiveButton("ȷ��",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated
										// method stub
										BarActivity.mViewPager
												.setCurrentItem(0);
										BarActivity.ra_base.setChecked(true);
									}
								}).show();
			}
		}
		lv_1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// ����ListView
		lv_1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
				get_user = cursor.getString(1);
				get_pass = cursor.getString(2);
				DiyToast.showToast(getActivity(), "�������в������û���" + get_user);
			}
		});
		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (get_user.isEmpty()) {
					new AlertDialog.Builder(getActivity())
							.setTitle("��ʾ")
							.setMessage("��ѡ��Ҫɾ�����˺���Ϣ��")
							.setPositiveButton("ȷ��",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();
				} else {
					new AlertDialog.Builder(getActivity())
							.setTitle("��ʾ")
							.setMessage("ȷ��Ҫɾ����")
							.setPositiveButton("ȷ��",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											if (get_user.equals("bizideal")) {
												DiyToast.showToast(
														getActivity(),
														"�㲻��ɾ������Ա�˻�");
											} else {
												db.execSQL(
														"delete from user where username = ? and passward = ?",
														new String[] {
																get_user,
																get_pass });
												getdata();
											}
										}
									}).show();
				}
			}
		});
		// ����
		getdata();

		return view;
	}

	private void getdata() {
		Cursor c = db.rawQuery("select * from user", null);
		c.moveToFirst();
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
				getActivity(), R.layout.activity_text, c, new String[] {
						"username", "passward" }, new int[] { R.id.tv_user,
						R.id.tv_pass });
		lv_1.setAdapter(simpleCursorAdapter);
	}
}