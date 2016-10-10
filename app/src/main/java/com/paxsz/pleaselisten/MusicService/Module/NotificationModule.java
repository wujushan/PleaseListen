package com.paxsz.pleaselisten.MusicService.Module;

import com.paxsz.pleaselisten.MusicService.MusicPlayerService;
import com.paxsz.pleaselisten.MusicService.Presenter.NotifyPresenterCompl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wujs on 2016/10/10.
 */
@Module
public class NotificationModule {
    private MusicPlayerService mService;

    public NotificationModule(MusicPlayerService service) {
        mService = service;
    }

    @Provides
    public MusicPlayerService provideService() {
        return mService;
    }

    @Provides
    public NotifyPresenterCompl providePresenterCompl(MusicPlayerService service) {
        return new NotifyPresenterCompl(service);
    }
}
