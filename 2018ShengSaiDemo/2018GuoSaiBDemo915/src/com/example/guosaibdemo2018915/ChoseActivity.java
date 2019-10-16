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
 * @�ļ�����ChoseActivity.java
 * @���������ѡ����ת
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-15
 */
public class ChoseActivity extends Activity implements OnClickListener {
	private ImageView iv_base;// ����
	private ImageView iv_link;// ����
	private ImageView iv_mode;// ģʽ
	private ImageView iv_setting;// ����
	private LinearLayout line_base;// ����
	private LinearLayout line_link;// ����
	private LinearLayout line_mode;// ģʽ
	private LinearLayout line_setting;// ����

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
		// ���õ���¼�
		line_base.setOnClickListener(this);
		line_link.setOnClickListener(this);
		line_mode.setOnClickListener(this);
		line_setting.setOnClickListener(this);
		// ��������
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
			// ����
			iv_base.setVisibility(View.VISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_setting.setVisibility(View.INVISIBLE);
			Intent intent = new Intent(ChoseActivity.this, BaseActivity.class);
			startActivity(intent);
			break;
		case R.id.line_link:
			// ����
			iv_base.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.VISIBLE);
			iv_setting.setVisibility(View.INVISIBLE);
			Intent intent1 = new Intent(ChoseActivity.this, LinkActivity.class);
			startActivity(intent1);
			break;
		case R.id.line_mode:
			// ģʽ
			iv_base.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.VISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_setting.setVisibility(View.INVISIBLE);
			Intent intent11 = new Intent(ChoseActivity.this, ModeActivity.class);
			startActivity(intent11);
			break;
		case R.id.line_setting:
			// ����
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
