package com.paxsz.pleaselisten.MusicLibrary.Mode;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wujs on 2016/10/9.
 */

public class MusicInfo implements Parcelable {
    private long id;
    private String title;
    private String artist;
    private String album;
    private String displayName;
    private long albumId;
    private String duration;
    private long size;
    private String url;




    public MusicInfo() {
    }

    public MusicInfo(long id, String title, String artist, String album, String displayName, long albumId, String duration, long size, String url) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.displayName = displayName;
        this.albumId = albumId;
        this.duration = duration;
        this.size = size;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.artist);
        dest.writeString(this.album);
        dest.writeString(this.displayName);
        dest.writeLong(this.albumId);
        dest.writeString(this.duration);
        dest.writeLong(this.size);
        dest.writeString(this.url);
    }

    protected MusicInfo(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.artist = in.readString();
        this.album = in.readString();
        this.displayName = in.readString();
        this.albumId = in.readLong();
        this.duration = in.readString();
        this.size = in.readLong();
        this.url = in.readString();
    }

    public static final Creator<MusicInfo> CREATOR = new Creator<MusicInfo>() {
        @Override
        public MusicInfo createFromParcel(Parcel source) {
            return new MusicInfo(source);
        }

        @Override
        public MusicInfo[] newArray(int size) {
            return new MusicInfo[size];
        }
    };
}
