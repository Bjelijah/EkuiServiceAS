package com.example.bean;

import com.example.action.CameraInterface.CamOpenOverCallback;

import android.content.Context;
import android.support.annotation.Nullable;

public interface ICameraInterface {

	public void setContext(@Nullable Context context);
	public void doOpenCamera(CamOpenOverCallback callback);
	

	
}
