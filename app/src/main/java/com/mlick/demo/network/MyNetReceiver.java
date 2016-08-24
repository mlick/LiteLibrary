package com.mlick.demo.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lxx on 2016/8/16 14:31
 */
public class MyNetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo activeInfo = manager.getActiveNetworkInfo();
                if (mobileInfo != null && wifiInfo != null && activeInfo != null) {
                    Toast.makeText(context, "mobile:" + mobileInfo
                            .isConnected() + "\n" + "wifi:" + wifiInfo
                            .isConnected() + "\n" + "active:" + activeInfo
                            .getTypeName(), Toast.LENGTH_SHORT).show();
                }
                if (activeInfo == null) {
                    Log.d("MyNetReceiver", "MyNetReceiver is no network!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
