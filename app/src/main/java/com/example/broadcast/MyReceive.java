package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.service.MainService;

public class MyReceive extends BroadcastReceiver {
	static final String BOOT_COMPLETED ="android.intent.action.BOOT_COMPLETED" ;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("123", "MyReceive on receive");
		Intent serviceIntent = new Intent(context,MainService.class);
		context.startService(serviceIntent);
	}

}
