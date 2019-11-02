package com.example.f1006;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.f1006.R.id;

public class JIbenActivity extends Fragment {
	RadioButton ratv,radvd,rakt,ralampz,ralampy,racl,radoor,rafan,rak,rat,rag;
	boolean dqk=false,dqg=false,dqt=false;
	public static float temp,hum,smoke,ill,air,co2,pm25,gas,man;
	View myview,myzhu;
	RelativeLayout jb;
	boolean tu=false;
	SeekBar seekBar;
	SQLiteDatabase db;
	TextView tvqi,tvzd;
	int tvmax=100,tvmin=0,tvprozhi,tvhuazhi;
	int idc=0;
	ListView listjiben;
	List<Map<String, String>> list=new ArrayList<Map<String,String>>();
	ContentValues cValues;
	
	
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view=inflater.inflate(R.layout.activity_jiben,container,false);
	ratv=(RadioButton)view.findViewById(R.id.rad1);
	radvd=(RadioButton)view.findViewById(R.id.Rad2);
	rakt=(RadioButton)view.findViewById(R.id.Rad3);
	ralampz=(RadioButton)view.findViewById(R.id.Rad4);
	ralampy=(RadioButton)view.findViewById(R.id.Rad5);
	racl=(RadioButton)view.findViewById(R.id.Rad6);
	radoor=(RadioButton)view.findViewById(R.id.Rad7);
	rafan=(RadioButton)view.findViewById(R.id.Rad7);
	rak=(RadioButton)view.findViewById(R.id.Radk);
	rat=(RadioButton)view.findViewById(R.id.Radt);
	rag=(RadioButton)view.findViewById(R.id.Radg);
	myview=(View)view.findViewById(R.id.myview);
	myzhu=(View)view.findViewById(R.id.myzhu);
	jb=(RelativeLayout)view.findViewById(R.id.jb);
	seekBar=(SeekBar)view.findViewById(R.id.seekBar1);
	myzhu.setVisibility(View.INVISIBLE);
	tvqi=(TextView)view.findViewById(R.id.textqi);
	tvzd=(TextView)view.findViewById(R.id.Textzhong);
	listjiben=(ListView)view.findViewById(R.id.listView1);
	
	try {
		db=this.getActivity().openOrCreateDatabase("shuju.db", Context.MODE_PRIVATE, null);
		db.execSQL("create table shuju(id text not null,zhi text not null)");
	} catch (Exception e) {
		// TODO: handle exception
	}
   
	cValues=new ContentValues();
	cValues.put("id", String.valueOf(ill));
	cValues.put("zhi", "π‚’’");
   
       idc+=1;
//     Map<String, String> map=new HashMap<String, String>();
//     map.put("id", String.valueOf(idc));
//     map.put("zhi",String.valueOf("5"));
//     list.add(map);
     SimpleAdapter adapter=new SimpleAdapter(getActivity(), list, R.layout.listjiben, new String[]{"ill"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4,R.id.te5,R.id.te6,R.id.te7,R.id.te8,R.id.te9});
     listjiben.setAdapter(adapter);
     
     
	ControlUtils.getData();
	SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		@Override
		public void onResult(DeviceBean arg0) {
			// TODO Auto-generated method stub
			 getActivity().runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			});
		}
	});
	seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			if (tvprozhi==100) {
				   tvmin=tvmin+100;
				   tvmax=tvmax+100;
				   tvqi.setText(String.valueOf(tvmin));
				   tvzd.setText(String.valueOf(tvmax));
				   seekBar.setProgress(0);
				}
			else if (tvprozhi==0) {
					if (!tvqi.getText().toString().equals("0")) {
						tvmin=tvmin-100;
						tvmax=tvmax-100;
						tvqi.setText(String.valueOf(tvmin));
						tvzd.setText(String.valueOf(tvmax));
						seekBar.setProgress(100);
					}
					
				}
				tvhuazhi=tvmin+tvprozhi;
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			tvprozhi=progress;
		}
	});
     
	
	rak.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (rak.isChecked()) {
				dqk=true;
				rag.setChecked(false);
				rat.setChecked(false);
			}else {
				dqk=false;
			}
		}
	});
	rag.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (rag.isChecked()) {
				dqg=true;
				rak.setChecked(false);
				rat.setChecked(false);
			}else {
				dqg=false;
				
			}
		}
	});
	rat.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (rat.isChecked()) {
				dqt=true;
				rag.setChecked(false);
				rak.setChecked(false);
			}else {
				dqt=false;
			}
		}
	});
	ratv.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (dqk) {
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2", ConstantUtil.OPEN);
			}else if (dqg) {
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2", ConstantUtil.CLOSE);
			}
			racl.setChecked(false);
		    radvd.setChecked(false);
			rakt.setChecked(false);
			ralampz.setChecked(false);
			ralampy.setChecked(false);
			radoor.setChecked(false);
			rafan.setChecked(false);
		}
	});
	radvd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (dqk) {
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3", ConstantUtil.OPEN);
			}else if (dqg) {
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3", ConstantUtil.CLOSE);
			}
			racl.setChecked(false);
		    ratv.setChecked(false);
			rakt.setChecked(false);
			ralampz.setChecked(false);
			ralampy.setChecked(false);
			radoor.setChecked(false);
			rafan.setChecked(false);
		}
	});
	rakt.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (dqk) {
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1", ConstantUtil.OPEN);
			}else if (dqg) {
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1", ConstantUtil.CLOSE);
			}
			racl.setChecked(false);
		    radvd.setChecked(false);
			ratv.setChecked(false);
			ralampz.setChecked(false);
			ralampy.setChecked(false);
			radoor.setChecked(false);
			rafan.setChecked(false);
		}
	});
	ralampz.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (dqk) {
				ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else if (dqg) {
				ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			racl.setChecked(false);
		    radvd.setChecked(false);
			rakt.setChecked(false);
			ratv.setChecked(false);
			ralampy.setChecked(false);
			radoor.setChecked(false);
			rafan.setChecked(false);
		}
	});
	ralampy.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (dqk) {
				ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else if (dqg) {
				ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			racl.setChecked(false);
		    radvd.setChecked(false);
			rakt.setChecked(false);
			ralampz.setChecked(false);
			ratv.setChecked(false);
			radoor.setChecked(false);
			rafan.setChecked(false);
		}
	});
	racl.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (dqk) {
				ControlUtils.control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}else if (dqg) {
				ControlUtils.control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}else if (dqt) {
				ControlUtils.control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
			ratv.setChecked(false);
		    radvd.setChecked(false);
			rakt.setChecked(false);
			ralampz.setChecked(false);
			ralampy.setChecked(false);
			radoor.setChecked(false);
			rafan.setChecked(false);
		}
	});
	radoor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (dqk) {
				ControlUtils.control(ConstantUtil.RFID_Open_Door,ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}else if (dqg) {
			
			}
			racl.setChecked(false);
		    radvd.setChecked(false);
			rakt.setChecked(false);
			ralampz.setChecked(false);
			ralampy.setChecked(false);
			ratv.setChecked(false);
			rafan.setChecked(false);
		}
	});
	rafan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (dqk) {
				ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else if (dqg) {
				ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			racl.setChecked(false);
		    radvd.setChecked(false);
			rakt.setChecked(false);
			ralampz.setChecked(false);
			ralampy.setChecked(false);
			radoor.setChecked(false);
			rafan.setChecked(false);
		}
	});
	jb.setOnLongClickListener(new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
				myzhu.setVisibility(View.VISIBLE);
				myview.setVisibility(View.INVISIBLE);
			
			return false;
		}
	});
	
	return view;
}

}
