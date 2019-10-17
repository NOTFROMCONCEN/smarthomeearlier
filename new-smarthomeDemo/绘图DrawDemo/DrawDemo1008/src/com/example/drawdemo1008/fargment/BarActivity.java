package com.example.drawdemo1008.fargment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.drawdemo1008.LoginActivity;
import com.example.drawdemo1008.R;
import com.example.drawdemo1008.toast.DiyToast;

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
	private TextView tv_now_pager;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	private List<Fragment> faFragments = new ArrayList<Fragment>();
	private boolean web_state = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		handler.post(timeRunnable);
		faFragments.add(new BaseActivity());
		faFragments.add(new LinkActivity());
		faFragments.add(new ModeActivity());
		faFragments.add(new DrawActivity());
		faFragments.add(new UpdataPassActivity());
		faFragments.add(new SettingActivity());
		tv_now_pager = (TextView) findViewById(R.id.tv_now_pager);
		tv_now_pager.setText("BaseActivity——数据采集、设备控制");

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
						System.out.println(position);
						switch (position) {
						case 0:
							tv_now_pager.setText("BaseActivity——数据采集、设备控制");
							break;
						case 1:
							tv_now_pager.setText("LinkActivity——联动");
							break;

						case 2:
							tv_now_pager.setText("ModeActivity——模式");
							break;

						case 3:
							tv_now_pager.setText("DrawActivity——图形绘制");
							break;

						case 4:
							tv_now_pager.setText("UpdataPassActivity——修改密码界面");
							break;

						case 5:
							tv_now_pager.setText("SettingActivity——设置界面");
							break;

						default:
							break;
						}
					}
				});

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

	/*
	 * @方法名：handler
	 * 
	 * @功 能：网络连接
	 * 
	 * @时 间：下午8:07:16
	 */
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
								if (arg0.equals(ConstantUtil.FAILURE)) {
									DiyToast.showToast(getApplicationContext(),
											"连接失败");
								} else if (arg0.equals(ConstantUtil.SUCCESS)) {
									DiyToast.showToast(getApplicationContext(),
											"连接成功");
									web_state = false;
								} else {
									DiyToast.showToast(getApplicationContext(),
											"未知网络");
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
