package com.vova.mobiledevelopertask.utils;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.vova.mobiledevelopertask.model.Image;

import java.io.File;

public class ImageComponent {

    private static final int COUNT = 20;

    private SparseArray<Image> images;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public ImageComponent(Context context) {
        this.images = new SparseArray<>();
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.gson = new Gson();

        retrieveImages();
        if (images.size() == 0) {
            getImages(context);
            saveImages();
        }
    }

    public Image getImage(int index) {
        return images.get(index);
    }

    public void saveImage(int index, Image image) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        String json = gson.toJson(image);
        prefsEditor.putString(String.valueOf(index), json);
        prefsEditor.apply();
    }

    public int size() {
        return images.size();
    }

    public SparseArray<Image> getImages() {
        return images;
    }

    private void saveImages() {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        for (int i = 0; i < COUNT; i++) {
            String json = gson.toJson(images.get(i));
            prefsEditor.putString(String.valueOf(i), json);
        }
        prefsEditor.apply();
    }

    private void retrieveImages() {
        images.clear();
        for (int i = 0; i < COUNT; i++) {
            String json = sharedPreferences.getString(String.valueOf(i), "");
            if (!json.isEmpty()) {
                Image image = gson.fromJson(json, Image.class);
                if (image != null) {
                    images.put(i, image);
                }
            }
        }
    }

    private void getImages(Context context) {

        //Check have permission for this
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        Cursor mCursor = null;

        String[] largeFileProjection = {MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.ORIENTATION};
        String largeFileSort = MediaStore.Images.ImageColumns._ID + " DESC";

        try {
            mCursor = context.getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    largeFileProjection,
                    null,
                    null,
                    largeFileSort);

            if (mCursor != null) {
                int index = 0;
                while (mCursor.moveToNext() && index < COUNT) {
                    String imagePath = mCursor.getString(mCursor
                            .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                    Image image = new Image();
                    image.setName("");
                    image.setPath(Uri.fromFile(new File(imagePath)).getPath());
                    images.append(index, image);
                    index++;
                }
            }

        } finally {
            if (mCursor != null && !mCursor.isClosed()) {
                mCursor.close();
            }
        }
    }
}
