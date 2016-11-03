package com.example.bean;

import com.example.action.CameraInterface;


public class CameraInterfaceFactory {

	private static boolean isNewApi(){
		return android.os.Build.VERSION.SDK_INT>20; 
	}
	
	
	public static ICameraInterface getCameraInterface(){
		if (isNewApi()) {
			return null;//FIXME
		}else{
			return CameraInterface.getInstance();
		}
	}
}
