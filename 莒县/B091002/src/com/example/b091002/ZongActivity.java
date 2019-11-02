package com.example.b091002;

import java.util.ArrayList;
import java.util.List;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ZongActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	public static ViewPager mViewPager;
	List<Fragment> fragments=new ArrayList<Fragment>();
	Button btngl,btnkz,btnlian,btnwd;
	TextView tvgl,tvkz,tvlian,tvwd;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa);
		fragments.add(new FangjianActivity());
		fragments.add(new KongzhiActivity());
		fragments.add(new ZhenlianActivity());
		fragments.add(new WenduActivity());
		fragments.add(new DianActivity());
		fragments.add(new Lian1Activity());
		fragments.add(new WqActivity());
		
		
		btngl=(Button)findViewById(R.id.butgl);
		btnkz=(Button)findViewById(R.id.butkz);
		btnlian=(Button)findViewById(R.id.butlian);
		btnwd=(Button)findViewById(R.id.butwd);
		tvgl=(TextView)findViewById(R.id.tegl);
		tvkz=(TextView)findViewById(R.id.tekz);
		tvlian=(TextView)findViewById(R.id.telian);
		tvwd=(TextView)findViewById(R.id.tewd);
		
		tvgl.setVisibility(View.VISIBLE);
		tvkz.setVisibility(View.INVISIBLE);
		tvlian.setVisibility(View.INVISIBLE);
		tvwd.setVisibility(View.INVISIBLE);
		
		btngl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			mViewPager.setCurrentItem(0);
			tvgl.setVisibility(View.VISIBLE);
			tvkz.setVisibility(View.INVISIBLE);
			tvlian.setVisibility(View.INVISIBLE);
			tvwd.setVisibility(View.INVISIBLE);
			}
		});
		btnkz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(1);
				tvkz.setVisibility(View.VISIBLE);
				tvgl.setVisibility(View.INVISIBLE);
				tvlian.setVisibility(View.INVISIBLE);
				tvwd.setVisibility(View.INVISIBLE);
			}
		});
		btnlian.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(2);
				tvlian.setVisibility(View.VISIBLE);
				tvkz.setVisibility(View.INVISIBLE);
				tvgl.setVisibility(View.INVISIBLE);
				tvwd.setVisibility(View.INVISIBLE);
			}
		});
		btnwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(3);
				tvwd.setVisibility(View.VISIBLE);
				tvkz.setVisibility(View.INVISIBLE);
				tvlian.setVisibility(View.INVISIBLE);
				tvgl.setVisibility(View.INVISIBLE);
			}
		});
		ControlUtils.setUser("bizideal", "123456", "15.1.10.26");
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {
			
			@Override
			public void onEvent(final String lj) {
				// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (lj.equals(ConstantUtil.SUCCESS)) {
						Toast.makeText(ZongActivity.this, "组网成功 ", Toast.LENGTH_LONG).show();
					}else if (lj.equals(ConstantUtil.FAILURE)) {
						Toast.makeText(ZongActivity.this, "组网失败", Toast.LENGTH_LONG).show();
					}else {
						Toast.makeText(ZongActivity.this, "组网中 ", Toast.LENGTH_LONG).show();
					}
				}
			});
			}
		});

//		// Set up the action bar.
//		final ActionBar actionBar = getActionBar();
//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//
//		// Create the adapter that will return a fragment for each of the three
//		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

//		// When swiping between different sections, select the corresponding
//		// tab. We can also use ActionBar.Tab#select() to do this if we have
//		// a reference to the Tab.
//		mViewPager
//				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//					@Override
//					public void onPageSelected(int position) {
//						actionBar.setSelectedNavigationItem(position);
//					}
//				});
//
//		// For each of the sections in the app, add a tab to the action bar.
//		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
//			// Create a tab with text corresponding to the page title defined by
//			// the adapter. Also specify this Activity object, which implements
//			// the TabListener interface, as the callback (listener) for when
//			// this tab is selected.
//			actionBar.addTab(actionBar.newTab()
//					.setText(mSectionsPagerAdapter.getPageTitle(i))
//					.setTabListener(this));
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_zong, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return fragments.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase();
			case 1:
				return getString(R.string.title_section2).toUpperCase();
			case 2:
				return getString(R.string.title_section3).toUpperCase();
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return textView;
		}
	}

}
