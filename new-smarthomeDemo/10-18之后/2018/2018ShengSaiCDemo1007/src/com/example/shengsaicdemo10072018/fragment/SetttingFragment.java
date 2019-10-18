package com.example.shengsaicdemo10072018.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

import com.example.shengsaicdemo10072018.LoginActivity;
import com.example.shengsaicdemo10072018.R;
import com.example.shengsaicdemo10072018.toast.DiyToast;

public class SetttingFragment extends Fragment {
	private Button btn_server_resert;// ����
	private Button btn_exit;// �˳�
	PopupWindow popupWindow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			final Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_setting, container,
				false);
		// ��
		btn_server_resert = (Button) view.findViewById(R.id.btn_resert_server);
		btn_exit = (Button) view.findViewById(R.id.btn_exit);
		// �˳���ǰ�˺�
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
		// ���������
		/**
		 * --------------------------------------------------------------------
		 * BUG:
		 * --------------------------------------------------------------------
		 * Popwindo�޷���ȡ����
		 */
		btn_server_resert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				show_Pop();
			}
		});
		return view;
	}

	private void show_Pop() {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = new LayoutInflater(getActivity()) {

			@Override
			public LayoutInflater cloneInContext(Context newContext) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		View view = layoutInflater.inflate(R.layout.activity_server_resert,
				null, false);
		popupWindow = new PopupWindow(view, 300, 400, true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),
				(Bitmap) null));
		popupWindow.showAsDropDown(btn_server_resert);
		
	}
	// // TODO Auto-generated method stub
	// final View view = getLayoutInflater(savedInstanceState)
	// .inflate(R.layout.activity_server_resert, null);
	// popupWindow = new PopupWindow(view,
	// ViewGroup.LayoutParams.MATCH_PARENT,
	// ViewGroup.LayoutParams.WRAP_CONTENT, true);
	// popupWindow = new PopupWindow(view, 250, 300);
	// popupWindow.setContentView(view);
	// popupWindow.showAsDropDown(btn_server_resert);
	// // popupWindow.setOutsideTouchable(true);
	// // popupWindow.setBackgroundDrawable(new ColorDrawable());
	// popupWindow.setTouchable(true);
	// popupWindow.setOutsideTouchable(true);
	// popupWindow.setBackgroundDrawable(null);
	// Button btn_cls = (Button) view
	// .findViewById(R.id.btn_server_con);
	// final EditText et_ip = (EditText) view
	// .findViewById(R.id.et_resert_ip);
	// final EditText et_port = (EditText) view
	// .findViewById(R.id.et_resert_port);
	// btn_cls.setOnLongClickListener(new OnLongClickListener() {
	//
	// @Override
	// public boolean onLongClick(View v) {
	// // TODO Auto-generated method stub
	// DiyToast.showToast(getActivity(), "����");
	// popupWindow.dismiss();
	// return false;
	// }
	// });
	// btn_cls.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// if (et_port.getText().toString().isEmpty()
	// || et_ip.getText().toString().isEmpty()) {
	// DiyToast.showToast(getActivity(), "��������ֵ");
	// } else {
	// LoginActivity.sharedPreferences
	// .edit()
	// .putBoolean("autologin", false)
	// .putBoolean("rember", true)
	// .putString(
	// "user",
	// LoginActivity.sharedPreferences
	// .getString("user", null))
	// .putString(
	// "pass",
	// LoginActivity.sharedPreferences
	// .getString("pass", null))
	// .putString("ip", et_ip.getText().toString())
	// .commit();
	// popupWindow.dismiss();
	// new AlertDialog.Builder(getActivity())
	// .setTitle("��ʾ")
	// .setMessage("�޸ĳɹ����Ƿ�ص���¼���棿")
	// .setPositiveButton(
	// "��",
	// new DialogInterface.OnClickListener() {
	//
	// @Override
	// public void onClick(
	// DialogInterface dialog,
	// int which) {
	// // TODO Auto-generated
	// // method stub
	// startActivity(new Intent(
	// getActivity(),
	// LoginActivity.class));
	// getActivity().finish();
	// }
	// })
	// .setNegativeButton(
	// "��",
	// new DialogInterface.OnClickListener() {
	//
	// @Override
	// public void onClick(
	// DialogInterface dialog,
	// int which) {
	// // TODO Auto-generated
	// // method stub
	//
	// }
	// }).show();
	// }
	// }
	// });

}
