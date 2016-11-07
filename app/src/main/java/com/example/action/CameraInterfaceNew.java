package com.example.action;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.Size;

import com.example.action.CameraInterface.CamOpenOverCallback;
import com.example.bean.ICameraInterface;



@SuppressLint("NewApi")
public class CameraInterfaceNew implements ICameraInterface {
	private static final String TAG = "CameraInterfaceNew";
	private static CameraInterfaceNew mInstance = null;
	public static CameraInterfaceNew getInstance(){
		if (mInstance==null) {
			mInstance = new CameraInterfaceNew();
		}
		return mInstance;
	}
	private CameraInterfaceNew(){}
	
	private Handler mHandler;
	private CameraManager mCamMgr;
	private CameraDevice mDevice;
	private Size mPreviewSize;
	private CamOpenOverCallback mCamOpenCallback;
	private Context mContext;
	private HandlerThread mHandlerThread;
	
	private CameraDevice.StateCallback mCameraStateCallback = new CameraDevice.StateCallback(){

		@Override
		public void onOpened(CameraDevice camera) {
			// TODO Auto-generated method stub
			Log.i("123", "camera on open");
			mCamOpenCallback.cameraHasOpened();
			
			doStartPreview(camera);
			
			
		}

		@Override
		public void onDisconnected(CameraDevice camera) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onError(CameraDevice camera, int error) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	@SuppressWarnings("MissingPermission")
	@Override
	public void doOpenCamera(CamOpenOverCallback callback) {
		// TODO Auto-generated method stub
		mCamOpenCallback = callback;
		mHandlerThread = new HandlerThread("CAMERA2");
		mHandlerThread.start();
		mHandler = new Handler(mHandlerThread.getLooper());
		
		/***********************/
		mCamMgr = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);
		try {
			CameraCharacteristics characteristics = mCamMgr.getCameraCharacteristics("1");
			StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
			mPreviewSize = map.getOutputSizes(SurfaceTexture.class)[0];
			mCamMgr.openCamera("1", mCameraStateCallback, mHandler);
		} catch (CameraAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
	public void setContext(Context context) {
		// TODO Auto-generated method stub
		if(context == null){
			throw new NullPointerException("new camera interface context should not be null!!!");
		}
		mContext = context;
	}
	

	
	
	
	private void doStartPreview(CameraDevice camera){
		
	}

	
	
	
	
	
}
