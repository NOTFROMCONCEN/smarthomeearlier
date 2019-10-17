package com.yarin.android.Examples_07_04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Activity01 extends Activity {
	private MediaPlayer mMediaPlayer;
	private VideoView videoView;
	private MediaController mediaController;
	static String uri;
	static String uri2;
	private EditText editText1;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 强制竖屏
		setContentView(R.layout.main);
		editText1 = (EditText) findViewById(R.id.editText1);
		videoView = (VideoView) findViewById(R.id.video);
		mediaController = new MediaController(this);
		// 获取raw.mp4的uri地址
		uri = "android.resource://" + getPackageName() + "/" + R.raw.play;
		uri2 = "https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/1165180_e9205e1b6684eeb6ac314682e5dc8133_3.mp4";
		// 让video和mediaController建立关联
		videoView.setMediaController(mediaController);
		mediaController.setMediaPlayer(videoView);
		// 让video获取焦点
		videoView.requestFocus();
		// 监听播放完成，
		videoView
				.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						// 重新开始播放
						videoView.start();
					}
				});
		new AlertDialog.Builder(this)
				.setTitle("")
				.setMessage("我有一些话憋了很久了，像今天对你说出来......")
				.setPositiveButton("听一下",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								handler.post(timeRunnable);
								videoView.setVideoURI(Uri.parse(uri));
								videoView.start();
								Toast.makeText(Activity01.this, "谢谢",
										Toast.LENGTH_SHORT).show();
							}
						})
				.setNegativeButton("去你TM的不听",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								videoView.setVideoURI(Uri.parse(uri2));
								handler2.post(timeRunnable2);
								Toast.makeText(Activity01.this, "CNM不听也得听",
										Toast.LENGTH_SHORT).show();
								videoView.start();
							}
						}).show();
	}

	Handler handler2 = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 5:
				editText1.setText("操你妈，你妈了个臭逼");
				break;

			default:
				break;
			}
			handler.postDelayed(timeRunnable2, 1000);
		}
	};
	Runnable timeRunnable2 = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int num = 0;
			num++;
			Message msg = handler.obtainMessage();
			msg.what = num;
			handler.sendMessage(msg);
		}
	};

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

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
