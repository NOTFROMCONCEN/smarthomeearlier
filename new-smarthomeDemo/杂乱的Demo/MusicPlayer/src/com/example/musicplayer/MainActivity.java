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
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//ǿ������
		txvName = (TextView) findViewById(R.id.txvName);
		txvUri = (TextView) findViewById(R.id.txvUri);
		txvUri.setText("ID:" + String.valueOf(R.raw.swin));
		tv_geci = (TextView) findViewById(R.id.tv_geci);
		btn_stop = (Button) findViewById(R.id.btn_stop);
		btn_start = (Button) findViewById(R.id.btnPickAudio);
		txvName.setText("����̫��.mp3");
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number++;
				playFromRaw();
				if (mper.isPlaying()) {
					Toast.makeText(MainActivity.this, "���Ѿ���̫����",
							Toast.LENGTH_SHORT).show();
				} else {
				}
				txvName.setText("����̫��" + "x" + number);
				tv_geci.setVisibility(View.VISIBLE);
				tv_geci.setText("ֻ����̫�� baby ֻ����̫�� baby" + "\n"
						+ "ֻ����ʵ����̫�� baby ֻ����̫�� baby" + "\n" + "ӭ����������������˴�������"
						+ "\n" + "���ָо��Ҵ�δ��" + "\n"
						+ "Cause I got a crush on you who you" + "\n"
						+ "�����ҵ��������˭" + "\n" + "�ٶ�һ�ۿ�һ�۾ͻᱬը" + "\n"
						+ "�ٽ�һ�㿿����챻�ڻ�" + "\n" + "��Ҫ����ռΪ����baby bae" + "\n"
						+ "�����ߵ����ﶼ������������� you you" + "\n" + "��Ӧ����������" + "\n"
						+ "uh �����˶��ڿ�����" + "\n" + "�ҵ������ǲ���" + "\n"
						+ "oh �������Ѳ������" + "\n" + "eh eh �ѵ������Ϊ��������" + "\n"
						+ "�ұ�������������" + "\n" + "��������ֵ���" + "\n" + "��һ��ѽ�����������"
						+ "\n" + "��������ôȥ����" + "\n" + "ֻ����̫�� baby ֻ����̫�� baby"
						+ "\n" + "ֻ����ʵ����̫�� baby ֻ����̫�� baby" + "\n"
						+ "oh eh oh ����ȷ�ϵظ�����" + "\n" + "oh eh oh �㵽������˭" + "\n"
						+ "oh eh oh ����ȷ�ϵظ�����" + "\n"
						+ "oh eh oh �㵽������˭ �������ڸ�����" + "\n"
						+ "��������� ���� make wave" + "\n"
						+ "���۵����� it&apos;s your birthday cake" + "\n"
						+ "�����ǵ� game call me ������" + "\n"
						+ "����ƭ���� I wanna play" + "\n" + "�ҵ��Ժ�ÿ��ÿ��ֻΪ��һ�˳���"
						+ "\n" + "�������������ߵ�����������ˮ" + "\n"
						+ "oh right baby I&apos;m fall in love with you" + "\n"
						+ "�ҵ�һ���㶼����ֻҪ��������㹻" + "\n" + "�ҵ���Ӧ������" + "\n"
						+ "uh ������һֱ�ܲ���" + "\n" + "���������ǵ�����" + "\n"
						+ "Oh ȫ��ֻ���������" + "\n" + "Eh eh �ѵ������Ϊ��������" + "\n"
						+ "�ұ�������������" + "\n" + "��������ֵ���" + "\n" + "��һ��ѽ�����������"
						+ "\n" + "��������ôȥ����" + "\n" + "ֻ����̫�� baby ֻ����̫�� baby"
						+ "\n" + "ֻ����ʵ����̫�� baby ֻ����̫�� baby" + "\n"
						+ "��Ը����ҵ�ȫ��������" + "\n" + "��ÿ�������ﶼ�μ��㻹���ұ����۾�Ҳ�ܿ�����"
						+ "\n" + "���ڿ�ʼ��ֻ׼�㿴��" + "\n"
						+ "I don&apos;t wanna wake up in dream ��ֻ�뿴���������Ļ�"
						+ "\n" + "ֻ����̫�� baby ֻ����̫�� baby" + "\n"
						+ "ֻ����ʵ����̫�� baby ֻ����̫�� baby" + "\n"
						+ "oh eh oh ����ȷ�ϵĸ�����" + "\n" + "oh eh oh �㵽������˭" + "\n"
						+ "oh eh oh ����ȷ�ϵĸ�����" + "\n" + "oh eh oh �㵽������˭�������ڸ�����");
			}
		});
		btn_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mper.isPlaying()) {
					mper.stop();
					Toast.makeText(MainActivity.this,
							"�����㿪ʼ����ˣ���ͣ��������.....����Ҳ��֪��Ϊʲô���У�",
							Toast.LENGTH_SHORT).show();

				} else {
					Toast.makeText(MainActivity.this, "�ȿ�ʼ�����£�",
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