package com.example.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.action.TestSocket;
import com.example.bean.AvcEncoder;
import com.howell.ekuiservice.R;

public class MainActivity extends Activity{

	TestSocket mgr;
	Context mContext;
//	DoFrameThread doFrameThread = null;
	AvcEncoder mAvcEncoder = null;
	byte [] mH264 = null;
	boolean isPreviewing = false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("123", "activity on create");


//
//				Intent intent = new Intent("RESTART_SERVER");
//				sendBroadcast(intent);
		finish();
		
//		mgr = TestSocket.getInstance();
//		mgr.init(SERVICE_PORT, CLIENT_PORT);
//		mgr.register(this);

//		ProtocolServerAction.getInstance().setContext(this);
//		ProtocolServerAction.getInstance().init();
		
		

	}

	@Override
	protected void onDestroy() {
		Log.i("123", "activity destroy");
		Intent intent = new Intent("RESTART_SERVER");
		sendBroadcast(intent);
		super.onDestroy();
	}



	
}
