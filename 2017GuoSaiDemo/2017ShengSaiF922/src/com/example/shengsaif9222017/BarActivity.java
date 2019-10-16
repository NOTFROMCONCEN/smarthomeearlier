package com.example.shengsaif9222017;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

public class BarActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	private RadioButton ra_base;// 环境监测
	private RadioButton ra_control;// 家电控制
	private RadioButton ra_link;// 情景模式
	private RadioGroup rg_bar_check;// 单选组
	private RadioButton[] rdo = new RadioButton[3];
	private boolean web_state = true;// 网络连接

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	private List<Fragment> faFragments = new ArrayList<Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		faFragments.add(new BaseActivity());
		faFragments.add(new ControlActivity());
		faFragments.add(new ModeActivity());
		ra_base = (RadioButton) findViewById(R.id.ra_base);
		ra_control = (RadioButton) findViewById(R.id.ra_control);
		ra_link = (RadioButton) findViewById(R.id.ra_link);
		rg_bar_check = (RadioGroup) findViewById(R.id.ra_bar_check);
		rdo[0] = ra_base;
		rdo[2] = ra_link;
		rdo[1] = ra_control;
		rg_bar_check.setBackgroundColor(Color.GRAY);
		ra_base.setTextColor(Color.WHITE);
		rg_bar_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (ra_base.getId() == checkedId) {
					ra_base.setTextColor(Color.WHITE);
					ra_control.setTextColor(Color.LTGRAY);
					ra_link.setTextColor(Color.LTGRAY);
					mViewPager.setCurrentItem(0);
				}
				if (ra_control.getId() == checkedId) {
					ra_base.setTextColor(Color.LTGRAY);
					ra_control.setTextColor(Color.WHITE);
					ra_link.setTextColor(Color.LTGRAY);
					mViewPager.setCurrentItem(1);
				}
				if (ra_link.getId() == checkedId) {
					ra_base.setTextColor(Color.LTGRAY);
					ra_control.setTextColor(Color.LTGRAY);
					ra_link.setTextColor(Color.WHITE);
					mViewPager.setCurrentItem(2);
				}
			}
		});
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(), faFragments);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						rdo[position].setChecked(true);
					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bar, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		private List<Fragment> fragments;

		public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return fragments.size();
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragments.get(arg0);
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (web_state) {
				ControlUtils.setUser("bizideal", "123456",
						MainActivity.IP_NUMBER);
				SocketClient.getInstance().creatConnect();
				SocketClient.getInstance().login(new LoginCallback() {

					@Override
					public void onEvent(final String web_state) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (web_state.equals("Success")) {
									// DiyToast.showToast(BarActivity.this,
									// "网络连接成功");
									BarActivity.this.web_state = false;
								} else {
									// DiyToast.showToast(BarActivity.this,
									// "网络连接失败");
									BarActivity.this.web_state = true;
								}
							}
						});
					}
				});
			}
			handler.postDelayed(timeRunnable, 1000);
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
}
