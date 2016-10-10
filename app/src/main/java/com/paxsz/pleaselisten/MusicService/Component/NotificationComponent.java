package com.paxsz.pleaselisten.MusicService.Component;

import com.paxsz.pleaselisten.MusicService.Module.NotificationModule;
import com.paxsz.pleaselisten.MusicService.MusicPlayerService;

import dagger.Component;

/**
 * Created by wujs on 2016/10/10.
 */
@Component(modules = NotificationModule.class)
public interface NotificationComponent {
    void inject(MusicPlayerService service);
}
