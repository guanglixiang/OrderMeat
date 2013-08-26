package com.yang.ordermeat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;



public class SignActivity extends Activity {
	private ArrayList<String> earlystaffslist;
	private ArrayList<HashMap<Integer, Boolean>> selectstatusList;
	private HashMap<Integer, Boolean> earlyMap;
	private EarlyAdapter  earlyshiftadapter;
	private boolean Clicked=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_layout);
		 GridView earlyshift = (GridView) findViewById(R.id.early_shift);
		 GridView nightshift = (GridView) findViewById(R.id.night_shift);
		 GridView takeout = (GridView) findViewById(R.id.takeout);
		 earlystaffslist = new ArrayList<String>();
		 selectstatusList = new ArrayList<HashMap<Integer, Boolean>>();
		 for(int i=1;i<100;i++){
			 if(i%2==0) earlystaffslist.add("王小二");
			 else earlystaffslist.add("王小三");
		 }
		 earlyshiftadapter = new EarlyAdapter(this, earlystaffslist);
		 earlyshift.setAdapter(earlyshiftadapter);
		 nightshift.setAdapter(earlyshiftadapter);
		 takeout.setAdapter(earlyshiftadapter);
		 earlyshift.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
//				if(selectstatusList.get(arg2).get(arg2)!=null){
//					if(selectstatusList.get(arg2).get(arg2).booleanValue()==Clicked) {
//						earlyMap = new HashMap<Integer, Boolean>();
//						earlyMap.put(arg2, !Clicked);
//						selectstatusList.add(earlyMap);
//					}
//					
//				}
				TextView tx = (TextView) arg1;
				tx.setBackgroundColor(Color.BLUE);
					
			}
		});
	}
	
}

class EarlyAdapter extends BaseAdapter{
	private List<String> mylist;
	private Context mContext;  
	public EarlyAdapter(Context c,List<String> orderlist) {
		// TODO Auto-generated constructor stub
		mContext=c;
		mylist = orderlist;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mylist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mylist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView tx ;
		if (convertView==null){
			tx = new TextView(mContext);
			tx.setLayoutParams(new GridView.LayoutParams(50,30)); 
			tx.setBackgroundColor(Color.WHITE);
		}else{
			tx=(TextView) convertView;
		}
		if(position==0){
			tx.setText("早班");
		}else{
			tx.setText((CharSequence)mylist.get(position));
		}
		return tx;
	}

	
}
