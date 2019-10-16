package com.example.guosaiedemo22019;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import lib.SocketThread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnSeekBarChangeListener {
	private Button btn_login;
	private EditText et_port, et_ip;
	private String port, ip;
	private TextView tv_num_state;
	private SeekBar sk_1;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private int recLen = 0;
	Double num;
	private String time, gettime, getnum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// �󶨿ؼ�
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		tv_num_state = (TextView) findViewById(R.id.tv_state);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sk_1.setOnSeekBarChangeListener(this);
		// �����ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// �½����ݿ�ָ�����ڼ�¼��¼��������
		Cursor cursor = db.rawQuery("select * from user", null);
		DecimalFormat df = new DecimalFormat("##0");

		if (cursor.getCount() != 0) {
			// ������ݿ������ݣ�ָ������
			cursor.moveToLast();
			// �õ����ݿ��ڲ����ݲ�ת��ΪString��ֵ
			getnum = cursor.getString(cursor.getColumnIndex("loginnum"));
			gettime = cursor.getString(cursor.getColumnIndex("logintime"));
			// ����¼����String��ֵת��Ϊ����
			num = Double.valueOf(getnum);
			// �����ı�
			tv_num_state.setText("֮ǰ����" + df.format(num) + "�ε�¼���ϴε�¼ʱ��Ϊ"
					+ gettime);
		} else {
			// �������Ϊ�գ���ֹ�����ı�����Ϊ�̶�ֵ
			tv_num_state.setText("֮ǰ����null�ε�¼���ϴε�¼ʱ��ΪHH:mm");
		}
		// �½�SharedPreferences,���ڼ�ס�ϴ��������ֵ
		final SharedPreferences rember = getSharedPreferences("rember",
				MODE_WORLD_WRITEABLE);
		if (rember != null) {
			String autoip, autoport;
			autoip = rember.getString("ip", null);
			autoport = rember.getString("port", null);
			// �Ƚ�booleanֵ
			if (rember.getBoolean("rember", false) == true) {
				et_ip.setText(autoip);
				et_port.setText(autoport);
			} else {
				et_ip.setText("");
				et_port.setText("");
			}
		} else {
			et_ip.setText("");
			et_port.setText("");
		}
		// ��½����
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				port = et_port.getText().toString();
				ip = et_ip.getText().toString();
				if (port.equals("")) {
					Toast.makeText(MainActivity.this, "������������˿ں�",
							Toast.LENGTH_SHORT).show();
				} else if (ip.equals("")) {
					Toast.makeText(MainActivity.this, "�����������IP��ַ",
							Toast.LENGTH_SHORT).show();
				} else {
					if (sk_1.getProgress() == sk_1.getMax()) {
						num++;
						String setnumString = Double.toString(num);
						db.execSQL(
								"insert into user (loginnum,logintime)values(?,?)",
								new String[] { setnumString, time });
						Toast.makeText(MainActivity.this, "��¼���",
								Toast.LENGTH_SHORT).show();
						rember.edit().putBoolean("rember", true)
								.putString("port", port).putString("ip", ip)
								.commit();
						SocketThread.SocketIp = ip;
						SocketThread.Port = Integer.valueOf(port);
						Intent intent = new Intent(MainActivity.this,
								BaseActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(MainActivity.this, "�뻬�������֤",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// ��ȡ��ǰʱ���Handler����
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
			formater.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			time = formater.format(new java.util.Date());
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	// SeekBar���ܵ�ʵ��
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		if (seekBar.getProgress() != seekBar.getMax()) {
			Toast.makeText(MainActivity.this, "�뻬�������֤", Toast.LENGTH_SHORT)
					.show();
			sk_1.setProgress(0);
		}
	}
}
