package com.example.shengsaiyangjuan10042018;

import com.example.shengsaiyangjuan10042018.fragment.BarActivity;
import com.example.shengsaiyangjuan10042018.sqlite.MyDataBaseHelper;
import com.example.shengsaiyangjuan10042018.toast.DiyToast;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼��ע�ᡢ�޸�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-4
 */
public class LoginActivity extends Activity {
	private Button btn_login;// ��¼
	private Button btn_web;// �������
	private Button btn_reg;// ע��
	private EditText et_user, et_pass;// �û����������ı���
	PopupWindow mPopupWindow_reg;
	PopupWindow mPopupWindow_web;
	private Button btn_reg_con, btn_web_con;
	private String user, pass, ip, port, euser, epass, repass;
	private EditText et_euser, et_epass, et_repass;
	private EditText et_ip, et_port;
	public static SharedPreferences sharedPreferences;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();

		// ע�ᰴť����¼�
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reg_popwindow();
			}
		});
		// ���������ť����¼�
		btn_web.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// web_popwindow();
				DiyToast.showToast(getApplicationContext(),
						"�����ã�Ĭ��IP��17.1.10.2" + "\n" + "�˿ڣ�6006");
			}
		});
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				pass = et_pass.getText().toString();// ����
				if (user.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
				} else if (pass.isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// �½����ݿ��α�
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "��½�ɹ�");
						sharedPreferences.edit().putString("ip", "17.1.10.2")
								.putString("port", "6006").commit();
						Intent intent = new Intent(getApplicationContext(),
								BarActivity.class);
						startActivity(intent);
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(),
								"�û����������������");
					}
				}
			}
		});
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����8:25:01
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_web = (Button) findViewById(R.id.btn_web);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// sharedPreferences
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
	}

	/*
	 * @��������web_popwindow
	 * 
	 * @�� �ܣ��������Popwindow
	 * 
	 * @ʱ �䣺����8:33:00
	 */
	private void web_popwindow() {
		// TODO Auto-generated method stub

		final View popupView = getLayoutInflater().inflate(
				R.layout.popwindow_web, null);
		mPopupWindow_web = new PopupWindow(popupView,
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, true);
		mPopupWindow_web.setTouchable(true);
		mPopupWindow_web.setOutsideTouchable(true);
		mPopupWindow_web.setBackgroundDrawable(new BitmapDrawable(
				getResources(), (Bitmap) null));
		mPopupWindow_reg.showAsDropDown(btn_web);
		et_ip = (EditText) popupView.findViewById(R.id.et_ip);
		et_port = (EditText) popupView.findViewById(R.id.et_port);
		btn_web_con = (Button) popupView.findViewById(R.id.btn_web_con);
		btn_web_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ip = et_ip.getText().toString();// IP
				port = et_port.getText().toString();// port
				if (ip.isEmpty()) {// IP��ַΪ��
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else if (port.isEmpty()) {// �˿�Ϊ��
					DiyToast.showToast(getApplicationContext(), "������˿ں�");
				} else {
					LoginActivity.sharedPreferences.edit().putString("ip", ip)
							.putString("port", port).commit();
					DiyToast.showToast(getApplicationContext(), "���óɹ�");
				}
			}
		});
	}

	/*
	 * @��������reg_popwindow
	 * 
	 * @�� �ܣ�ע��Popwindow
	 * 
	 * @ʱ �䣺����8:33:00
	 */
	private void reg_popwindow() {
		// TODO Auto-generated method stub

		final View popupView = getLayoutInflater().inflate(
				R.layout.popwindow_reg, null);
		mPopupWindow_reg = new PopupWindow(popupView,
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, true);
		mPopupWindow_reg.setTouchable(true);
		mPopupWindow_reg.setOutsideTouchable(true);
		mPopupWindow_reg.setBackgroundDrawable(new BitmapDrawable(
				getResources(), (Bitmap) null));
		mPopupWindow_reg.showAsDropDown(btn_reg);
		btn_reg_con = (Button) popupView.findViewById(R.id.btn_reg_con);
		et_euser = (EditText) popupView.findViewById(R.id.et_euser);
		et_epass = (EditText) popupView.findViewById(R.id.et_epass);
		et_repass = (EditText) popupView.findViewById(R.id.et_repass);
		btn_reg_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				euser = et_euser.getText().toString();// �û���
				epass = et_epass.getText().toString();// ����
				repass = et_repass.getText().toString();// ȷ������
				if (euser.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (epass.isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "����������");
				} else if (repass.isEmpty()) {// ȷ������Ϊ��
					DiyToast.showToast(getApplicationContext(), "������ȷ������");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// �½��α�
					if (cursor.moveToNext()) {// �α��ƶ�
						DiyToast.showToast(getApplicationContext(), "�û����ѱ�ע��");
					} else {
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] { euser, epass });// �������ݿ�
							DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
							mPopupWindow_reg.dismiss();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"�����������벻һ��");
						}
					}
				}
			}
		});
	}
}
