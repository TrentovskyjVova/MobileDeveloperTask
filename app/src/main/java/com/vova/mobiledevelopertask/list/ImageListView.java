package com.vova.mobiledevelopertask.list;

import android.util.SparseArray;

import com.vova.mobiledevelopertask.model.Image;

public interface ImageListView {

    void setRecyclerViewImages(SparseArray<Image> images);

    void navigateToMain(int imageIndex);
}
