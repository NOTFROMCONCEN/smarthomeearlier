package com.example.guosaifdemo910;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class BaseActivity extends Fragment {
	static float temp, smo, hum, press, ill, gas, co, pm, per;
	private RadioGroup rg_state, rg_open_cls, rg_check_draw;
	private RadioButton ra_tv, ra_dvd, ra_cls, ra_kongtiao, ra_open, ra_stop,
			ra_lamp_left, ra_lamp_right, right, ra_cur, ra_door, ra_fan;
	private SeekBar sk_1;
	private TextView tv_left_number, ra_right_number;
	private int count;
	private String s_temp, s_hum, s_ill, s_smo, s_gas, s_press, s_co, s_pm,
			s_per;
	private RadioButton ra_temp, ra_hum, ra_ill, ra_gas, ra_press, ra_smo,
			ra_pm, ra_co, ra_per, ra_test_number;
	private MydataBaseHelper dbHelper;
	private SQLiteDatabase db;
	static float getdata;
	static String num;
	private View myview01, myview02;
	private String radio_state, radio_name;
	private Random random = new Random();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
		ra_cur = (RadioButton) view.findViewById(R.id.ra_cur);
		ra_door = (RadioButton) view.findViewById(R.id.ra_door);
		ra_fan = (RadioButton) view.findViewById(R.id.ra_fan);
		ra_kongtiao = (RadioButton) view.findViewById(R.id.ra_kongtiao);
		ra_lamp_left = (RadioButton) view.findViewById(R.id.ra_lamp_left);
		ra_lamp_right = (RadioButton) view.findViewById(R.id.ra_lamp_right);
		ra_tv = (RadioButton) view.findViewById(R.id.ra_tv);
		ra_dvd = (RadioButton) view.findViewById(R.id.ra_dvd);
		rg_state = (RadioGroup) view.findViewById(R.id.rg_state);
		myview01 = (View) view.findViewById(R.id.myview01);
		myview02 = (View) view.findViewById(R.id.myview02);
		rg_check_draw = (RadioGroup) view.findViewById(R.id.rg_check_draw);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		ra_hum = (RadioButton) view.findViewById(R.id.ra_hum);
		ra_ill = (RadioButton) view.findViewById(R.id.ra_ill);
		ra_gas = (RadioButton) view.findViewById(R.id.ra_gas);
		ra_smo = (RadioButton) view.findViewById(R.id.ra_smo);
		ra_pm = (RadioButton) view.findViewById(R.id.ra_pm);
		ra_co = (RadioButton) view.findViewById(R.id.ra_co);
		ra_per = (RadioButton) view.findViewById(R.id.ra_per);
		ra_press = (RadioButton) view.findViewById(R.id.ra_press);
		ra_test_number = (RadioButton) view.findViewById(R.id.ra_test_number);
		dbHelper = new MydataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 程序启动插入数据库
		db.execSQL("insert into radiostate(radioname,radiostate)values(?,?)",
				new String[] { "temp", "open" });

		myview01.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				myview01.setVisibility(View.INVISIBLE);
				myview02.setVisibility(View.VISIBLE);
				return false;
			}
		});
		myview02.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				myview01.setVisibility(View.VISIBLE);
				myview02.setVisibility(View.INVISIBLE);
				return false;
			}
		});
		myview01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				radiocheckdialog();
			}
		});
		myview02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				radiocheckdialog();
			}
		});

		count = 1;
		sqliteupdata.post(timeRunnable);
		return view;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void radiocheckdialog() {
		dbHelper = new MydataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				getActivity());
		LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
		final View view = layoutInflater.inflate(R.layout.radio_state, null);
		builder.setView(view);
		final RadioButton ra_temp_dialog = (RadioButton) view
				.findViewById(R.id.ra_temp_dialog);
		final RadioButton ra_hum_dialog = (RadioButton) view
				.findViewById(R.id.ra_hum_dialog);
		final RadioButton ra_ill_dialog = (RadioButton) view
				.findViewById(R.id.ra_ill_dialog);
		final RadioButton ra_gas_dialog = (RadioButton) view
				.findViewById(R.id.ra_gas_dialog);
		final RadioButton ra_press_dialog = (RadioButton) view
				.findViewById(R.id.ra_press_dialog);
		final RadioButton ra_smo_dialog = (RadioButton) view
				.findViewById(R.id.ra_smo_dialog);
		final RadioButton ra_pm_dialog = (RadioButton) view
				.findViewById(R.id.ra_pm_dialog);
		final RadioButton ra_co_dialog = (RadioButton) view
				.findViewById(R.id.ra_co_dialog);
		final RadioButton ra_per_dialog = (RadioButton) view
				.findViewById(R.id.ra_per_dialog);
		final RadioButton ra_test_dialog = (RadioButton) view
				.findViewById(R.id.ra_test_number_dialog);
		ra_temp_dialog.setChecked(true);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (ra_co_dialog.isChecked()) {
					Toast.makeText(getActivity(), "Co2被选择", Toast.LENGTH_SHORT)
							.show();
					db.execSQL(
							"insert into radiostate(radioname,radiostate)values(?,?)",
							new String[] { "co", "open" });
				}
				if (ra_test_dialog.isChecked()) {
					Toast.makeText(getActivity(), "测试参数被选择", Toast.LENGTH_SHORT)
							.show();
					db.execSQL(
							"insert into radiostate(radioname,radiostate)values(?,?)",
							new String[] { "test", "open" });
				}
				if (ra_temp_dialog.isChecked()) {
					Toast.makeText(getActivity(), "温度被选择", Toast.LENGTH_SHORT)
							.show();
					db.execSQL(
							"insert into radiostate(radioname,radiostate)values(?,?)",
							new String[] { "temp", "open" });
				}
				if (ra_hum_dialog.isChecked()) {
					Toast.makeText(getActivity(), "湿度被选择", Toast.LENGTH_SHORT)
							.show();
					db.execSQL(
							"insert into radiostate(radioname,radiostate)values(?,?)",
							new String[] { "hum", "open" });
				}
				if (ra_gas_dialog.isChecked()) {
					Toast.makeText(getActivity(), "燃气被选择", Toast.LENGTH_SHORT)
							.show();
					db.execSQL(
							"insert into radiostate(radioname,radiostate)values(?,?)",
							new String[] { "gas", "open" });
				}
				if (ra_ill_dialog.isChecked()) {
					Toast.makeText(getActivity(), "光照被选择", Toast.LENGTH_SHORT)
							.show();
					db.execSQL(
							"insert into radiostate(radioname,radiostate)values(?,?)",
							new String[] { "ill", "open" });
				}
				if (ra_per_dialog.isChecked()) {
					Toast.makeText(getActivity(), "人体红外被选择", Toast.LENGTH_SHORT)
							.show();
					db.execSQL(
							"insert into radiostate(radioname,radiostate)values(?,?)",
							new String[] { "per", "open" });
				}
				if (ra_pm_dialog.isChecked()) {
					Toast.makeText(getActivity(), "PM2.5被选择",
							Toast.LENGTH_SHORT).show();
					db.execSQL(
							"insert into radiostate(radioname,radiostate)values(?,?)",
							new String[] { "pm", "open" });
				}
				if (ra_press_dialog.isChecked()) {
					Toast.makeText(getActivity(), "气压被选择", Toast.LENGTH_SHORT)
							.show();
					db.execSQL(
							"insert into radiostate(radioname,radiostate)values(?,?)",
							new String[] { "press", "open" });
				}
				if (ra_smo_dialog.isChecked()) {
					Toast.makeText(getActivity(), "烟雾被选择", Toast.LENGTH_SHORT)
							.show();
					db.execSQL(
							"insert into radiostate(radioname,radiostate)values(?,?)",
							new String[] { "smo", "open" });
				}

			}
		});
		builder.show();
	}

	Handler sqliteupdata = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			dbHelper = new MydataBaseHelper(getActivity(), "info.db", null, 2);
			db = dbHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery("select * from radiostate", null);
			if (cursor.getCount() != 0) {
				cursor.moveToLast();
				radio_state = cursor.getString(cursor
						.getColumnIndex("radiostate"));
				radio_name = cursor.getString(cursor
						.getColumnIndex("radioname"));
				if (radio_name.equals("co")) {
					if (radio_state.equals("open")) {
						ra_co.setChecked(true);
					}
				}
				if (radio_name.equals("temp")) {
					if (radio_state.equals("open")) {
						ra_temp.setChecked(true);
					}
				}
				if (radio_name.equals("hum")) {
					if (radio_state.equals("open")) {
						ra_hum.setChecked(true);
					}
				}
				if (radio_name.equals("gas")) {
					if (radio_state.equals("open")) {
						ra_gas.setChecked(true);
					}
				}
				if (radio_name.equals("pm")) {
					if (radio_state.equals("open")) {
						ra_pm.setChecked(true);
					}
				}
				if (radio_name.equals("ill")) {
					if (radio_state.equals("open")) {
						ra_ill.setChecked(true);
					}
				}
				if (radio_name.equals("pm")) {
					if (radio_state.equals("open")) {
						ra_pm.setChecked(true);
					}
				}
				if (radio_name.equals("per")) {
					if (radio_state.equals("open")) {
						ra_per.setChecked(true);
					}
				}
				if (radio_name.equals("test")) {
					if (radio_state.equals("open")) {
						ra_test_number.setChecked(true);
					}
				}

			}

			// ////
			if (ra_temp.isChecked()) {
				getdata = temp;
				num = "10";
			}
			if (ra_hum.isChecked()) {
				getdata = hum;
				num = "20";
			}
			if (ra_ill.isChecked()) {
				getdata = ill;
				num = "30";
			}
			if (ra_gas.isChecked()) {
				getdata = gas;
				num = "40";
			}
			if (ra_press.isChecked()) {
				getdata = press;
				num = "50";
			}
			if (ra_smo.isChecked()) {
				getdata = smo;
				num = "60";
			}
			if (ra_pm.isChecked()) {
				getdata = pm;
				num = "70";
			}
			if (ra_co.isChecked()) {
				getdata = co;
				num = "80";
			}
			if (ra_per.isChecked()) {
				getdata = per;
				num = "90";
			}
			if (ra_test_number.isChecked()) {
				getdata = Float.parseFloat("60");
				num = "test";
			}
			temp = Float.valueOf(random.nextInt(40) % (40 - 20 + 1));// 温度
			hum = Float.valueOf(random.nextInt(40) % (120 - 10 + 1));// 湿度
			gas = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// 燃气
			smo = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// 烟雾
			ill = Float.valueOf(random.nextInt(9999) % (9999 - 500 + 1));// 光照
			co = Float.valueOf(random.nextInt(40) % (100 - 10 + 1));// Co2
			pm = Float.valueOf(random.nextInt(40) % (100 - 40 + 1));// pm2.5
			press = Float.valueOf(random.nextInt(1800) % (1800 - 10 + 1));// 气压
			DecimalFormat df = new DecimalFormat("0");
			s_co = df.format(co);
			s_gas = df.format(gas);
			s_hum = df.format(hum);
			s_ill = df.format(ill);
			s_pm = df.format(pm);
			s_press = df.format(press);
			s_smo = df.format(smo);
			s_temp = df.format(temp);
			if (Float.valueOf(random.nextInt(10) % (10 - 1 + 1)) > 5) {
				s_per = "有人";
				per = 1;
			} else {
				s_per = "无人";
				per = 0;
			}
			db.execSQL(
					"insert into basedata(temp,smo,hum,press,ill,gas,co,pm,per)values(?,?,?,?,?,?,?,?,?)",
					new String[] { s_temp, s_hum, s_ill, s_smo, s_gas, s_press,
							s_co, s_pm, s_per });
			sqliteupdata.postDelayed(timeRunnable, 5000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = sqliteupdata.obtainMessage();
			sqliteupdata.sendMessage(msg);
		}
	};
}