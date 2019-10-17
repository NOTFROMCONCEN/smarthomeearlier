package com.example.remberandautologin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OkActivity extends Activity {
	private LinearLayout line_reg;
	private LinearLayout line_login;
	private TextView tv_tips;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		tv_tips = (TextView) findViewById(R.id.tv_tips);
		tv_tips.setVisibility(View.VISIBLE);
		line_login.setVisibility(View.INVISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
		tv_tips.setText("µÇÂ½³É¹¦");
	}
}
