package com.paxsz.pleaselisten.MusicLibrary.Mode;

import android.support.v4.app.Fragment;

/**
 * Created by wujs on 2016/10/9.
 */

public class MusicLibraryTabItem {
    private String mTabName;
    private Class<? extends Fragment> mFragment;

    public MusicLibraryTabItem(String tabName, Class<? extends Fragment> fragment) {
        mTabName = tabName;
        this.mFragment = fragment;
    }

    public String getTabName() {
        return mTabName;
    }

    public void setTabName(String tabName) {
        mTabName = tabName;
    }

    public Class<? extends Fragment> getFragment() {
        return mFragment;
    }

    public void setFragment(Class<? extends Fragment> fragment) {
        this.mFragment = fragment;
    }
}
