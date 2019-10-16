package com.example.drawdemo53;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BarActivity extends FragmentActivity {
	// 当前是Bar滑动框架，大部分代码为自动填充；使用/***标注出的部分是要手动录入
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
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar);
		/** 绑定页面代码 **/
		faFragments.add(new BaseActivity());
		faFragments.add(new LinkActivity());
		faFragments.add(new ModeActivity());
		faFragments.add(new DrawActivity());
		/** 绑定页面代码 **/

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		/** 内部的faFragments是要录入完成最低端的左右代码以及定义List（Fragment）faFrament后才可使用自动修复 **/
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(), faFragments);
		/** 内部的faFragments是要录入完成最低端的左右代码以及定义List（Fragment）faFrament后才可使用自动修复 **/

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

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
		/** 新建Fragment **/
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
		public CharSequence getPageTitle(int position) {
			switch (position) {
			/** 设置界面顶端提示符，使用return可以在后面直接加“XXX”，也可以在values文件夹内部的string文件进行修改 **/
			case 0:
				return getString(R.string.title_section1).toUpperCase();
			case 1:
				return getString(R.string.title_section2).toUpperCase();
			case 2:
				return getString(R.string.title_section3).toUpperCase();
			case 3:
				return getString(R.string.title_section4).toUpperCase();
			}
			/** 设置界面顶端提示符，使用return可以在后面直接加“XXX”，也可以在values文件夹内部的string文件进行修改 **/
			return null;
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return fragments.get(arg0);
		}
	}
}
