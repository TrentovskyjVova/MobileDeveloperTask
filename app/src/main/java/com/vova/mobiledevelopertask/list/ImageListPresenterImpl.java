package com.vova.mobiledevelopertask.list;

import android.util.SparseArray;

import com.vova.mobiledevelopertask.model.Image;

public class ImageListPresenterImpl implements ImageListPresenter, ImageListInteractor.OnFinishedListener {

    private ImageListView imageListView;
    private ImageListInteractor interactor;

    public ImageListPresenterImpl(ImageListView imageListView, ImageListInteractor interactor) {
        this.imageListView = imageListView;
        this.interactor = interactor;
    }

    @Override
    public void getImages() {
        interactor.getImages(this);
    }

    @Override
    public void onDestroy() {
        imageListView = null;
    }

    public ImageListView getImageListView() {
        return imageListView;
    }

    @Override
    public void onFinished(SparseArray<Image> images) {
        if (imageListView != null) {
            imageListView.setRecyclerViewImages(images);
        }
    }
}
