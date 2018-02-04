package com.example.ricardochampa.alarmas;

/**
 * Created by ricardochampa on 04/02/2018.
 */

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;

/**
 * Created by monty on 5/01/18.
 */

public class NotificationUtils extends ContextWrapper {

    private NotificationManager mManager;
    private boolean isAndroid8;
    public static final String ANDROID_CHANNEL_ID = "com.pablomonteserin.ANDROID";
    public static final String ANDROID_CHANNEL_NAME = "ANDROID CHANNEL";

    public NotificationUtils(Context base, boolean isAndroid8) {
        super(base);
        this.isAndroid8 = isAndroid8;
        if(isAndroid8){
            createChannels();
        }
    }

    @TargetApi(26)
    public void createChannels() {
        // create android channel
        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        // Sets whether notifications posted to this channel should display notification lights
        androidChannel.enableLights(true);
        // Sets whether notification posted to this channel should vibrate.
        androidChannel.enableVibration(true);
        // Sets the notification light color for notifications posted to this channel
        androidChannel.setLightColor(Color.GREEN);
        // Sets whether notifications posted to this channel appear on the lockscreen or not
        // androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        getManager().createNotificationChannel(androidChannel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public Notification.Builder getChannelNotification(String title, String body) {
        Notification.Builder builder;

        if(isAndroid8){
            builder  = new Notification.Builder(getApplicationContext(), ANDROID_CHANNEL_ID);
        }else{
            builder = new Notification.Builder(getApplicationContext());
        }
        builder .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
        return builder;
    }
}