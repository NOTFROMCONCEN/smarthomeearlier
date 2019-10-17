package cn.com.jbit.sendmessage;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * ���Ͷ���
 * @version 1.0
 * @date 2013-10-12
 */
public class SendMessageActivity extends Activity {
	
	private EditText _etPhone=null;//�ֻ�����
	private EditText _etContent=null;//��������
	private Button _btnSend=null;//���Ͱ�ť
	
	/** 
	 * Called when the activity is first created. 
	 * �����
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.main);//���������ļ�
		//ʵ�����ؼ���Ϣ
		initView();
	}
	/**
	 * ʵ�����ؼ���Ϣ
	 */
	private void initView() {
		_etPhone=(EditText)findViewById(R.id.etPhone);
		_etContent=(EditText)findViewById(R.id.etContent);
		_btnSend=(Button)findViewById(R.id.btnSend);
		//���Ͱ�ť�ĵ���¼�����
		_btnSend.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// ���Ͷ���
				sendMessage();
			}
		});
	}
	/**
	 * ���Ͷ���
	 */
	private void sendMessage() {
		//��ȡ������ֻ�����Ͷ�������
		String phone = _etPhone.getText().toString();
		String content = _etContent.getText().toString();
		//�ж��ֻ�����Ͷ������ݲ���Ϊ��
		if(phone.equals("")||content.equals("")){
			Toast.makeText(SendMessageActivity.this, "�����˺Ͷ������ݶ�����Ϊ��", Toast.LENGTH_LONG).show();
			return;
		}
		//�������Ź�����
		SmsManager smsManager = SmsManager.getDefault();

		//�����������70,���ֳɶ������ŷ���
		if (content.length() > 70) {
			Log.i("==", "content.length() > 70)");
			//��������Ϣ���в��
			List<String> msgs = smsManager.divideMessage(content);
			//ѭ�����Ͷ���
			for (String msg : msgs) {
				Log.i("==", "for (String msg : msgs)");
				//���Ͷ���15901077153
				smsManager.sendTextMessage(phone, null, msg, null, null);
				Log.i("==", "sendTextMessage");
			}
		} else {
			Log.i("==", "else");
			smsManager.sendTextMessage(phone, null, content, null, null);
		}
		Toast.makeText(SendMessageActivity.this, "�����ѷ���", Toast.LENGTH_LONG).show();
	}
}
