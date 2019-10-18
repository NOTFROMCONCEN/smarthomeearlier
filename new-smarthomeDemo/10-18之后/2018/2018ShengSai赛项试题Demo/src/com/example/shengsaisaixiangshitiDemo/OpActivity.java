package com.example.shengsaisaixiangshitiDemo;

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
 * @文件名：OpActivity.java
 * @描述：账户管理
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-16
 */
public class OpActivity extends Fragment {
	private Button btn_delete;// 删除
	private Button btn_updata_pass;// 修改密码
	private ListView lv_1;
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	String get_user, get_pass;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_op, container, false);
		// 绑定
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		btn_delete = (Button) view.findViewById(R.id.btn_delete);
		btn_updata_pass = (Button) view.findViewById(R.id.btn_updata_pass);
		// 设置ListView选择模式（暂时弃用，无效果）
		lv_1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		// 修改密码点击
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
				builder.setPositiveButton("确定",
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
								DiyToast.showToast(getActivity(), "修改成功");
								LoginActivity.sharedPreferences.edit()
										.putBoolean("autologin", false)
										.putBoolean("rember", false)
										.putString("user", "")
										.putString("pass", "")
										.putString("ip", "").commit();
								getActivity().finish();
							}
						});
				builder.setTitle("修改密码");
				builder.show();
			}
		});
		// 判断登录账户是否有管理员权限
		/**
		 * ------------------------------------------------------------------ --
		 * 方法详解：
		 * --------------------------------------------------------------------
		 * 此方法为暂行测试，会受Fragment预加载功能影响，需查阅资料
		 * 目前解决方法：1.依靠预加载读取从LoginActivity.java传来的数值（管理员、用户） 2.读取数据库 3.解决预加载问题
		 * 
		 * 
		 * --------------------------------------------------------------------
		 * BUG：
		 * --------------------------------------------------------------------
		 * 手动滑动至此界面，预加载不会启动，此方法不会启用，但是使用自建的RadioButton由基本界面切换至此界面，本方法被启用
		 * 
		 */
		if (BarActivity.ra_op.isChecked()) {
			if (LoginActivity.login_op.equals("用户")) {
				new AlertDialog.Builder(getActivity())
						.setTitle("提示")
						.setMessage("你非管理员账号不能进行该操作")
						.setPositiveButton("确定",
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
		// 同设置ListView选择模式，弃用
		lv_1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// 设置ListView点击事件
		lv_1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
				get_user = cursor.getString(1);
				get_pass = cursor.getString(2);
				DiyToast.showToast(getActivity(), "即将进行操作的用户：" + get_user);
			}
		});
		// 删除按钮
		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (get_user.isEmpty()) {
					new AlertDialog.Builder(getActivity())
							.setTitle("提示")
							.setMessage("请选择要删除的账号信息？")
							.setPositiveButton("确定",
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
							.setTitle("提示")
							.setMessage("确定要删除吗？")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											if (get_user.equals("bizideal")) {
												DiyToast.showToast(
														getActivity(),
														"你不能删除管理员账户");
											} else {
												db.execSQL(
														"delete from user where username = ? and passward = ?",
														new String[] {
																get_user,
																get_pass });// 删除数据库
												getdata();// 更新
											}
										}
									}).show();
				}
			}
		});
		// 插入
		getdata();

		return view;
	}

	/*
	 * @方法名：getdata
	 * 
	 * @功 能：更新数据库内容至ListView
	 * 
	 * @时 间：上午10:19:51
	 */
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