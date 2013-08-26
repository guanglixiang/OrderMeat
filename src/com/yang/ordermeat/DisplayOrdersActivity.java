package com.yang.ordermeat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;


public class DisplayOrdersActivity extends Activity implements  OnClickListener{
	private ListView ordermeatListView;
	//订单适配器
	private OrderMeatAdapter adapter;
	//存储所有订单信息
	private Button conbt;
	private Button signbt ;
	private Button startbt;
	private Button warnbt;
	private Button checkbt;
	private Button storagebt;
	private List<OrderMeat> ordermeats = new ArrayList<OrderMeat>();
    private NetworkStateBroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_orders);
		ordermeatListView = (ListView) findViewById(R.id.ordermeat_listview);
		conbt = (Button) findViewById(R.id.connect_status);
		startbt = (Button) findViewById(R.id.start);
		warnbt = (Button) findViewById(R.id.warnning);
		checkbt = (Button) findViewById(R.id.check);
		storagebt = (Button) findViewById(R.id.storaged);
		signbt = (Button) findViewById(R.id.sign_in);
		conbt.setOnClickListener(this);
		startbt.setOnClickListener(this);
		warnbt.setOnClickListener(this);
		checkbt.setOnClickListener(this);
		storagebt.setOnClickListener(this);
		signbt.setOnClickListener(this);
		//初始化显示的数据
		for(int i=0;i<100;i++){
			OrderMeat ordermeat = new OrderMeat();
			ordermeat.setOrderAddre("延安西路726号华敏翰尊11楼-J室12房");
			ordermeat.setOrderDay("06-04");
			ordermeat.setOrdernum(String.valueOf(i));
			ordermeat.setOrdertime("14:50");
			ordermeat.setOrderAmou("72元");
			ordermeat.setOrderStatus("已打印");
			ordermeats.add(ordermeat);
		}
		adapter = new OrderMeatAdapter(this, R.layout.ordermeat_item, ordermeats);
		ordermeatListView.setAdapter(adapter);
		onItemClick();
		
	}
	
	/*
	 * 注册网络连接状态的广播，用以实时显示连接状态
	 */
	@Override
	public void onResume(){
		receiver=new NetworkStateBroadcastReceiver(conbt);
	  	IntentFilter filter=new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(receiver, filter);
		super.onResume();
	}
	
	@Override
	public void onStop(){
	    unregisterReceiver(receiver);
        super.onStop();
	}

	private void onItemClick() {
		ordermeatListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("ordermeatslist", (ArrayList<? extends Parcelable>) ordermeats);
				bundle.putLong("position", arg3);
				intent.putExtras(bundle);
				intent.setClass(DisplayOrdersActivity.this, ManageOrderActivity.class);
				DisplayOrdersActivity.this.startActivity(intent);
			}});
	}

	
	//handle bottom buttons
	@Override
	public void onClick(View v) {
		HandleBottombuttons handlebt = new HandleBottombuttons(this, v);
		handlebt.handButton(v);
		
	}

 
}

//class NetStateReceiver extends BroadcastReceiver{
//	private Button bt;
//	public NetStateReceiver(Button bt){
//		this.bt=bt;
//	}
//
//	@Override
//	  public void onReceive(Context arg0, Intent arg1) {
//		        ConnectivityManager connectivityManager = (ConnectivityManager) arg0  
//		                .getSystemService(Context.CONNECTIVITY_SERVICE);  
//		        if (connectivityManager != null){
//		        	NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();  
//		        	 if (net_info != null) {
//		        		 for (int i = 0; i < net_info.length; i++) {  
//			                    // 判断获得的网络状态是否是处于连接状态  
//			                	Log.d("xiangkang", "------143----------");
//			                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {  
//			                    	Log.d("xiangkang", "------145----------");
//			                    	bt.setTextColor(Color.GREEN);
//			                    	bt.setText(R.string.connected);
//			                    	return;
//			                    }
//		        		 }
//		        	 }
//		        }
//	        	bt.setTextColor(Color.BLACK);
//            	bt.setText(R.string.disconnected); 
//	  }
//}
