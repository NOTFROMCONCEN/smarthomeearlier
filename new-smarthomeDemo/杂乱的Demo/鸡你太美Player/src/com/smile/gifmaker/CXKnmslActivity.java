package com.smile.gifmaker;

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
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class CXKnmslActivity extends Activity {
	private TextView txvName, txvUri, tv_geci;
	private Button btn_start, btn_stop;
	private MediaPlayer mper;
	private int number = 0;
	private Spinner sp_1;
	private ImageView qianyanzhanghan;
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 强制竖屏
		setContentView(R.layout.activity_cxknmsl);
		txvName = (TextView) findViewById(R.id.txvName);
		txvUri = (TextView) findViewById(R.id.txvUri);
		tv_geci = (TextView) findViewById(R.id.tv_geci);
		btn_stop = (Button) findViewById(R.id.btn_stop);
		qianyanzhanghan = (ImageView) findViewById(R.id.qianyanzhanghan);
		btn_start = (Button) findViewById(R.id.btnPickAudio);
		sp_1 = (Spinner) findViewById(R.id.sp_check);
		webView = (WebView) findViewById(R.id.local_webview);
		webView.loadUrl("http://player.youku.com/embed/XNDA5MzQyNDY4NA==?client_id=d68f8152aef3be26&password=&autoplay=false#v.ali213.net");
		txvName.setText("鸡你太美.mp3");
		AnimationSet animationSet = new AnimationSet(true);
		ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.5f, 0.5f,
				1.5f, Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 1.0f);
		scaleAnimation.setDuration(10000);// 动画持续时间
		animationSet.addAnimation(scaleAnimation);// 保存动画效果到。。
		animationSet.setFillAfter(true);// 结束后保存状态
		qianyanzhanghan.startAnimation(animationSet);// 设置给控件
		animationSet.setFillAfter(false);// 结束后不保存状态
		animationSet.start();
		playFromRaw();

		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number++;
				playFromRaw();
				if (mper.isPlaying()) {
					Toast.makeText(CXKnmslActivity.this, "鸡已经在太美了",
							Toast.LENGTH_SHORT).show();
				}
				if (sp_1.getSelectedItem().toString().equals("刷粉丝")) {
					txvName.setText("鸡你太美" + "x" + number);
					tv_geci.setVisibility(View.VISIBLE);
					tv_geci.setText("只因你太美 baby 只因你太美 baby" + "\n"
							+ "只因你实在是太美 baby 只因你太美 baby" + "\n"
							+ "迎面走来的你让我如此蠢蠢欲动" + "\n" + "这种感觉我从未有" + "\n"
							+ "Cause I got a crush on you who you" + "\n"
							+ "你是我的我是你的谁" + "\n" + "再多一眼看一眼就会爆炸" + "\n"
							+ "再近一点靠近点快被融化" + "\n" + "想要把你占为己有baby bae" + "\n"
							+ "不管走到哪里都会想起的人是你 you you" + "\n" + "我应该拿你怎样"
							+ "\n" + "uh 所有人都在看着你" + "\n" + "我的心总是不安" + "\n"
							+ "oh 我现在已病入膏肓" + "\n" + "eh eh 难道真的因为你而疯狂吗" + "\n"
							+ "我本来不是这种人" + "\n" + "因你变成奇怪的人" + "\n"
							+ "第一次呀变成这样的我" + "\n" + "不管我怎么去否认" + "\n"
							+ "只因你太美 baby 只因你太美 baby" + "\n"
							+ "只因你实在是太美 baby 只因你太美 baby" + "\n"
							+ "oh eh oh 现在确认地告诉我" + "\n" + "oh eh oh 你到底属于谁"
							+ "\n" + "oh eh oh 现在确认地告诉我" + "\n"
							+ "oh eh oh 你到底属于谁 就是现在告诉我" + "\n"
							+ "跟着这节奏 缓缓 make wave" + "\n"
							+ "甜蜜的奶油 it&apos;s your birthday cake" + "\n"
							+ "男人们的 game call me 你恋人" + "\n"
							+ "别被欺骗愉快的 I wanna play" + "\n" + "我的脑海每分每秒只为你一人沉醉"
							+ "\n" + "最迷人让我神魂颠倒是你身上香水" + "\n"
							+ "oh right baby I&apos;m fall in love with you"
							+ "\n" + "我的一切你都拿走只要有你就已足够" + "\n" + "我到底应该怎样"
							+ "\n" + "uh 我心里一直很不安" + "\n" + "其他男人们的视线" + "\n"
							+ "Oh 全都只看向你的脸" + "\n" + "Eh eh 难道真的因为你而疯狂吗" + "\n"
							+ "我本来不是这种人" + "\n" + "因你变成奇怪的人" + "\n"
							+ "第一次呀变成这样的我" + "\n" + "不管我怎么去否认" + "\n"
							+ "只因你太美 baby 只因你太美 baby" + "\n"
							+ "只因你实在是太美 baby 只因你太美 baby" + "\n" + "我愿意把我的全部都给你"
							+ "\n" + "我每天在梦里都梦见你还有我闭着眼睛也能看到你" + "\n"
							+ "现在开始我只准你看我" + "\n"
							+ "I don&apos;t wanna wake up in dream 我只想看你这是真心话"
							+ "\n" + "只因你太美 baby 只因你太美 baby" + "\n"
							+ "只因你实在是太美 baby 只因你太美 baby" + "\n"
							+ "oh eh oh 现在确认的告诉我" + "\n" + "oh eh oh 你到底属于谁"
							+ "\n" + "oh eh oh 现在确认的告诉我" + "\n"
							+ "oh eh oh 你到底属于谁就是现在告诉我");

				} else if (sp_1.getSelectedItem().toString().equals("刷赞")) {
					txvName.setText("暴扣蔡徐坤" + "x" + number);
					tv_geci.setVisibility(View.VISIBLE);
					tv_geci.setText("AYO everybody 在你头上暴扣"
							+ "\n"
							+ "no fly 我根本不是idol"
							+ "\n"
							+ "I’m from china我也不是来自Tokyo"
							+ "\n"
							+ "我的rap听完你们都别怕LOW"
							+ "\n"
							+ "AYO everybody 在你头上暴扣"
							+ "\n"
							+ "no fly 我根本不是idol"
							+ "\n"
							+ "I’m from china我也不是来自Tokyo"
							+ "\n"
							+ "我的rap听完你们都别怕LOW"
							+ "\n"
							+ "王浩轩："
							+ "\n"
							+ "uh 马上登上TOP"
							+ "\n"
							+ "hold on hold on我也不是怕OG"
							+ "\n"
							+ "对付你们其实根本不用我发力"
							+ "\n"
							+ "你们 更WOW meifabi"
							+ "\n"
							+ "你们 更WOW meifabi"
							+ "\n"
							+ "承受了太多压力"
							+ "\n"
							+ "登上了热搜第二"
							+ "\n"
							+ "然后就变得更funny funny"
							+ "\n"
							+ "让你们认识我是我的荣幸"
							+ "\n"
							+ "希望暴扣能让你高兴"
							+ "\n"
							+ "看我努力了这么多年终于能让你们知道我的动静"
							+ "\n"
							+ "梦想变得更宝贵"
							+ "\n"
							+ "追梦路上我不会浪费"
							+ "\n"
							+ "努力拼搏的行为"
							+ "\n"
							+ "让我比赛变得更纯粹"
							+ "\n"
							+ "为了梦想而努力"
							+ "\n"
							+ "多么困难也不能放弃"
							+ "\n"
							+ "具有夺冠的实力"
							+ "\n"
							+ "好吧坚持才能让我毫不费力"
							+ "\n"
							+ "wow 808080 qiu"
							+ "\n"
							+ "AYO everybody 在你头上暴扣"
							+ "\n"
							+ "no fly 我根本不是idol"
							+ "\n"
							+ "I’m from china我也不是来自Tokyo"
							+ "\n"
							+ "我的rap听完你们都别怕LOW"
							+ "\n"
							+ "AYO everybody 在你头上暴扣"
							+ "\n"
							+ "no fly 我根本不是idol"
							+ "\n"
							+ "I’m from china我也不是来自Tokyo"
							+ "\n"
							+ "我的rap听完你们都别怕LOW"
							+ "\n"
							+ "现在我会充满勇气"
							+ "\n"
							+ "没有什么脾气"
							+ "\n"
							+ "不用那么费力"
							+ "\n"
							+ "uh 这是我的时代马上跟我走"
							+ "\n"
							+ "举起手 看我秀 你说rap我说no"
							+ "\n"
							+ "DH董辉："
							+ "\n"
							+ "现在马上看好我的作秀"
							+ "\n"
							+ "击垮障碍方式全部say no"
							+ "\n"
							+ "把以往所有的过去的事情集合起来通通打包带走"
							+ "\n"
							+ "可能是最近我有些疲惫了"
							+ "\n"
							+ "承诺撕碎了"
							+ "\n"
							+ "并没有想着要逃避 只是现实他太过锋利"
							+ "\n"
							+ "曾经我放弃的错过的让我受痛苦的只是不想再被定义"
							+ "\n"
							+ "我想说感谢你的陪伴 这半年有你和我作伴"
							+ "\n"
							+ "曾经最低落的时候你和我并肩作战度过这些难关"
							+ "\n"
							+ "也想过有一天会失败 逼迫自己克服所有依赖"
							+ "\n"
							+ "告诫自己承受一切苦难最终换来前途一片灿烂woo"
							+ "\n"
							+ "I’m working now working woring working working now"
							+ "\n" + "你低着头 键盘在你手中" + "\n" + "谎言我早已看透" + "\n"
							+ "一招就结束战斗" + "\n" + "跟着我这段伴奏" + "\n" + "必定会把你悟透"
							+ "\n" + "马上就冲破羁绊飞离底层自娱圈" + "\n" + "那些断了线的人们别来扮鬼脸"
							+ "\n" + "怕了被骂了的hate kid" + "\n"
							+ "还有努力却失败的fake hate" + "\n" + "他们全部拼命都在算计" + "\n"
							+ "不懂得方法全废弃" + "\n" + "you need 和他们争吵" + "\n"
							+ "就为了赚那么点酬劳" + "\n" + "到最后连ren ge都丢失 我建议你回家去查找"
							+ "\n" + "uh 808080 qiu" + "\n"
							+ "AYO everybody 在你头上暴扣" + "\n"
							+ "no fly 我根本不是idol" + "\n"
							+ "I’m from china我也不是来自Tokyo" + "\n"
							+ "我的rap听完你们都别怕LOW" + "\n" + "AYO everybody 在你头上暴扣"
							+ "\n" + "no fly 我根本不是idol" + "\n"
							+ "m from china我也不是来自Tokyo" + "\n"
							+ "我的rap听完你们都别怕LOW");
				} else {
					Toast.makeText(CXKnmslActivity.this,
							"篮球打的太多了，休息一下（选择的Spinner无效）", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
		btn_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mper.isPlaying()) {
					mper.stop();
					Toast.makeText(CXKnmslActivity.this,
							"如果你点开始点多了，是停不下来的.....（我也不知道为什么不行）",
							Toast.LENGTH_SHORT).show();

				} else {
					Toast.makeText(CXKnmslActivity.this, "先开始播放呗？",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private void playFromRaw() {
		if (sp_1.getSelectedItem().toString().equals("鸡你太美")) {
			mper = MediaPlayer.create(this, R.raw.swin);
		} else if (sp_1.getSelectedItem().toString().equals("在蔡徐坤头上暴扣")) {
			mper = MediaPlayer.create(this, R.raw.bigghost);
		} else {
			Toast.makeText(CXKnmslActivity.this, "篮球打的太多了，休息一下（选择的Spinner无效）",
					Toast.LENGTH_SHORT).show();
		}
		mper.start();
	}
}