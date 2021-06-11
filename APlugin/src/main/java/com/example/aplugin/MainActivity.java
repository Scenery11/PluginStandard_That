package com.example.aplugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转插件Activity
                Intent intent = new Intent(that, SecondActivity.class);
                startActivity(intent);

                //开启插件服务
                Intent serviceIntent = new Intent(that, OneService.class);
                startService(serviceIntent);

                //注册插件广播
//                IntentFilter filter = new IntentFilter();
//                filter.addAction("com.example.aplugin.MainActivity");
//                registerReceiver(new MyReceiver(), filter);
            }
        });

        findViewById(R.id.sendBroadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction("com.example.aplugin.MainActivity");
//                sendBroadcast(intent);
            }
        });
    }
}