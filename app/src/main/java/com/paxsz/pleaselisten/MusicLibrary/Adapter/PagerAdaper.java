package com.paxsz.pleaselisten.MusicLibrary.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.paxsz.pleaselisten.MusicLibrary.View.fragment.MusicLibraryFragment;

/**
 * Created by wujs on 2016/10/8.
 */

public class PagerAdaper extends FragmentPagerAdapter {

    public PagerAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MusicLibraryFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab"+position;
    }
}
