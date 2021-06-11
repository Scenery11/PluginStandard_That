package com.example.pluginstandard;

import android.content.Context;
import android.content.Intent;

/**
 * @author: 王硕风
 * @date: 2021.6.10 22:35
 * @Description:
 */
public interface BroadcastReceiverStandardInterface {

    void attach(Context context);

    void onReceive(Context context, Intent intent);
}
