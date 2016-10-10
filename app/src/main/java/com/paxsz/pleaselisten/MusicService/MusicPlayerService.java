package com.paxsz.pleaselisten.MusicService;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.paxsz.pleaselisten.Constants.PendingIntentAction;
import com.paxsz.pleaselisten.MusicService.Component.DaggerNotificationComponent;
import com.paxsz.pleaselisten.MusicService.Module.NotificationModule;
import com.paxsz.pleaselisten.MusicService.Presenter.NotifyPresenterCompl;

import javax.inject.Inject;


public class MusicPlayerService extends Service {

    private static final String TAG = MusicPlayerService.class.getSimpleName();
    private MusicBroadcastReceiver mReceiver;
    private MediaPlayer mMediaPlayer;
    private String path;
    private boolean isPuase;
    @Inject
    NotifyPresenterCompl mNotifyPresenterCompl;

    public MusicPlayerService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        initView();
        intject();
        registerReceiver(mReceiver, getIntentFilter());
    }

    private void initView() {
        mMediaPlayer = new MediaPlayer();
        mReceiver = new MusicBroadcastReceiver();
    }

    private void intject() {
        DaggerNotificationComponent.builder().notificationModule(new NotificationModule(this)).build().inject(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification = mNotifyPresenterCompl.initNotification();
        startForeground(110, notification);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);//停止前台服务--参数：是否移除之前的通知
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }

    public class MyBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

    private MyBinder myBinder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return myBinder;
    }

    /**
     * 音乐控制广播类
     */
    class MusicBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case PendingIntentAction.NF_PRE_ACTION:
                    Log.i(TAG, "onReceive: NF_PRE_ACTION");
                    break;
                case PendingIntentAction.NF_PLAY_ACTION:
                    Log.i(TAG, "onReceive: NF_PLAY_ACTION");
                    break;
                case PendingIntentAction.NF_NEXT_ACTION:
                    Log.i(TAG, "onReceive: NF_NEXT_ACTION");
                    break;
                default:
                    break;
            }
        }
    }

    static IntentFilter getIntentFilter() {
        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(PendingIntentAction.NF_PRE_ACTION);
        mIntentFilter.addAction(PendingIntentAction.NF_PLAY_ACTION);
        mIntentFilter.addAction(PendingIntentAction.NF_NEXT_ACTION);
        return mIntentFilter;
    }
}


