package com.example.guosaiddemo907;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/*
 * @文件名：ChoseActivity.java
 * @描述：选择功能界面
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-7
 */
public class ChoseActivity extends Activity implements OnClickListener {
	private ImageView iv_chuanganqi;// 传感器按钮
	private ImageView iv_shebeikongzhi;// 传感器按钮
	private ImageView iv_qingjingmoshi;// 传感器按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chose);
		iv_chuanganqi = (ImageView) findViewById(R.id.iv_chuanganqi);
		iv_qingjingmoshi = (ImageView) findViewById(R.id.iv_qingjingmoshi);
		iv_shebeikongzhi = (ImageView) findViewById(R.id.iv_shebeikongzhi);
		// 设置点击事件
		iv_chuanganqi.setOnClickListener(this);
		iv_qingjingmoshi.setOnClickListener(this);
		iv_shebeikongzhi.setOnClickListener(this);
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：监听点击事件
	 * 
	 * @时 间：上午9:11:20
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_chuanganqi:
			Intent intent = new Intent(ChoseActivity.this, BaseActivity.class);
			startActivity(intent);
			break;
		case R.id.iv_qingjingmoshi:
			Intent intent2 = new Intent(ChoseActivity.this, LinkActivity.class);
			startActivity(intent2);
			break;
		case R.id.iv_shebeikongzhi:
			Intent intent3 = new Intent(ChoseActivity.this,
					ControulActivity.class);
			startActivity(intent3);
			break;
		default:
			break;
		}
	}
}
