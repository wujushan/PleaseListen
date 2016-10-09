package com.paxsz.pleaselisten.MusicLibrary.View;

import com.paxsz.pleaselisten.MusicLibrary.Mode.MusicInfo;

import java.util.List;

/**
 * Created by wujs on 2016/10/9.
 */

public interface IMusicFragment {
    void updateMusicList(List<MusicInfo> musicInfos);
}
