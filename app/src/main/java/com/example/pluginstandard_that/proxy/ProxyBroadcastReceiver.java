package com.example.pluginstandard_that.proxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.pluginstandard.BroadcastReceiverStandardInterface;
import com.example.pluginstandard_that.PluginManager;

import java.lang.reflect.Constructor;

/**
 * @author: 王硕风
 * @date: 2021.6.10 22:36
 * @Description:
 */
public class ProxyBroadcastReceiver extends BroadcastReceiver {
    private String className;
    private BroadcastReceiverStandardInterface mReceiverStandardInterface;

    public ProxyBroadcastReceiver() {

    }

    public ProxyBroadcastReceiver(String className, Context context) {
        this.className = className;
        try {
            Class loadClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);
            Constructor loadClassConstructor = loadClass.getConstructor(new Class[]{});
            // OneService
            Object instance = loadClassConstructor.newInstance(new Object[]{});
            mReceiverStandardInterface = ((BroadcastReceiverStandardInterface) instance);
            //把ProxyActivity通过attach传过去
            mReceiverStandardInterface.attach(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (mReceiverStandardInterface != null) {
            mReceiverStandardInterface.onReceive(context, intent);
        }
    }
}
