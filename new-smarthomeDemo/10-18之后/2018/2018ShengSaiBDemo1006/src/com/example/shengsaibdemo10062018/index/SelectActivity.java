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
 * @�ļ�����SelectActivity.java
 * @������ѡ�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-6
 */
public class SelectActivity extends Activity implements OnClickListener {
	private TextView tv_base;// ��������
	private ImageView iv_base;// ����ͼƬ
	private TextView tv_link;// ��������
	private ImageView iv_link;// ����ͼƬ
	private TextView tv_mode;// ģʽ����
	private ImageView iv_mode;// ģʽͼƬ
	private TextView tv_op;// ��������
	private ImageView iv_op;// ����ͼƬ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		setTitle("SelectActivity");
		initView();// ��
		// ��ʼ������
		iv_base.setVisibility(View.VISIBLE);
		iv_link.setVisibility(View.INVISIBLE);
		iv_mode.setVisibility(View.INVISIBLE);
		iv_op.setVisibility(View.INVISIBLE);
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����8:40:03
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
	 * @��������onClick
	 * 
	 * @�� �ܣ����пؼ�����¼�
	 * 
	 * @ʱ �䣺����8:40:13
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
