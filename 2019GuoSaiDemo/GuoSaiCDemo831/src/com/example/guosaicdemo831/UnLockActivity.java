package com.example.guosaicdemo831;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @文件名：UnLockActivity.java
 * @描述：对用户完成滑动解锁功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-31
 */
public class UnLockActivity extends Activity {
	private SeekBar sk_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unlock);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sk_1.setProgress(0);
		sk_1.setMax(100);
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// 停止滑动
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					DiyToast.showTaost(UnLockActivity.this, "当前IP为："
							+ LoginActivity.ip_number);
					Intent intent = new Intent(UnLockActivity.this,
							BarActivity.class);
					startActivity(intent);
					finish();
				} else {
					DiyToast.showTaost(UnLockActivity.this, "请完成滑动验证");
					sk_1.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// 开始滑动
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// 进度条改变
				// TODO Auto-generated method stub

			}
		});
	}
}
