package com.example.guosaigdemo9262019;

import java.util.ArrayList;
import java.util.List;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

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
	private List<Fragment> faFragments = new ArrayList<Fragment>();
	private RadioButton ra_base, ra_link, ra_draw, ra_setting;
	private RadioGroup rg_bar_check;
	private RadioButton[] rdo = new RadioButton[4];
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	private boolean web_state = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		faFragments.add(new BaseActivity());
		faFragments.add(new LinkActivity());
		faFragments.add(new DrawActivity());
		faFragments.add(new SettingActivity());
		ra_base = (RadioButton) findViewById(R.id.ra_base);
		ra_draw = (RadioButton) findViewById(R.id.ra_draw);
		ra_link = (RadioButton) findViewById(R.id.ra_link);
		ra_setting = (RadioButton) findViewById(R.id.ra_setting);
		rg_bar_check = (RadioGroup) findViewById(R.id.rg_bar_check);
		System.out.println(LoginActivity.IP_NUMBER);
		// 激活进程
		handler.post(timeRunnable);
		rdo[0] = ra_base;
		rdo[1] = ra_link;
		rdo[2] = ra_draw;
		rdo[3] = ra_setting;
		rg_bar_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (ra_base.getId() == checkedId) {
					mViewPager.setCurrentItem(0);
				}
				if (ra_draw.getId() == checkedId) {
					mViewPager.setCurrentItem(2);
				}
				if (ra_link.getId() == checkedId) {
					mViewPager.setCurrentItem(1);
				}
				if (ra_setting.getId() == checkedId) {
					mViewPager.setCurrentItem(3);
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
						LoginActivity.IP_NUMBER);
				SocketClient.getInstance().creatConnect();
				SocketClient.getInstance().login(new LoginCallback() {

					@Override
					public void onEvent(final String arg0) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (arg0.equals("Success")) {
									DiyToast.showToast(getApplicationContext(),
											"网络连接成功");
									web_state = false;
								} else {

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
