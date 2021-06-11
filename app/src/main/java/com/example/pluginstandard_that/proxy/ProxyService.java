package com.example.pluginstandard_that.proxy;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.pluginstandard.ServiceStandardInterface;
import com.example.pluginstandard_that.PluginManager;

import java.lang.reflect.Constructor;

/**
 * @author: 王硕风
 * @date: 2021.6.10 22:05
 * @Description:
 */
public class ProxyService extends Service {
    public String serviceName;

    public ServiceStandardInterface mStandardInterface;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        init(intent);
        return null;
    }

    private void init(Intent intent) {
        //serviceNmae:com.example.aplugin.OneService
        serviceName = intent.getStringExtra("serviceName");
        try {
            Class loadClass = PluginManager.getInstance().getDexClassLoader().loadClass(serviceName);

            Constructor<?> localConstructor = loadClass.getConstructor(new Class[]{});
            Object instance = localConstructor.newInstance(new Object[]{});
//            OneService
            mStandardInterface = (ServiceStandardInterface) instance;
            mStandardInterface.attach(this);
            Bundle bundle = new Bundle();
            bundle.putInt("form", 1);
            mStandardInterface.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mStandardInterface == null) {
            init(intent);
        }

        return mStandardInterface.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mStandardInterface.onDestroy();
        super.onDestroy();

    }

    @Override
    public boolean onUnbind(Intent intent) {
        mStandardInterface.onUnbind(intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        mStandardInterface.onRebind(intent);
        super.onRebind(intent);
    }
}
