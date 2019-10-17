package com.example.shengsai2018saixiang918;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;

public class SettingActivity extends Fragment {
	private Button btn_server_resert;// ����
	private Button btn_exit;// �˳�
	PopupWindow popupWindow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_setting, container,
				false);
		btn_server_resert = (Button) view.findViewById(R.id.btn_resert_server);
		btn_exit = (Button) view.findViewById(R.id.btn_exit);
		btn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoginActivity.sharedPreferences.edit()
						.putBoolean("autologin", false)
						// �Զ���¼false
						.putBoolean("rember", false)
						// ��ס����false
						.putString("user", "")// �û���
						.putString("pass", "")// ����
						.putString("ip", "").commit();// Ip��ַ
				getActivity().finish();
			}
		});
		btn_server_resert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater layoutInflater = LayoutInflater
						.from(getActivity());
				View view = layoutInflater.inflate(
						R.layout.activity_server_resert, null, false);
				Button btn_cls = (Button) view
						.findViewById(R.id.btn_server_con);
				final EditText et_ip = (EditText) view
						.findViewById(R.id.et_resert_ip);
				final EditText et_port = (EditText) view
						.findViewById(R.id.et_resert_port);
				popupWindow = new PopupWindow(view, 400, 400);
				popupWindow.setContentView(view);
				popupWindow.showAsDropDown(btn_server_resert);
				popupWindow.setFocusable(true);
				popupWindow.setOutsideTouchable(true);
				popupWindow.setBackgroundDrawable(new ColorDrawable());
				popupWindow.setOnDismissListener(new OnDismissListener() {

					@Override
					public void onDismiss() {
						Toast.makeText(getActivity(), "���óɹ�",
								Toast.LENGTH_SHORT).show();
					}
				});
				btn_cls.setOnLongClickListener(new OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub
						DiyToast.showToast(getActivity(), "����");
						return false;
					}
				});
				btn_cls.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (et_port.getText().toString().isEmpty()
								|| et_ip.getText().toString().isEmpty()) {
							DiyToast.showToast(getActivity(), "��������ֵ");
						} else {
							LoginActivity.sharedPreferences
									.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", "")
									.putString("pass", "")
									.putString("ip", et_ip.getText().toString())
									.commit();
							popupWindow.dismiss();
						}
					}
				});
			}
		});
		return view;
	}
}
