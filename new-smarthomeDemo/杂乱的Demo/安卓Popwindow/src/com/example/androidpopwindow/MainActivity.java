package com.example.androidpopwindow;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn_open;
	private PopupWindow popupWindow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_open = (Button) findViewById(R.id.btn_open);
		btn_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				makePopupWindows();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void popwindow() {
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		View view = layoutInflater
				.inflate(R.layout.popwindow_main, null, false);
		Button btn_cls = (Button) view.findViewById(R.id.btn_cls);
		EditText et_numbe = (EditText) view.findViewById(R.id.editText1);
		popupWindow = new PopupWindow(view, 400, 400);
		popupWindow.setContentView(view);
		popupWindow.showAsDropDown(btn_open);
		popupWindow.setFocusable(true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				Toast.makeText(getApplicationContext(), "Popwindow被关闭",
						Toast.LENGTH_SHORT).show();
			}
		});
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
			}
		});
	}

	/**
	 * 点击评分，如果评分后，显示的弹出框
	 */
	private void makePopupWindows() {
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.popwindow_main, null);
		popupWindow = new PopupWindow(view, 600, 600);
		// 设置背景颜色
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.alpha = 0.5f;
		getWindow().setAttributes(params);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
		popupWindow.setTouchable(true); // 设置PopupWindow可触摸
		popupWindow.showAsDropDown(btn_open);
		view.setFocusableInTouchMode(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		view.setOnKeyListener(new android.view.View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK && popupWindow.isShowing()) {
					popupWindow.dismiss();
					WindowManager.LayoutParams params = getWindow()
							.getAttributes();
					params.alpha = 1.0f;
					getWindow().setAttributes(params);
					return true;
				}
				return false;
			}
		});
	}
}