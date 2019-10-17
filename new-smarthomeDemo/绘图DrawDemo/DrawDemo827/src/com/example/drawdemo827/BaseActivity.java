package com.example.drawdemo827;

import com.bizideal.smarthome.socket.SocketClient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @�ļ�����BaseActivity.java
 * @��������ɽ���Ļ��������ݲɼ����豸���ƹ���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-27
 */
public class BaseActivity extends Fragment {
	private Button btn_send;
	private EditText et_send;
	private ToggleButton tg_lamp;// ���
	private ToggleButton tg_door;// �Ž�
	private ToggleButton tg_fan;// ����
	private ToggleButton tg_tv;// ����
	private ToggleButton tg_kongtiao;// �յ�
	private ToggleButton tg_dvd;// DVD
	private ToggleButton tg_warm;// ������
	private TextView tv_temp;// �¶�
	private TextView tv_hum;// �¶�
	private TextView tv_gas;// ȼ��
	private TextView tv_press;// ��ѹ
	private TextView tv_smo;// ����
	private TextView tv_ill;// ����
	private TextView tv_co;// ������̼
	private TextView tv_pm;// PM2.5
	private TextView tv_per;// �������

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) view.findViewById(R.id.tg_dvd);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_kongtiao = (ToggleButton) view.findViewById(R.id.tg_kongtiao);
		tg_lamp = (ToggleButton) view.findViewById(R.id.tg_lamp);
		tg_tv = (ToggleButton) view.findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		return view;
	}
}