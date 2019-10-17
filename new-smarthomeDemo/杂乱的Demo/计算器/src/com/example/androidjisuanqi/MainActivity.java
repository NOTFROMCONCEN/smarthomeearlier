package com.example.androidjisuanqi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private Button btn_jia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_jia = (Button) findViewById(R.id.btn_jia);
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		btn_jia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (editText1.getText().toString().isEmpty()
						|| editText2.getText().toString().isEmpty()) {
				} else {
					Integer int_1 = Integer.valueOf(editText1.getText()
							.toString());
					Integer int_2 = Integer.valueOf(editText2.getText()
							.toString());
					String string_out = String.valueOf(int_1 + int_2);
					editText3.setText(string_out);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
