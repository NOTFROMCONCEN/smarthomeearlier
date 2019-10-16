package com.example.guosaicdemo923;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @�ļ�����UnLockActivity.java
 * @��������������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-23
 */
public class UnLockActivity extends Activity {
	private SeekBar sk_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unlock);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {// ���û����������¼�

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// ֹͣ����
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					Intent intent = new Intent(getApplicationContext(),
							BarActivity.class);
					startActivity(intent);
					finish();
				} else {
					DiyToast.showToast(getApplicationContext(), "�뻬�������֤");
					sk_1.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// ��ʼ����
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// �������ı�
				// TODO Auto-generated method stub

			}
		});
	}
}