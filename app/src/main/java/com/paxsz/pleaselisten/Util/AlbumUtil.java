package com.paxsz.pleaselisten.Util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import com.paxsz.pleaselisten.R;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wujs on 2016/10/10.
 */

public class AlbumUtil {
    private Context mContext;
    private static final Uri albumArtUri = Uri.parse("content://media/external/audio/albumart");

    public AlbumUtil(Context context) {
        this.mContext = context;
    }

    public Bitmap getAlbum(long musicId, long albumId, boolean small) {
        if (albumId < 0) {
            if (musicId >= 0) {
                Bitmap bm = getAlbumFromFile(musicId, -1);
                if (bm != null) {
                    return bm;
                }
            } else {
                return getDefaultAlbum(small);
            }
        }
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = ContentUris.withAppendedId(albumArtUri, albumId);
        if (uri != null) {
            InputStream inputStream = null;
            try {
                inputStream = resolver.openInputStream(uri);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 1;
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStream, null, options);
                if (small) {
                    options.inSampleSize = computeSampleSize(options, 40);
                } else {
                    options.inSampleSize = computeSampleSize(options, 600);
                }
                options.inJustDecodeBounds = false;
                options.inDither = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                return BitmapFactory.decodeStream(inputStream, null, options);

            } catch (FileNotFoundException e) {
//                e.printStackTrace();
                Bitmap bm = getAlbumFromFile(musicId, albumId);
                if (bm != null) {
                    if (bm.getConfig() == null) {
                        bm = bm.copy(Bitmap.Config.RGB_565, false);
                        if (bm == null) {
                            return getDefaultAlbum(small);
                        }
                    }
                } else {
                    bm = getDefaultAlbum(small);
                }
                return bm;
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private int computeSampleSize(BitmapFactory.Options options, int size) {
        int w = options.outWidth;
        int h = options.outHeight;
        int candidateW = w / size;
        int candidateH = h / size;
        int candidate = Math.max(candidateW, candidateH);
        if (candidate == 0) {
            return 1;
        }
        if (candidate > 1) {
            if ((w > size) && (w / candidate) < size) {
                candidate -= 1;
            }
        }
        if (candidate > 1) {
            if ((h > size) && (h / candidate) < size) {
                candidate -= 1;
            }
        }
        return candidate;
    }

    private Bitmap getDefaultAlbum(boolean small) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        if (small) {
            return BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher, options);
        } else
            return BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher, options);

    }

    private Bitmap getAlbumFromFile(long musicId, long albumId) {

        Bitmap bm = null;
        try {
            FileDescriptor fileDescriptor = null;
            Uri uri ;
            if (albumId < 0) {
                uri = Uri.parse("content://media/external/audio/media/" + musicId + "/albumart");
            } else {
                uri = ContentUris.withAppendedId(albumArtUri, albumId);
            }
            ParcelFileDescriptor pfd = mContext.getContentResolver().openFileDescriptor(uri, "r");
            if (pfd != null) {
                fileDescriptor = pfd.getFileDescriptor();
            }
            bm = decodeBitmap(fileDescriptor);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bm;
    }

    private Bitmap decodeBitmap(FileDescriptor fd) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fd, null, options);
        options.inSampleSize = 100;
        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bm = BitmapFactory.decodeFileDescriptor(fd, null, options);
        return bm;
    }

}
