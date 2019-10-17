package com.example.listviewcheckbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * �Զ���������
 * 
 * @author LGL
 * 
 */
public class ListAdapter extends BaseAdapter {
	// ���ݼ�
	private List<Bean> list = new ArrayList<Bean>();
	// ������
	private Context mContext;
	// �洢��ѡ��״̬��map����
	private Map<Integer, Boolean> isCheck = new HashMap<Integer, Boolean>();

	// ���췽��
	public ListAdapter(Context mContext) {
		super();
		this.mContext = mContext;
		// Ĭ��Ϊ��ѡ��
		initCheck(false);
	}

	// ��ʼ��map����
	public void initCheck(boolean flag) {
		// map���ϵ�������list��������һ�µ�
		for (int i = 0; i < list.size(); i++) {
			// ����Ĭ�ϵ���ʾ
			isCheck.put(i, flag);
		}
	}

	// ��������
	public void setData(List<Bean> data) {
		this.list = data;
	}

	// �������
	public void addData(Bean bean) {
		// �±� ����
		list.add(0, bean);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// ���Ϊnull�ͷ���һ��0
		return list != null ? list.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		View view = null;
		// �ж��ǲ��ǵ�һ�ν���
		if (convertView == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) view.findViewById(R.id.tvTitle);
			viewHolder.cbCheckBox = (CheckBox) view
					.findViewById(R.id.cbCheckBox);
			// ��ǣ����Ը���
			view.setTag(viewHolder);
		} else {
			view = convertView;
			// ֱ���ù�����
			viewHolder = (ViewHolder) view.getTag();
		}
		// �õ�����
		Bean bean = list.get(position);
		// �������
		viewHolder.title.setText(bean.getTitle().toString());
		// ��ѡ��ĵ���¼�
		viewHolder.cbCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// ��map���ϱ���
						isCheck.put(position, isChecked);
					}
				});
		// ����״̬
		if (isCheck.get(position) == null) {
			isCheck.put(position, false);
		}
		viewHolder.cbCheckBox.setChecked(isCheck.get(position));
		return view;
	}

	// �Ż�
	public static class ViewHolder {
		public TextView title;
		public CheckBox cbCheckBox;
	}

	// ȫѡ��ť��ȡ״̬
	public Map<Integer, Boolean> getMap() {
		// ����״̬
		return isCheck;
	}

	// ɾ��һ������
	public void removeData(int position) {
		list.remove(position);
	}
}