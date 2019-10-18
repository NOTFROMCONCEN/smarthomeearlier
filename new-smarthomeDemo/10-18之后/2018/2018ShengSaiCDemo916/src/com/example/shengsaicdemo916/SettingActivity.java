package com.example.shengsaicdemo916;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SettingActivity extends Fragment {
	private Button btn_server_resert;// ����
	private Button btn_exit;// �˳�

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
		return view;
	}
}
