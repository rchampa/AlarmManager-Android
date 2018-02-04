package com.example.ricardochampa.alarmas;

/**
 * Created by ricardochampa on 04/02/2018.
 */


import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sonu on 09/04/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("alarma: ","MEHH");
        Toast.makeText(context, "ALARM!! ALARM!!", Toast.LENGTH_SHORT).show();

        NotificationUtils notificationUtils;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // only for Oreo(8) and newer versions
            notificationUtils = new NotificationUtils(context, true);
        } else {
            notificationUtils = new NotificationUtils(context, false);
        }


        Notification.Builder nb = notificationUtils.getChannelNotification("Titulo", "Mensaje");
        notificationUtils.getManager().notify(101, nb.build());


    }


}
