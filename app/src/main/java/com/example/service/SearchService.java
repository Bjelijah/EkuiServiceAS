package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bean.IConst;
import com.example.utils.JsonUtil;
import com.example.utils.PhoneConfig;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by howell on 2016/11/4.
 */

public class SearchService extends Service implements IConst{


    private MulticastSocket ds;
    String multicastHost="224.0.0.1";
    InetAddress receiveAddress;
    MyReceiveThread mThread;
    String mClientIP;

    private DatagramSocket mSocket = null;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("123","search service onStartCommand");

        try {
            ds = new MulticastSocket(SEARCH_SERVICE_RECEIVE_PORT);
            receiveAddress=InetAddress.getByName(multicastHost);
            ds.joinGroup(receiveAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mThread = new MyReceiveThread();
        mThread.start();


        return super.onStartCommand(intent, flags, startId);
    }

    private void sendBack(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                String sIP = PhoneConfig.getPhoneIP(SearchService.this);
                String sIPjson = JsonUtil.createIPJstr(sIP);
                byte [] data = sIPjson.getBytes();
                InetAddress address = null;
                try {
                    mSocket = new DatagramSocket();
                    address = InetAddress.getByName(mClientIP);
                    DatagramPacket pack = new DatagramPacket(data, data.length,address,SEARCH_SEARCH_SEND_PORT);
                    mSocket.send(pack);
                    mSocket.disconnect();
                    mSocket.close();
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }





    class MyReceiveThread extends Thread{
        @Override
        public void run() {
            Log.i("123","MyReceiveThread");
            super.run();

            byte buf[] = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf, 1024);
            while (true){
                try {
                    ds.receive(dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String str = new String(buf, 0, dp.getLength());

                mClientIP = JsonUtil.getSearchIP(str);
                Log.i("123","mClientIP="+mClientIP);
                sendBack();
            }

        }
    }

}
