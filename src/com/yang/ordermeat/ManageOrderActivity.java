package com.yang.ordermeat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ManageOrderActivity extends Activity implements OnItemClickListener{
	private showOrderNumAdapter ordernumadapter;
	private showOrderNumAdapter waiteradapter;
	private ArrayList<OrderMeat> orderlist = null;
	private ArrayList<OrderMeat> mWaiterList = null;
	private Map<Integer, SparseBooleanArray> orderwithwaiterMap;
	private  SparseBooleanArray selectedOrderArray;
	private final int SHOW_WAITERS=1;
	private final int SHOW_ORDER_NUM=2;
	private boolean Selected = true;
	private int lastClickBt = -2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);  
		 setContentView(R.layout.manageorderlayout);  
		 Bundle bundle = this.getIntent().getExtras();
		 orderlist =new ArrayList<OrderMeat>();
		 mWaiterList = new ArrayList<OrderMeat>();
		 orderlist = bundle.getParcelableArrayList("ordermeatslist");
		 long position =  bundle.getLong("position");
		 initWaitersData();
		 initOrdersData();
		 GridView ordernumgridview = (GridView) findViewById(R.id.orders_display);
		 ordernumadapter = new showOrderNumAdapter(this, orderlist,SHOW_ORDER_NUM);
		 ordernumgridview.setAdapter(ordernumadapter);
		 ordernumgridview.setOnItemClickListener(this);
		 GridView waitersgridview = (GridView) findViewById(R.id.waiter_display);
		 waiteradapter = new showOrderNumAdapter(this, mWaiterList,SHOW_WAITERS);
		 waitersgridview.setAdapter(waiteradapter);
		 waitersgridview.setOnItemClickListener(this);
		 selectedOrderArray = new SparseBooleanArray(mWaiterList.size());  
		 orderwithwaiterMap = new HashMap<Integer, SparseBooleanArray>();
	}
	/*
	 * 初始化所有服务员
	 */
	private void initWaitersData(){
		//waiter.setOrderType(SHOW_WAITERS);
		for (int i = 0; i < 100; i++) {
			OrderMeat waiter = new OrderMeat();
			waiter.setOrderCharger("wangxiaoer");
			mWaiterList.add(waiter);
		}
	}

	/*
	 * 初始化所有订单
	 */
	private void initOrdersData(){
		//waiter.setOrderType(SHOW_WAITERS);
		for (int i = 0; i < 100; i++) {
			OrderMeat waiter = new OrderMeat();
			waiter.setOrderCharger("wangxiaoer");
			mWaiterList.add(waiter);
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		CheckBox cbBox = (CheckBox) arg1.findViewById(R.id.checkbox);
		cbBox.toggle(); 
		switch (arg0.getAdapter().getItemViewType(arg2)) {
		case SHOW_WAITERS:
			if (arg2!=lastClickBt) {
				if (lastClickBt>=0){
					Log.d("xiangyang", "92-------------lastClickBt="+lastClickBt);
				//	Log.d("xiangyang", "arg0.getChildAt="+arg0.getAdapter().getView(lastClickBt, null, null).setBackgroundColor(Color.WHITE));
					arg0.getChildAt(lastClickBt).setBackgroundColor(Color.WHITE);
					Log.d("xiangyang", "92-------------arg0.getChildAt("+lastClickBt+").toString="+arg0.getChildAt(lastClickBt).toString());
					arg1.setBackgroundColor(Color.RED);
					lastClickBt =  arg2;
				}else{
					Log.d("xiangyang", "98-------------lastClickBt="+lastClickBt);
					arg1.setBackgroundColor(Color.RED);
					lastClickBt =  arg2;
				}
//				if(-2==lastClickBt){
//					
//					arg1.setBackgroundColor(Color.RED);
//					orderwithwaiterMap.put(arg2, selectedOrderArray);
//					lastClickBt = arg2;
//				}else{
//					if (lastClickBt>=0) {
//						arg0.getChildAt(lastClickBt).setBackgroundColor(Color.WHITE);
//					}
//					
//					//换成rediobutton
//					if(selectedOrderArray.size()==0||orderwithwaiterMap.size()>=2){
//						//Toast.makeText(this, "请先选择订单",Toast.LENGTH_SHORT).show();
//						orderwithwaiterMap.clear();
//					}
//					if (cbBox.isChecked()) {
//						arg1.setBackgroundColor(Color.RED);
//						orderwithwaiterMap.put(arg2, selectedOrderArray);
//						lastClickBt = arg2;
//					}else{
//						arg1.setBackgroundColor(Color.WHITE);
//					}
//				}
			}else {
				if (cbBox.isChecked()) {
					Log.d("xiangyang", "117-------------");
					arg1.setBackgroundColor(Color.RED);
//					orderwithwaiterMap.put(arg2, selectedOrderArray);
//					lastClickBt = arg2;
				}else{
					Log.d("xiangyang", "122-------------");
					arg1.setBackgroundColor(Color.WHITE);
				}
			}
			Toast.makeText(this, "当前的第"+arg2+"个checkbox状态为"+cbBox.isChecked(), Toast.LENGTH_SHORT).show();
			break;
		case SHOW_ORDER_NUM:
			Toast.makeText(this, "当前checkbox状态为"+cbBox.isChecked(), Toast.LENGTH_SHORT).show();
			//Toast.makeText(this, "当前点的是订单",Toast.LENGTH_SHORT).show();
			if(cbBox.isChecked()){
				selectedOrderArray.put(arg2, cbBox.isChecked());
			}else{
				selectedOrderArray.delete(arg2);
			}
			for (int i = 0; i < selectedOrderArray.size(); i++) {
				selectedOrderArray.keyAt(i);
				
			}
//			Map<Integer, Boolean> selectstatusMap = new HashMap<Integer, Boolean>();
//			selectstatusMap.put(arg2, cbBox.isChecked());
//			if(cbBox.isChecked()){
//				
//			}
//			selectstatusMap.put(arg2, Selected);
//			List<Map<Integer, Boolean>> selectwaiter = new ArrayList<Map<Integer, Boolean>>();
//			selectwaiter.add(selectstatusMap);

		default:
			break;
		}
		if(!orderwithwaiterMap.isEmpty()){
			Log.d("zhengye", "key="+orderwithwaiterMap.keySet().iterator().next());
			for (int i = 0; i < orderwithwaiterMap.get(orderwithwaiterMap.keySet().iterator().next()).size(); i++) {
				Log.d("zhengye", "value="+orderwithwaiterMap.get(orderwithwaiterMap.keySet().iterator().next()).keyAt(i));
			}
		}
		
	}

}	
	

class showOrderNumAdapter extends BaseAdapter {
	private List<OrderMeat> ol;
	private Context mContext;  
	private int TYPE;
	public showOrderNumAdapter(Context c,
			List<OrderMeat> orderlist,int type) {
		// TODO Auto-generated constructor stub
		super();
		mContext=c;
		ol = orderlist;
		TYPE = type;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		//Log.d("zhengye", "ol.size="+ol.size());
		return ol.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (TYPE==1) return ol.get(position).getOrderCharger();
		else if (TYPE ==2) return ol.get(position).getOrdernum();
		else return ol.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public int getItemViewType(int position){
		return TYPE;
	}
	
	public int getAdapterType() {
		return TYPE;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CheckBox ordernum;  
		if(convertView ==null){
			convertView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.checkbox, null);
		}
		//Log.d("zhengye", "getItem="+(CharSequence) getItem(position));
//		if(TYPE==1){
//			RadioButton ordernum =  new RadioButton(mContext);
//			ordernum.setLayoutParams(new GridView.LayoutParams(50,30)); 
//			ordernum.setBackgroundColor(Color.RED);
//			ordernum.setText( (CharSequence) getItem(position));
//			return ordernum;
//
//		}else{
			ordernum = (CheckBox) convertView.findViewById(R.id.checkbox);
			ordernum.setLayoutParams(new GridView.LayoutParams(50,30)); 
			//ordernum.setBackgroundColor(Color.WHITE);
			ordernum.setText( (CharSequence) getItem(position));
			return ordernum;
		//}
		//ordernum= new CheckBox(mContext);
	}
	
}
