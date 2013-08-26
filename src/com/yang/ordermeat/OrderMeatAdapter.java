package com.yang.ordermeat;

import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class OrderMeatAdapter extends ArrayAdapter<OrderMeat>{
	private int itemviewId;
	final int VIEW_TYPE = 2;  
	final int DISPLAY_DETAILS = 0;  
    final int DISPLAY_STATUS = 1;

	public OrderMeatAdapter(Context context, int resource,
			List<OrderMeat> objects) {
		super(context, resource, objects);
		itemviewId = resource;
	}
	@Override
	public int getViewTypeCount(){
		return VIEW_TYPE;
	}
	@Override
	public int getItemViewType(int position){
		int p = position;
		if (p%2==0) 
			return DISPLAY_DETAILS;
		else
			return DISPLAY_STATUS;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		OrderMeat ordermeat = getItem(position);
		int itemviewtype = getItemViewType(position);
		Holder holder;
		if (convertView == null) {		
			convertView = (LinearLayout) LayoutInflater.from(getContext()).inflate(itemviewId, null);
			holder = new Holder();
			holder.ordernum = (TextView) convertView.findViewById(R.id.order_number);
			holder.orderamou = (TextView) convertView.findViewById(R.id.order_amount);
			holder.orderday = (TextView) convertView.findViewById(R.id.order_day);
			holder.ordertime = (TextView) convertView.findViewById(R.id.order_time);
			holder.orderadd = (TextView) convertView.findViewById(R.id.order_address);
			RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,  
					LayoutParams.WRAP_CONTENT);  
			mLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, R.id.order_info);  
			if(DISPLAY_DETAILS==itemviewtype){
				View display_details = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.display_order_details, null);
				((ViewGroup) convertView.findViewById(R.id.order_info)).addView(display_details,mLayoutParams);
				holder.ordercharger = (TextView) convertView.findViewById(R.id.order_charger);
				holder.ordercurrentamou = (TextView) convertView.findViewById(R.id.order_current);
				holder.ordertotalamou = (TextView) convertView.findViewById(R.id.order_total);
			}else if(DISPLAY_STATUS==itemviewtype){
				View display_status = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.display_order_status,null);
				((ViewGroup) convertView.findViewById(R.id.order_info)).addView(display_status,mLayoutParams);
				holder.orderstatus = (TextView) convertView.findViewById(R.id.order_status);
			}
			convertView.setTag(holder);

		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.ordernum.setText(ordermeat.getOrdernum());
		holder.orderamou.setText(ordermeat.getOrderAmou());
		holder.orderday.setText(ordermeat.getOrderDay());
		holder.ordertime.setText(ordermeat.getOrdertime());
		holder.orderadd.setText(ordermeat.getOrderAddre());
		if(DISPLAY_DETAILS==itemviewtype){
			holder.ordercharger.setText("王小二");
			holder.ordercurrentamou.setText("210元/12");
			holder.ordertotalamou.setText("1350元/29");
		}else if(DISPLAY_STATUS==itemviewtype){
			holder.orderstatus.setText(ordermeat.getOrderStatus());
		}
		return convertView;
	}


	private class Holder{
		TextView ordernum;
		TextView orderamou;
		TextView orderday;
		TextView ordertime;
		TextView orderadd;
		TextView ordercharger;
		TextView ordercurrentamou;
		TextView ordertotalamou;
		TextView orderstatus;
	}
	
}
