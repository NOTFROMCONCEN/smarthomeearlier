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
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// ǿ������
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
		txvName.setText("����̫��.mp3");
		AnimationSet animationSet = new AnimationSet(true);
		ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.5f, 0.5f,
				1.5f, Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 1.0f);
		scaleAnimation.setDuration(10000);// ��������ʱ��
		animationSet.addAnimation(scaleAnimation);// ���涯��Ч��������
		animationSet.setFillAfter(true);// �����󱣴�״̬
		qianyanzhanghan.startAnimation(animationSet);// ���ø��ؼ�
		animationSet.setFillAfter(false);// �����󲻱���״̬
		animationSet.start();
		playFromRaw();

		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number++;
				playFromRaw();
				if (mper.isPlaying()) {
					Toast.makeText(CXKnmslActivity.this, "���Ѿ���̫����",
							Toast.LENGTH_SHORT).show();
				}
				if (sp_1.getSelectedItem().toString().equals("ˢ��˿")) {
					txvName.setText("����̫��" + "x" + number);
					tv_geci.setVisibility(View.VISIBLE);
					tv_geci.setText("ֻ����̫�� baby ֻ����̫�� baby" + "\n"
							+ "ֻ����ʵ����̫�� baby ֻ����̫�� baby" + "\n"
							+ "ӭ����������������˴�������" + "\n" + "���ָо��Ҵ�δ��" + "\n"
							+ "Cause I got a crush on you who you" + "\n"
							+ "�����ҵ��������˭" + "\n" + "�ٶ�һ�ۿ�һ�۾ͻᱬը" + "\n"
							+ "�ٽ�һ�㿿����챻�ڻ�" + "\n" + "��Ҫ����ռΪ����baby bae" + "\n"
							+ "�����ߵ����ﶼ������������� you you" + "\n" + "��Ӧ����������"
							+ "\n" + "uh �����˶��ڿ�����" + "\n" + "�ҵ������ǲ���" + "\n"
							+ "oh �������Ѳ������" + "\n" + "eh eh �ѵ������Ϊ��������" + "\n"
							+ "�ұ�������������" + "\n" + "��������ֵ���" + "\n"
							+ "��һ��ѽ�����������" + "\n" + "��������ôȥ����" + "\n"
							+ "ֻ����̫�� baby ֻ����̫�� baby" + "\n"
							+ "ֻ����ʵ����̫�� baby ֻ����̫�� baby" + "\n"
							+ "oh eh oh ����ȷ�ϵظ�����" + "\n" + "oh eh oh �㵽������˭"
							+ "\n" + "oh eh oh ����ȷ�ϵظ�����" + "\n"
							+ "oh eh oh �㵽������˭ �������ڸ�����" + "\n"
							+ "��������� ���� make wave" + "\n"
							+ "���۵����� it&apos;s your birthday cake" + "\n"
							+ "�����ǵ� game call me ������" + "\n"
							+ "����ƭ���� I wanna play" + "\n" + "�ҵ��Ժ�ÿ��ÿ��ֻΪ��һ�˳���"
							+ "\n" + "�������������ߵ�����������ˮ" + "\n"
							+ "oh right baby I&apos;m fall in love with you"
							+ "\n" + "�ҵ�һ���㶼����ֻҪ��������㹻" + "\n" + "�ҵ���Ӧ������"
							+ "\n" + "uh ������һֱ�ܲ���" + "\n" + "���������ǵ�����" + "\n"
							+ "Oh ȫ��ֻ���������" + "\n" + "Eh eh �ѵ������Ϊ��������" + "\n"
							+ "�ұ�������������" + "\n" + "��������ֵ���" + "\n"
							+ "��һ��ѽ�����������" + "\n" + "��������ôȥ����" + "\n"
							+ "ֻ����̫�� baby ֻ����̫�� baby" + "\n"
							+ "ֻ����ʵ����̫�� baby ֻ����̫�� baby" + "\n" + "��Ը����ҵ�ȫ��������"
							+ "\n" + "��ÿ�������ﶼ�μ��㻹���ұ����۾�Ҳ�ܿ�����" + "\n"
							+ "���ڿ�ʼ��ֻ׼�㿴��" + "\n"
							+ "I don&apos;t wanna wake up in dream ��ֻ�뿴���������Ļ�"
							+ "\n" + "ֻ����̫�� baby ֻ����̫�� baby" + "\n"
							+ "ֻ����ʵ����̫�� baby ֻ����̫�� baby" + "\n"
							+ "oh eh oh ����ȷ�ϵĸ�����" + "\n" + "oh eh oh �㵽������˭"
							+ "\n" + "oh eh oh ����ȷ�ϵĸ�����" + "\n"
							+ "oh eh oh �㵽������˭�������ڸ�����");

				} else if (sp_1.getSelectedItem().toString().equals("ˢ��")) {
					txvName.setText("���۲�����" + "x" + number);
					tv_geci.setVisibility(View.VISIBLE);
					tv_geci.setText("AYO everybody ����ͷ�ϱ���"
							+ "\n"
							+ "no fly �Ҹ�������idol"
							+ "\n"
							+ "I��m from china��Ҳ��������Tokyo"
							+ "\n"
							+ "�ҵ�rap�������Ƕ�����LOW"
							+ "\n"
							+ "AYO everybody ����ͷ�ϱ���"
							+ "\n"
							+ "no fly �Ҹ�������idol"
							+ "\n"
							+ "I��m from china��Ҳ��������Tokyo"
							+ "\n"
							+ "�ҵ�rap�������Ƕ�����LOW"
							+ "\n"
							+ "��������"
							+ "\n"
							+ "uh ���ϵ���TOP"
							+ "\n"
							+ "hold on hold on��Ҳ������OG"
							+ "\n"
							+ "�Ը�������ʵ���������ҷ���"
							+ "\n"
							+ "���� ��WOW meifabi"
							+ "\n"
							+ "���� ��WOW meifabi"
							+ "\n"
							+ "������̫��ѹ��"
							+ "\n"
							+ "���������ѵڶ�"
							+ "\n"
							+ "Ȼ��ͱ�ø�funny funny"
							+ "\n"
							+ "��������ʶ�����ҵ�����"
							+ "\n"
							+ "ϣ���������������"
							+ "\n"
							+ "����Ŭ������ô����������������֪���ҵĶ���"
							+ "\n"
							+ "�����ø�����"
							+ "\n"
							+ "׷��·���Ҳ����˷�"
							+ "\n"
							+ "Ŭ��ƴ������Ϊ"
							+ "\n"
							+ "���ұ�����ø�����"
							+ "\n"
							+ "Ϊ�������Ŭ��"
							+ "\n"
							+ "��ô����Ҳ���ܷ���"
							+ "\n"
							+ "���ж�ڵ�ʵ��"
							+ "\n"
							+ "�ðɼ�ֲ������Һ�������"
							+ "\n"
							+ "wow 808080 qiu"
							+ "\n"
							+ "AYO everybody ����ͷ�ϱ���"
							+ "\n"
							+ "no fly �Ҹ�������idol"
							+ "\n"
							+ "I��m from china��Ҳ��������Tokyo"
							+ "\n"
							+ "�ҵ�rap�������Ƕ�����LOW"
							+ "\n"
							+ "AYO everybody ����ͷ�ϱ���"
							+ "\n"
							+ "no fly �Ҹ�������idol"
							+ "\n"
							+ "I��m from china��Ҳ��������Tokyo"
							+ "\n"
							+ "�ҵ�rap�������Ƕ�����LOW"
							+ "\n"
							+ "�����һ��������"
							+ "\n"
							+ "û��ʲôƢ��"
							+ "\n"
							+ "������ô����"
							+ "\n"
							+ "uh �����ҵ�ʱ�����ϸ�����"
							+ "\n"
							+ "������ ������ ��˵rap��˵no"
							+ "\n"
							+ "DH���ԣ�"
							+ "\n"
							+ "�������Ͽ����ҵ�����"
							+ "\n"
							+ "�����ϰ���ʽȫ��say no"
							+ "\n"
							+ "���������еĹ�ȥ�����鼯������ͨͨ�������"
							+ "\n"
							+ "�������������Щƣ����"
							+ "\n"
							+ "��ŵ˺����"
							+ "\n"
							+ "��û������Ҫ�ӱ� ֻ����ʵ��̫������"
							+ "\n"
							+ "�����ҷ����Ĵ����������ʹ���ֻ�ǲ����ٱ�����"
							+ "\n"
							+ "����˵��л������ ����������������"
							+ "\n"
							+ "����������ʱ������Ҳ�����ս�ȹ���Щ�ѹ�"
							+ "\n"
							+ "Ҳ�����һ���ʧ�� �����Լ��˷���������"
							+ "\n"
							+ "����Լ�����һ�п������ջ���ǰ;һƬ����woo"
							+ "\n"
							+ "I��m working now working woring working working now"
							+ "\n" + "�����ͷ ������������" + "\n" + "���������ѿ�͸" + "\n"
							+ "һ�оͽ���ս��" + "\n" + "��������ΰ���" + "\n" + "�ض��������͸"
							+ "\n" + "���Ͼͳ�������ײ�����Ȧ" + "\n" + "��Щ�����ߵ����Ǳ��������"
							+ "\n" + "���˱����˵�hate kid" + "\n"
							+ "����Ŭ��ȴʧ�ܵ�fake hate" + "\n" + "����ȫ��ƴ���������" + "\n"
							+ "�����÷���ȫ����" + "\n" + "you need ����������" + "\n"
							+ "��Ϊ��׬��ô�����" + "\n" + "�������ren ge����ʧ �ҽ�����ؼ�ȥ����"
							+ "\n" + "uh 808080 qiu" + "\n"
							+ "AYO everybody ����ͷ�ϱ���" + "\n"
							+ "no fly �Ҹ�������idol" + "\n"
							+ "I��m from china��Ҳ��������Tokyo" + "\n"
							+ "�ҵ�rap�������Ƕ�����LOW" + "\n" + "AYO everybody ����ͷ�ϱ���"
							+ "\n" + "no fly �Ҹ�������idol" + "\n"
							+ "m from china��Ҳ��������Tokyo" + "\n"
							+ "�ҵ�rap�������Ƕ�����LOW");
				} else {
					Toast.makeText(CXKnmslActivity.this,
							"������̫���ˣ���Ϣһ�£�ѡ���Spinner��Ч��", Toast.LENGTH_SHORT)
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
							"�����㿪ʼ����ˣ���ͣ��������.....����Ҳ��֪��Ϊʲô���У�",
							Toast.LENGTH_SHORT).show();

				} else {
					Toast.makeText(CXKnmslActivity.this, "�ȿ�ʼ�����£�",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private void playFromRaw() {
		if (sp_1.getSelectedItem().toString().equals("����̫��")) {
			mper = MediaPlayer.create(this, R.raw.swin);
		} else if (sp_1.getSelectedItem().toString().equals("�ڲ�����ͷ�ϱ���")) {
			mper = MediaPlayer.create(this, R.raw.bigghost);
		} else {
			Toast.makeText(CXKnmslActivity.this, "������̫���ˣ���Ϣһ�£�ѡ���Spinner��Ч��",
					Toast.LENGTH_SHORT).show();
		}
		mper.start();
	}
}