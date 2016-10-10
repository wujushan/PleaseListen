package com.paxsz.pleaselisten.MusicService.Presenter;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.paxsz.pleaselisten.Constants.PendingIntentAction;
import com.paxsz.pleaselisten.MainActivity;
import com.paxsz.pleaselisten.R;
import com.paxsz.pleaselisten.Util.MusicNotificationUtil;

/**
 * Created by wujs on 2016/10/10.
 */

public class NotifyPresenterCompl implements INotifyPresenter {
    private Context mContext;

    public NotifyPresenterCompl(Context context) {
        mContext = context;
    }

    @Override
    public Notification initNotification() {
        PendingIntent remotePIntent = PendingIntent.getActivity(mContext, 0, new Intent(mContext, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent preIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(PendingIntentAction.NF_PRE_ACTION), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent playIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(PendingIntentAction.NF_PLAY_ACTION), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent nextIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(PendingIntentAction.NF_NEXT_ACTION), PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews contentView = new RemoteViews(mContext.getPackageName(), R.layout.music_notification_content_view);
        contentView.setOnClickPendingIntent(R.id.nf_pre_bt, preIntent);
        contentView.setOnClickPendingIntent(R.id.nf_play_bt, playIntent);
        contentView.setOnClickPendingIntent(R.id.nf_next_bt, nextIntent);

        RemoteViews bigContentView = new RemoteViews(mContext.getPackageName(), R.layout.music_notification_bigcontent_view);
        bigContentView.setOnClickPendingIntent(R.id.nf_big_pre_bt, preIntent);
        bigContentView.setOnClickPendingIntent(R.id.nf_big_play_bt, playIntent);
        bigContentView.setOnClickPendingIntent(R.id.nf_big_next_bt, nextIntent);
        MusicNotificationUtil notificationUtil = new MusicNotificationUtil(mContext);
        Notification notification = notificationUtil.initNotification("", "", "", R.mipmap.ic_launcher, remotePIntent, contentView, bigContentView);
        return notification;
    }
}
