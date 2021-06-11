package com.example.aplugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.pluginstandard.BroadcastReceiverStandardInterface;

/**
 * @author: 王硕风
 * @date: 2021.6.10 22:47
 * @Description:
 */
public class MyReceiver extends BroadcastReceiver implements BroadcastReceiverStandardInterface {
    @Override
    public void attach(Context context) {
        Toast.makeText(context, "绑定上下文成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到广播", Toast.LENGTH_SHORT).show();
    }
}
