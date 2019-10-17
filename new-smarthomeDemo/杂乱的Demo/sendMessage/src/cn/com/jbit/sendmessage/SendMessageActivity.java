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
 * 发送短信
 * @version 1.0
 * @date 2013-10-12
 */
public class SendMessageActivity extends Activity {
	
	private EditText _etPhone=null;//手机号码
	private EditText _etContent=null;//短信内容
	private Button _btnSend=null;//发送按钮
	
	/** 
	 * Called when the activity is first created. 
	 * 主入口
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.main);//关联布局文件
		//实例化控件信息
		initView();
	}
	/**
	 * 实例化控件信息
	 */
	private void initView() {
		_etPhone=(EditText)findViewById(R.id.etPhone);
		_etContent=(EditText)findViewById(R.id.etContent);
		_btnSend=(Button)findViewById(R.id.btnSend);
		//发送按钮的点击事件监听
		_btnSend.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				// 发送短信
				sendMessage();
			}
		});
	}
	/**
	 * 发送短信
	 */
	private void sendMessage() {
		//获取输入的手机号码和短信内容
		String phone = _etPhone.getText().toString();
		String content = _etContent.getText().toString();
		//判断手机号码和短信内容不能为空
		if(phone.equals("")||content.equals("")){
			Toast.makeText(SendMessageActivity.this, "收信人和短信内容都不能为空", Toast.LENGTH_LONG).show();
			return;
		}
		//创建短信管理类
		SmsManager smsManager = SmsManager.getDefault();

		//如果字数超过70,需拆分成多条短信发送
		if (content.length() > 70) {
			Log.i("==", "content.length() > 70)");
			//将短信信息进行拆分
			List<String> msgs = smsManager.divideMessage(content);
			//循环发送短信
			for (String msg : msgs) {
				Log.i("==", "for (String msg : msgs)");
				//发送短信15901077153
				smsManager.sendTextMessage(phone, null, msg, null, null);
				Log.i("==", "sendTextMessage");
			}
		} else {
			Log.i("==", "else");
			smsManager.sendTextMessage(phone, null, content, null, null);
		}
		Toast.makeText(SendMessageActivity.this, "短信已发送", Toast.LENGTH_LONG).show();
	}
}
