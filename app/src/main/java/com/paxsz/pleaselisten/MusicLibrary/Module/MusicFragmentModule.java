package com.paxsz.pleaselisten.MusicLibrary.Module;

import com.paxsz.pleaselisten.MusicLibrary.Presenter.MusicFragmentPresenterCompl;
import com.paxsz.pleaselisten.MusicLibrary.View.fragment.MusicFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wujs on 2016/10/9.
 */
@Module
public class MusicFragmentModule {
    private MusicFragment mMusicFragment;

    public MusicFragmentModule(MusicFragment fragment) {
        this.mMusicFragment = fragment;
    }

    @Provides
    public MusicFragment provideFragment() {
        return mMusicFragment;
    }

    @Provides
    public MusicFragmentPresenterCompl providePresenterCompl(MusicFragment fragment) {
        return new MusicFragmentPresenterCompl(fragment);
    }
}
