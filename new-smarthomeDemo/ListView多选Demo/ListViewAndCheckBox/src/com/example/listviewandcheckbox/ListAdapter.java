package com.example.listviewandcheckbox;

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
 * @author Administrator
 * @year 2019
 * @Todo TODO 自定义适配器（重写ListView）
 * @package_name com.example.listviewandcheckbox
 * @project_name ListViewAndCheckBox
 * @file_name ListAdapter.java
 */
public class ListAdapter extends BaseAdapter {
	// 数据库(并不是，使用Bean存储)
	private List<Bean> list = new ArrayList<Bean>();
	// 存储勾选状态
	private Map<Integer, Boolean> isCheck = new HashMap<Integer, Boolean>();

	// 上下文
	private Context mContext;

	// 构造方法
	public ListAdapter(Context mContext) {
		super();
		this.mContext = mContext;
		// 默认不选中
		initCheck(false);
	}

	// 初始化map合集
	public void initCheck(boolean flag) {
		// TODO Auto-generated method stub
		// map集合数量和list是一致的
		for (int i = 0; i < list.size(); i++) {
			// 默认显示
			isCheck.put(i, flag);
		}
	}

	// 设置数据
	public void setData(List<Bean> data) {
		// TODO Auto-generated method stub
		this.list = data;
	}

	public void addData(Bean bean) {
		// 下标数据
		list.add(0, bean);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// 如果数值为null就返回一个0
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

	// 优化
	public static class ViewHolder {
		public TextView title;
		public CheckBox cbCheckBox;
	}

	// 全选按钮获取状态s
	public Map<Integer, Boolean> getMap() {
		return isCheck;
	}

	// 删除一个数据
	public void removeData(int postion) {
		list.remove(postion);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		View view = null;
		// 判断是不是首次进入程序
		if (convertView == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) view.findViewById(R.id.tvTitle);
			viewHolder.cbCheckBox = (CheckBox) view
					.findViewById(R.id.cbCheckBox);
			// 标记，可以复用
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		// 得到对象
		Bean bean = list.get(position);
		// 填充数据
		viewHolder.title.setText(bean.getTitle().toString());
		// 勾选框点击事件
		viewHolder.cbCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// 用map集合保存
						isCheck.put(position, isChecked);
					}
				});
		// 设置状态
		if (isCheck.get(position) == null) {
			isCheck.put(position, false);
		}
		viewHolder.cbCheckBox.setChecked(isCheck.get(position));
		return view;
	}
}
