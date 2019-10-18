package com.example.guosaibdemo2018915;

import android.app.Activity;
import android.content.Intent;
import android.graphics.AvoidXfermode.Mode;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

/*
 * @文件名：ChoseActivity.java
 * @描述：完成选择跳转
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-15
 */
public class ChoseActivity extends Activity implements OnClickListener {
	private ImageView iv_base;// 基本
	private ImageView iv_link;// 联动
	private ImageView iv_mode;// 模式
	private ImageView iv_setting;// 管理
	private LinearLayout line_base;// 基本
	private LinearLayout line_link;// 联动
	private LinearLayout line_mode;// 模式
	private LinearLayout line_setting;// 管理

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose);
		iv_base = (ImageView) findViewById(R.id.iv_base);
		iv_link = (ImageView) findViewById(R.id.iv_link);
		iv_mode = (ImageView) findViewById(R.id.iv_mode);
		iv_setting = (ImageView) findViewById(R.id.iv_setting);
		line_base = (LinearLayout) findViewById(R.id.line_base);
		line_link = (LinearLayout) findViewById(R.id.line_link);
		line_mode = (LinearLayout) findViewById(R.id.line_mode);
		line_setting = (LinearLayout) findViewById(R.id.line_setting);
		// 设置点击事件
		line_base.setOnClickListener(this);
		line_link.setOnClickListener(this);
		line_mode.setOnClickListener(this);
		line_setting.setOnClickListener(this);
		// 设置显隐
		iv_base.setVisibility(View.VISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_setting.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_base:
			// 基本
			iv_base.setVisibility(View.VISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_setting.setVisibility(View.INVISIBLE);
			Intent intent = new Intent(ChoseActivity.this, BaseActivity.class);
			startActivity(intent);
			break;
		case R.id.line_link:
			// 联动
			iv_base.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.VISIBLE);
			iv_setting.setVisibility(View.INVISIBLE);
			Intent intent1 = new Intent(ChoseActivity.this, LinkActivity.class);
			startActivity(intent1);
			break;
		case R.id.line_mode:
			// 模式
			iv_base.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.VISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_setting.setVisibility(View.INVISIBLE);
			Intent intent11 = new Intent(ChoseActivity.this, ModeActivity.class);
			startActivity(intent11);
			break;
		case R.id.line_setting:
			// 设置
			iv_base.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_setting.setVisibility(View.VISIBLE);
			Intent intent111 = new Intent(ChoseActivity.this,
					SettingActivity.class);
			startActivity(intent111);
			break;

		default:
			break;
		}
	}

}
