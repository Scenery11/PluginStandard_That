package com.example.pluginstandard;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author: 王硕风
 * @date: 2021.6.8 23:59
 * @Description:
 */
public interface ActivityStandardInterface {

    public void attach(AppCompatActivity proxyActivity);

    public void onCreate(Bundle savedInstanceState);

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void onSaveInstanceState(Bundle outState);

    public boolean onTouchEvent(MotionEvent event);

    public void onBackPressed();
}
