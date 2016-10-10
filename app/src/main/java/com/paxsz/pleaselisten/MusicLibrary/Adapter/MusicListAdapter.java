package com.paxsz.pleaselisten.MusicLibrary.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.paxsz.pleaselisten.MusicApplication;
import com.paxsz.pleaselisten.MusicLibrary.Mode.MusicInfo;
import com.paxsz.pleaselisten.R;
import com.paxsz.pleaselisten.Util.AlbumUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wujs on 2016/10/8.
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.Holder> {


    private List<MusicInfo> mMusicInfos;
    private Context mContext;
    private AlbumUtil mAlbumUtil;
    private  String albumUri = "content://media/external/audio/albumart";
    public MusicListAdapter(Context context) {
        mMusicInfos = new ArrayList<>();
        mAlbumUtil = new AlbumUtil(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.music_fragment_recyclerview_item_layout;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new Holder(view);
    }

    public void setData(List<MusicInfo> list) {
        this.mMusicInfos = list;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        if (mMusicInfos.size() > 0) {
            MusicInfo musicInfo = mMusicInfos.get(position);
            if (musicInfo.getAlbumId() <0){
                String uri = "content://media/external/audio/media/" + musicInfo.getId() + "/albumart";
                ImageLoader.getInstance().displayImage(uri,holder.album, MusicApplication.mOptions);

            }else {
                String uri = albumUri + musicInfo.getAlbumId();
                ImageLoader.getInstance().displayImage(uri,holder.album,MusicApplication.mOptions);
            }
//            holder.album.setImageBitmap(mAlbumUtil.getAlbum(musicInfo.getId(),musicInfo.getAlbumId(),true));
            holder.title.setText(musicInfo.getTitle());
            holder.artist.setText(musicInfo.getArtist());
            holder.duration.setText(String.valueOf(musicInfo.getDuration()));
        }
    }

    @Override
    public int getItemCount() {
        return mMusicInfos.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView album;
        TextView title;
        TextView artist;
        TextView duration;
        ImageButton more;

        public Holder(View itemView) {
            super(itemView);
            album = (ImageView) itemView.findViewById(R.id.item_music_album);
            title = (TextView) itemView.findViewById(R.id.item_music_title);
            artist = (TextView) itemView.findViewById(R.id.item_music_artist);
            duration = (TextView) itemView.findViewById(R.id.item_music_duration);
            more = (ImageButton) itemView.findViewById(R.id.item_music_more);
        }
    }
}
