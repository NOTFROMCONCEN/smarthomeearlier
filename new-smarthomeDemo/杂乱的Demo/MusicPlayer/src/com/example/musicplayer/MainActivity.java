package com.example.musicplayer;

import java.io.FilenameFilter;
import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {
	private TextView txvName, txvUri, tv_geci;
	private Button btn_start, btn_stop;
	private MediaPlayer mper;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
		txvName = (TextView) findViewById(R.id.txvName);
		txvUri = (TextView) findViewById(R.id.txvUri);
		txvUri.setText("ID:" + String.valueOf(R.raw.swin));
		tv_geci = (TextView) findViewById(R.id.tv_geci);
		btn_stop = (Button) findViewById(R.id.btn_stop);
		btn_start = (Button) findViewById(R.id.btnPickAudio);
		txvName.setText("鸡你太美.mp3");
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number++;
				playFromRaw();
				if (mper.isPlaying()) {
					Toast.makeText(MainActivity.this, "鸡已经在太美了",
							Toast.LENGTH_SHORT).show();
				} else {
				}
				txvName.setText("鸡你太美" + "x" + number);
				tv_geci.setVisibility(View.VISIBLE);
				tv_geci.setText("只因你太美 baby 只因你太美 baby" + "\n"
						+ "只因你实在是太美 baby 只因你太美 baby" + "\n" + "迎面走来的你让我如此蠢蠢欲动"
						+ "\n" + "这种感觉我从未有" + "\n"
						+ "Cause I got a crush on you who you" + "\n"
						+ "你是我的我是你的谁" + "\n" + "再多一眼看一眼就会爆炸" + "\n"
						+ "再近一点靠近点快被融化" + "\n" + "想要把你占为己有baby bae" + "\n"
						+ "不管走到哪里都会想起的人是你 you you" + "\n" + "我应该拿你怎样" + "\n"
						+ "uh 所有人都在看着你" + "\n" + "我的心总是不安" + "\n"
						+ "oh 我现在已病入膏肓" + "\n" + "eh eh 难道真的因为你而疯狂吗" + "\n"
						+ "我本来不是这种人" + "\n" + "因你变成奇怪的人" + "\n" + "第一次呀变成这样的我"
						+ "\n" + "不管我怎么去否认" + "\n" + "只因你太美 baby 只因你太美 baby"
						+ "\n" + "只因你实在是太美 baby 只因你太美 baby" + "\n"
						+ "oh eh oh 现在确认地告诉我" + "\n" + "oh eh oh 你到底属于谁" + "\n"
						+ "oh eh oh 现在确认地告诉我" + "\n"
						+ "oh eh oh 你到底属于谁 就是现在告诉我" + "\n"
						+ "跟着这节奏 缓缓 make wave" + "\n"
						+ "甜蜜的奶油 it&apos;s your birthday cake" + "\n"
						+ "男人们的 game call me 你恋人" + "\n"
						+ "别被欺骗愉快的 I wanna play" + "\n" + "我的脑海每分每秒只为你一人沉醉"
						+ "\n" + "最迷人让我神魂颠倒是你身上香水" + "\n"
						+ "oh right baby I&apos;m fall in love with you" + "\n"
						+ "我的一切你都拿走只要有你就已足够" + "\n" + "我到底应该怎样" + "\n"
						+ "uh 我心里一直很不安" + "\n" + "其他男人们的视线" + "\n"
						+ "Oh 全都只看向你的脸" + "\n" + "Eh eh 难道真的因为你而疯狂吗" + "\n"
						+ "我本来不是这种人" + "\n" + "因你变成奇怪的人" + "\n" + "第一次呀变成这样的我"
						+ "\n" + "不管我怎么去否认" + "\n" + "只因你太美 baby 只因你太美 baby"
						+ "\n" + "只因你实在是太美 baby 只因你太美 baby" + "\n"
						+ "我愿意把我的全部都给你" + "\n" + "我每天在梦里都梦见你还有我闭着眼睛也能看到你"
						+ "\n" + "现在开始我只准你看我" + "\n"
						+ "I don&apos;t wanna wake up in dream 我只想看你这是真心话"
						+ "\n" + "只因你太美 baby 只因你太美 baby" + "\n"
						+ "只因你实在是太美 baby 只因你太美 baby" + "\n"
						+ "oh eh oh 现在确认的告诉我" + "\n" + "oh eh oh 你到底属于谁" + "\n"
						+ "oh eh oh 现在确认的告诉我" + "\n" + "oh eh oh 你到底属于谁就是现在告诉我");
			}
		});
		btn_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mper.isPlaying()) {
					mper.stop();
					Toast.makeText(MainActivity.this,
							"如果你点开始点多了，是停不下来的.....（我也不知道为什么不行）",
							Toast.LENGTH_SHORT).show();

				} else {
					Toast.makeText(MainActivity.this, "先开始播放呗？",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private void playFromRaw() {
		mper = MediaPlayer.create(this, R.raw.swin);
		mper.start();
	}
}