package com.example.listviewandcheckboxforsqlitedemo4.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO ×Ô¶¨ÒåToast
 * @package_name com.example.listviewandcheckboxforsqlitedemo4.toast
 * @project_name ListViewAndCheckBoxForSQLiteDemo4
 * @file_name DiyToast.java
 */
public class DiyToast {
	public static Toast toast;

	public static void showToast(Context context, String string) {
		if (toast == null) {
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			toast.setText(string);
		}
		toast.show();
	}
}
