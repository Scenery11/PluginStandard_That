package com.example.pluginstandard_that.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pluginstandard_that.PluginManager;
import com.example.pluginstandard_that.R;
import com.example.pluginstandard_that.proxy.ProxyActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    private final static String ACTION = "com.example.receiver.action";
    public BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "我是宿主，收到插件消息", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);

        //宿主中正常注册广播
        registerReceiver(mReceiver, new IntentFilter(ACTION));
    }

    public void load(View view) {
        loadPlugin();

    }

    private void loadPlugin() {
        File filesDir = this.getDir("plugin", Context.MODE_PRIVATE);
        String name = "APlugin.apk";
        String filePath = new File(filesDir, name).getAbsolutePath();
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
        InputStream is = null;
        FileOutputStream os = null;
        try {
            Log.i(TAG, "加载插件 " + new File(Environment.getExternalStorageDirectory(), name).getAbsolutePath());
            is = new FileInputStream(new File(Environment.getExternalStorageDirectory(), name));
            os = new FileOutputStream(filePath);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            File f = new File(filePath);
            if (f.exists()) {
                Toast.makeText(this, "dex overwrite", Toast.LENGTH_SHORT).show();
            }
            //加载插件信息
            PluginManager.getInstance().loadPath(this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void click(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", PluginManager.getInstance().getPackageInfo().activities[0].name);
        startActivity(intent);
    }

    public void sendBroadcast(View view) {
        Toast.makeText(MainActivity.this, "我是宿主，插件收到请回答", Toast.LENGTH_SHORT).show();
        sendBroadcast(new Intent("action1"));
    }
}