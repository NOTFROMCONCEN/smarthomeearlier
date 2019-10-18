package com.example.shengsaibdemo10062018.index;

import com.example.shengsaibdemo10062018.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Region.Op;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * @文件名：SelectActivity.java
 * @描述：选择界面
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-6
 */
public class SelectActivity extends Activity implements OnClickListener {
	private TextView tv_base;// 基本文字
	private ImageView iv_base;// 基本图片
	private TextView tv_link;// 联动文字
	private ImageView iv_link;// 联动图片
	private TextView tv_mode;// 模式文字
	private ImageView iv_mode;// 模式图片
	private TextView tv_op;// 管理文字
	private ImageView iv_op;// 管理图片

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		setTitle("SelectActivity");
		initView();// 绑定
		// 初始化隐藏
		iv_base.setVisibility(View.VISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		iv_op.setVisibility(View.INVISIBLE);
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午8:40:03
	 */
	private void initView() {
		// TODO Auto-generated method stub
		tv_base = (TextView) findViewById(R.id.tv_base);
		tv_link = (TextView) findViewById(R.id.tv_link);
		tv_mode = (TextView) findViewById(R.id.tv_mode);
		tv_op = (TextView) findViewById(R.id.tv_op);
		iv_base = (ImageView) findViewById(R.id.iv_base);
		iv_link = (ImageView) findViewById(R.id.iv_link);
		iv_mode = (ImageView) findViewById(R.id.iv_mode);
		iv_op = (ImageView) findViewById(R.id.iv_op);
		tv_base.setOnClickListener(this);
		tv_link.setOnClickListener(this);
		tv_mode.setOnClickListener(this);
		tv_op.setOnClickListener(this);
		iv_base.setOnClickListener(this);
		iv_link.setOnClickListener(this);
		iv_op.setOnClickListener(this);
		iv_mode.setOnClickListener(this);
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：所有控件点击事件
	 * 
	 * @时 间：上午8:40:13
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_base:
			iv_base.setVisibility(View.VISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_op.setVisibility(View.INVISIBLE);
			Intent intent = new Intent(SelectActivity.this, BaseActivity.class);
			startActivity(intent);
			break;
		case R.id.tv_link:
			iv_base.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.VISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_op.setVisibility(View.INVISIBLE);
			Intent intent1 = new Intent(SelectActivity.this, LinkActivity.class);
			startActivity(intent1);
			break;
		case R.id.tv_mode:
			iv_base.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.VISIBLE);
			iv_op.setVisibility(View.INVISIBLE);
			Intent intent11 = new Intent(SelectActivity.this,
					ModeActivity.class);
			startActivity(intent11);
			break;
		case R.id.tv_op:
			iv_base.setVisibility(View.INVISIBLE);
			iv_link.setVisibility(View.INVISIBLE);
			iv_mode.setVisibility(View.INVISIBLE);
			iv_op.setVisibility(View.VISIBLE);
			Intent intent111 = new Intent(SelectActivity.this, OpActivity.class);
			startActivity(intent111);
			break;

		default:
			break;
		}
	}
}
