package com.howell.ekuiservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("123", "activity on create");
        foo();
//        finish();
    }

    @Override
    protected void onDestroy() {
        Log.i("123", "activity destroy");
        foo();
        super.onDestroy();
    }

    private void foo(){
        Intent intent = new Intent("RESTART_SERVER");
        sendBroadcast(intent);

    }
}
