package com.paxsz.pleaselisten.MusicLibrary.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.paxsz.pleaselisten.MusicLibrary.Mode.MusicLibraryTabItem;
import com.paxsz.pleaselisten.MusicLibrary.View.fragment.AlbumFragment;
import com.paxsz.pleaselisten.MusicLibrary.View.fragment.MusicFragment;
import com.paxsz.pleaselisten.MusicLibrary.View.fragment.MusicianFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujs on 2016/10/8.
 */

public class MusicLibraryPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = MusicLibraryPagerAdapter.class.getSimpleName();
    private List<MusicLibraryTabItem> tabItems;

    public MusicLibraryPagerAdapter(FragmentManager fm) {
        super(fm);
        tabItems = new ArrayList<>();
        initTabItem();
    }

    private void initTabItem() {
        tabItems.clear();
        tabItems.add(new MusicLibraryTabItem("歌曲", MusicFragment.class));
        tabItems.add(new MusicLibraryTabItem("专辑", AlbumFragment.class));
        tabItems.add(new MusicLibraryTabItem("音乐人", MusicianFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        String fragmentName = tabItems.get(position).getFragment().getName();
        try {
            fragment = (Fragment) Class.forName(fragmentName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return tabItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabItems.get(position).getTabName();
    }
}
