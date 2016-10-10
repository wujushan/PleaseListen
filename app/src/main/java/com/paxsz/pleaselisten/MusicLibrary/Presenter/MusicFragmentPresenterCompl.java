package com.paxsz.pleaselisten.MusicLibrary.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.paxsz.pleaselisten.MusicLibrary.Mode.MusicInfo;
import com.paxsz.pleaselisten.MusicLibrary.View.IMusicFragment;
import com.paxsz.pleaselisten.MusicLibrary.View.fragment.MusicFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujs on 2016/10/9.
 */

public class MusicFragmentPresenterCompl implements IMusicFragmentPresenter {
    private MusicInfo mMusicInfo;
    private Context mContext;
    private IMusicFragment mIMusicFragment;

    public MusicFragmentPresenterCompl(MusicFragment fragment) {
        mIMusicFragment =  fragment;
        this.mContext = fragment.getActivity();
    }

    public void initMusicInfos(){

    }
    public void getMusicInfos(){
        Cursor cursor = mContext.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        );
        List<MusicInfo> musicInfos = new ArrayList<>();
        for (int i = 0;i<cursor.getCount();i++){
            cursor.moveToNext();
            MusicInfo musicInfo = new MusicInfo();
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
            if (isMusic != 0){
                musicInfo.setId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                musicInfo.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                musicInfo.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                musicInfo.setAlbum(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                musicInfo.setDisplayName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
                musicInfo.setAlbumId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                musicInfo.setDuration(formatTime(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))));
                musicInfo.setSize(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)));
                musicInfo.setUrl(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                musicInfos.add(musicInfo);
            }
        }
        mIMusicFragment.updateMusicList(musicInfos);
    }
    /**
     * 格式化时间，将毫秒转换为分:秒格式
     * @param time
     * @return
     */
    public static String formatTime(long time) {
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";
        if (min.length() < 2) {
            min = "0" + time / (1000 * 60) + "";
        } else {
            min = time / (1000 * 60) + "";
        }
        if (sec.length() == 4) {
            sec = "0" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 3) {
            sec = "00" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 2) {
            sec = "000" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 1) {
            sec = "0000" + (time % (1000 * 60)) + "";
        }
        return min + ":" + sec.trim().substring(0, 2);
    }
}
