package com.example.drawdemo1003.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import com.example.drawdemo1003.R;
import com.example.drawdemo1003.helpclass.DiyToast;
import com.example.drawdemo1003.myview.MyView;

/*
 * @�ļ�����DrawFragment.java
 * @����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-5
 */
public class DrawFragment extends Fragment {
	private RadioButton ra_temp, ra_smo, ra_ill;// �¶��������ѡ��
	private ListView lv_1;
	public static float getdata;
	public static String num;
	public static boolean draw_state = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		initView(view);// �󶨿ؼ�
		lv_1.setAdapter(null);
		// �������
		handler.post(timeRunnable);
		// ����ListView����¼�
		/**
		 * ���ã�����HashMap�޷����αꣿ lv_1.setOnItemClickListener(new
		 * OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> arg0, View arg1, int
		 *           arg2, long arg3) { // TODO Auto-generated method stub //
		 *           Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
		 *           DiyToast.showToast(getActivity(), "��ǰ��ֵ��" + cursor); } });
		 */
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ListView��������ֵ
	 * 
	 * @ʱ �䣺����3:23:13
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ��XX��ѡ�С��ʹ������ݲɼ��������ֵ
			if (ra_ill.isChecked()) {
				getdata = BaseFragment.ill;
				num = "����";
			}
			if (ra_smo.isChecked()) {
				getdata = BaseFragment.smo;
				num = "����";
			}
			if (ra_temp.isChecked()) {
				getdata = BaseFragment.temp;
				num = "�¶�";
			}
			System.out.println(getdata);
			/**
			 * ����ListView���ֿ���д�ˣ�дһ���Եý������ӣ�
			 */
			getdata();
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

	/*
	 * @��������getdata()
	 * 
	 * @�� �ܣ���ȡ�������õ�ListView��
	 * 
	 * @ʱ �䣺����3:21:34
	 */
	private void getdata() {
		/**
		 * XML�ļ��пɽ�ListView���õײ��۽�
		 */
		SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
				MyView.list, R.layout.activity_draw_text, new String[] {
						"number", "data", "time" }, new int[] { R.id.tv_num,
						R.id.tv_data, R.id.tv_time });
		lv_1.setAdapter(simpleAdapter);
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����3:19:31
	 */
	private void initView(View view) {
		// TODO Auto-generated method stub
		ra_ill = (RadioButton) view.findViewById(R.id.ra_ill);
		ra_smo = (RadioButton) view.findViewById(R.id.ra_smo);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
	}
}
