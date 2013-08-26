package com.yang.ordermeat;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class OrderMeat implements Parcelable {

	//订单号
	private String ordernum;
	//订单金额
	private String orderamou;
	//订单日期
	private String orderday;
	//订单时刻
	private String ordertime;
	//订单状态
	private String orderstatus;
	//订单送达地址
	private String orderaddre;
	//订单负责人
	private String ordercharger;
	//要显示订单的类型，提供给adapter类使用
	private int ordertype;
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getOrderAmou() {
		return orderamou;
	}
	public void setOrderAmou(String orderamou) {
		this.orderamou = orderamou;
	}
	
	public String getOrderDay() {
		return orderday;
	}
	public void setOrderDay(String orderday) {
		this.orderday = orderday;
	}
	
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public String getOrderAddre() {
		return orderaddre;
	}
	public void setOrderAddre(String orderaddre) {
		this.orderaddre = orderaddre;
	}
	
	public String getOrderStatus() {
		return orderstatus;
	}
	public void setOrderStatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	public String getOrderCharger() {
		return ordercharger;
	}
	public void setOrderCharger(String ordercharger) {
		this.ordercharger = ordercharger;
	}
	
	public int getOrderType() {
		return ordertype;
	}
	public void setOrderType(int ordertype) {
		this.ordertype = ordertype;
	}


	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(ordernum);
		dest.writeString(orderamou);
		dest.writeString(orderday);
		dest.writeString(ordertime);
		dest.writeString(orderstatus);
		dest.writeString(orderaddre);
	}
	
    public static final Parcelable.Creator<OrderMeat> CREATOR = new Creator<OrderMeat>() {
        public OrderMeat createFromParcel(Parcel source) {
            Log.i("dd","createFromParcel:"+source);
            // TODO Auto-generated method stub
            OrderMeat ordermeat = new OrderMeat();
            ordermeat.ordernum = source.readString();
            ordermeat.orderamou = source.readString();
            ordermeat.orderday = source.readString();
            ordermeat.ordertime = source.readString();
            ordermeat.orderstatus = source.readString();
            ordermeat.orderaddre = source.readString();
            return ordermeat;
        }
        public OrderMeat[] newArray(int size) {
            // TODO Auto-generated method stub
            return new OrderMeat[size];
        }
    };
}
