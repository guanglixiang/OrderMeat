package com.yang.ordermeat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Button;


public class NetworkStateBroadcastReceiver extends BroadcastReceiver{
	private Button bt;
	public NetworkStateBroadcastReceiver(Button bt){
		this.bt=bt;
	}

	@Override
	  public void onReceive(Context arg0, Intent arg1) {
		        ConnectivityManager connectivityManager = (ConnectivityManager) arg0  
		                .getSystemService(Context.CONNECTIVITY_SERVICE);  
		        if (connectivityManager != null){
		        	NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();  
		        	 if (net_info != null) {
		        		 for (int i = 0; i < net_info.length; i++) {  
			                    // 判断获得的网络状态是否是处于连接状态  
			                	Log.d("xiangkang", "------143----------");
			                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {  
			                    	Log.d("xiangkang", "------145----------");
			                    	bt.setTextColor(Color.GREEN);
			                    	bt.setText(R.string.connected);
			                    	return;
			                    }
		        		 }
		        	 }
		        }
	        	bt.setTextColor(Color.BLACK);
            	bt.setText(R.string.disconnected); 
	  }
}