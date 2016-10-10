package com.paxsz.pleaselisten.Util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

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

    public void sample(String ticker, String title, String content, int smallIcon, PendingIntent intent) {
        //状态栏文字
        mBuilder.setTicker(ticker);
        //通知栏标题
        mBuilder.setContentTitle(title);
        //通知栏内容
        mBuilder.setContentText(content);
        //触发的intent
        mBuilder.setContentIntent(intent);
        //这边设置颜色，可以给5.0及以上版本smallIcon设置背景色
        mBuilder.setColor(Color.RED);
        //小图标
        mBuilder.setSmallIcon(smallIcon);
        //大图标(这边同时设置了小图标跟大图标，在5.0及以上版本通知栏里面的样式会有所不同)
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), smallIcon));
        //设置该条通知时间
        mBuilder.setWhen(System.currentTimeMillis());
        //设置为true，点击该条通知会自动删除，
//        mBuilder.setAutoCancel(true);
        //设置优先级，级别高的排在前面
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
    }

    public Notification initNotification(String ticker, String title,
                                 String content, int smallIcon,
                                 PendingIntent intent, RemoteViews contentView,
                                 RemoteViews bigContentView) {
        sample(ticker, title, content, smallIcon, intent);
        //在api大于等于16的情况下，如果视图超过一定范围，可以转变成bigContentView
        mBuilder.setCustomContentView(contentView);
        mBuilder.setCustomBigContentView(bigContentView);
//        Notification notification = mBuilder.build();
        return mBuilder.build();
    }
}
