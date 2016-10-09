package com.paxsz.pleaselisten.MusicLibrary.Component;

import com.paxsz.pleaselisten.MusicLibrary.Module.MusicFragmentModule;
import com.paxsz.pleaselisten.MusicLibrary.Presenter.MusicFragmentPresenterCompl;
import com.paxsz.pleaselisten.MusicLibrary.View.fragment.MusicFragment;

import dagger.Component;

/**
 * Created by wujs on 2016/10/9.
 */
@Component(modules = MusicFragmentModule.class)
public interface MusicFragmentComponent {
    void inject(MusicFragment fragment);
    MusicFragmentPresenterCompl getPresenterCompl();
}
