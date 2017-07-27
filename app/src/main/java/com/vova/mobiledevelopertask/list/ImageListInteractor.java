package com.vova.mobiledevelopertask.list;

import android.util.SparseArray;

import com.vova.mobiledevelopertask.model.Image;

public interface ImageListInteractor {

    interface OnFinishedListener {
        void onFinished(SparseArray<Image> images);
    }

    void getImages(ImageListInteractor.OnFinishedListener listener);
}
