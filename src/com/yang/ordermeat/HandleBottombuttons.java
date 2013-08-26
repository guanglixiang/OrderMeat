package com.yang.ordermeat;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/*
 * this class used to handle bottom buttons
 * 
 */
public  class HandleBottombuttons{
	private int buttonid;
	private Context currcontext;
	public HandleBottombuttons (Context context,View v) {
		this.buttonid=v.getId();
		this.currcontext=context;
	}
	public void handButton(View view){
		switch (buttonid) {
		case R.id.sign_in:
			Intent intent = new Intent();
			intent.setClass(this.currcontext, SignActivity.class);  
			this.currcontext.startActivity(intent); 
			break;
		case R.id.connect_status:
			break;
		case R.id.start:
			break;
		case R.id.warnning:
			break;
		case R.id.check:
			break;
		case R.id.storaged:
			break;
		default:
			break;
		}
		
	}


}