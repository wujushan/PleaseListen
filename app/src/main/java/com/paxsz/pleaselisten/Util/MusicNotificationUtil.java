package com.paxsz.pleaselisten.Util;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * Created by wujs on 2016/10/9.
 */

public class MusicNotificationUtil {
    private NotificationCompat.Builder mBuilder;
    private Context mContext;

    public MusicNotificationUtil(Context context) {
        mContext = context;
        mBuilder = new NotificationCompat.Builder(context);
    }
    public void triggerNotification(){

    }

}
