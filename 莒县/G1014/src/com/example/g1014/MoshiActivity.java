package com.example.g1014;

import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MoshiActivity extends Fragment {
	CheckBox chaf,chlj,chzdy;
	CheckBox chlamp,chfan,chbjd,chdoor,chtd1,chtd2,chtd3,chcl;
	Spinner spdx;
	EditText etlian;
	Switch swmoshi;
	boolean kg=false,kzk=false,kzg=false,af=false,lj=false,bjd=false,lamp=false,door=false,fan=false,cl=false,tv=false,dvd=false,kt=false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_moshi, container,false);
		chaf=(CheckBox)view.findViewById(R.id.chaf);
		chlj=(CheckBox)view.findViewById(R.id.Chlj);
		chzdy=(CheckBox)view.findViewById(R.id.Chzdy);
		chlamp=(CheckBox)view.findViewById(R.id.chlamp);
		chfan=(CheckBox)view.findViewById(R.id.Chfan);
		chbjd=(CheckBox)view.findViewById(R.id.Chbjd);
		chdoor=(CheckBox)view.findViewById(R.id.Chdoor);
		chtd1=(CheckBox)view.findViewById(R.id.Chtd1);
		chtd2=(CheckBox)view.findViewById(R.id.Chtd2);
		chtd3=(CheckBox)view.findViewById(R.id.Chtd3);
		chcl=(CheckBox)view.findViewById(R.id.Chcl);
		spdx=(Spinner)view.findViewById(R.id.Spdx);
		etlian=(EditText)view.findViewById(R.id.edlian);
		swmoshi=(Switch)view.findViewById(R.id.swmoshi);
		
		chaf.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
		      	af=true;
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (af) {
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (JibenActivity.man==1) {
								ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}
						}
					}
				}).start();
			}else {
				af=false;
			}
				
			}
		});
		chlj.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
				lj=true;
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (lj) {
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (JibenActivity.man==1) {
								ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}else if (JibenActivity.gas>800) {
								ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}
						}
					}
				}).start();
			}else {
				lj=false;
			}
				
			}
		});
		chzdy.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (etlian.getText().toString().isEmpty()) {
					Toast.makeText(getActivity(), "输入数据为空", Toast.LENGTH_LONG).show();
					chzdy.setChecked(false);
				}else {
					int fazhi=Integer.parseInt(etlian.getText().toString());
					String dx=spdx.getSelectedItem().toString();
					if (dx.equals("大于")&&JibenActivity.temp>fazhi&&kzk) {
						kg=true;
					}else if (dx.equals("大于")&&JibenActivity.temp>fazhi&&kzg) {
						kg=false;
					}else if (dx.equals("小于")&&JibenActivity.temp<fazhi&&kzk) {
						kg=false;
					}else if (dx.equals("小于")&&JibenActivity.temp<fazhi&&kzg) {
						kg=false;
					}
				}
			}
		});
	    swmoshi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					kzk=true;
				}else {
				   kzg=true;
				}
			}
		});
	    chlamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (kg) {
					if (isChecked) {
					
					lamp=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (lamp) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (kzk) {
									ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								}else if (kzg) {
									ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				}else {
					lamp=false;
				}
				}
			}
		});
	    chbjd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    	
	    	@Override
	    	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    		// TODO Auto-generated method stub
	    		if (kg) {
	    			bjd=true;
	    			new Thread(new Runnable() {
	    				
	    				@Override
	    				public void run() {
	    					// TODO Auto-generated method stub
	    					while (bjd) {
	    						try {
	    							Thread.sleep(1000);
	    						} catch (Exception e) {
	    							// TODO: handle exception
	    						}
	    						if (kzk) {
	    							ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
	    						}else if (kzg) {
	    							ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
	    						}
	    					}
	    				}
	    			}).start();
	    		}else {
	    			bjd=false;
	    		}
	    	}
	    });
	    chfan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    	
	    	@Override
	    	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    		// TODO Auto-generated method stub
	    		if (kg) {
	    			fan=true;
	    			new Thread(new Runnable() {
	    				
	    				@Override
	    				public void run() {
	    					// TODO Auto-generated method stub
	    					while (fan) {
	    						try {
	    							Thread.sleep(1000);
	    						} catch (Exception e) {
	    							// TODO: handle exception
	    						}
	    						if (kzk) {
	    							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
	    						}else if (kzg) {
	    							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
	    						}
	    					}
	    				}
	    			}).start();
	    		}else {
	    			fan=false;
	    		}
	    	}
	    });
	    chcl.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    	
	    	@Override
	    	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    		// TODO Auto-generated method stub
	    		if (kg) {
	    			cl=true;
	    			new Thread(new Runnable() {
	    				
	    				@Override
	    				public void run() {
	    					// TODO Auto-generated method stub
	    					while (cl) {
	    						try {
	    							Thread.sleep(1000);
	    						} catch (Exception e) {
	    							// TODO: handle exception
	    						}
	    						if (kzk) {
	    							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
	    						}else if (kzg) {
	    							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
	    						}
	    					}
	    				}
	    			}).start();
	    		}else {
	    			cl=false;
	    		}
	    	}
	    });
	    chtd1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    	
	    	@Override
	    	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    		// TODO Auto-generated method stub
	    		if (kg) {
	    			kt=true;
	    			new Thread(new Runnable() {
	    				
	    				@Override
	    				public void run() {
	    					// TODO Auto-generated method stub
	    					while (kt) {
	    						try {
	    							Thread.sleep(1000);
	    						} catch (Exception e) {
	    							// TODO: handle exception
	    						}
	    						if (kzk) {
	    							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1", ConstantUtil.OPEN);
	    						}else if (kzg) {
	    							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"1", ConstantUtil.CLOSE);
	    						}
	    					}
	    				}
	    			}).start();
	    		}else {
	    			kt=false;
	    		}
	    	}
	    });
	    chtd2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    	
	    	@Override
	    	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    		// TODO Auto-generated method stub
	    		if (kg) {
	    			tv=true;
	    			new Thread(new Runnable() {
	    				
	    				@Override
	    				public void run() {
	    					// TODO Auto-generated method stub
	    					while (tv) {
	    						try {
	    							Thread.sleep(1000);
	    						} catch (Exception e) {
	    							// TODO: handle exception
	    						}
	    						if (kzk) {
	    							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5", ConstantUtil.OPEN);
	    						}else if (kzg) {
	    							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"5", ConstantUtil.CLOSE);
	    						}
	    					}
	    				}
	    			}).start();
	    		}else {
	    			tv=false;
	    		}
	    	}
	    });
	    chtd3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    	
	    	@Override
	    	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    		// TODO Auto-generated method stub
	    		if (kg) {
	    			dvd=true;
	    			new Thread(new Runnable() {
	    				
	    				@Override
	    				public void run() {
	    					// TODO Auto-generated method stub
	    					while (dvd) {
	    						try {
	    							Thread.sleep(1000);
	    						} catch (Exception e) {
	    							// TODO: handle exception
	    						}
	    						if (kzk) {
	    							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"8", ConstantUtil.OPEN);
	    						}else if (kzg) {
	    							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8", ConstantUtil.CLOSE);
	    						}
	    					}
	    				}
	    			}).start();
	    		}else {
	    			dvd=false;
	    		}
	    	}
	    });
	    chdoor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	    	
	    	@Override
	    	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    		// TODO Auto-generated method stub
	    		if (kg) {
	    			door=true;
	    			new Thread(new Runnable() {
	    				
	    				@Override
	    				public void run() {
	    					// TODO Auto-generated method stub
	    					while (door) {
	    						try {
	    							Thread.sleep(1000);
	    						} catch (Exception e) {
	    							// TODO: handle exception
	    						}
	    						if (kzk) {
	    							ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
	    						}else if (kzg) {
	    						
	    						}
	    					}
	    				}
	    			}).start();
	    		}else {
	    			door=false;
	    		}
	    	}
	    });
		return view;
	}

}
