package com.vova.mobiledevelopertask.list;

import android.content.Context;

import com.vova.mobiledevelopertask.utils.ImageComponent;

public class ImageListInteractorImpl implements ImageListInteractor {

    private ImageComponent imageComponent;

    public ImageListInteractorImpl(Context context) {
        this.imageComponent = new ImageComponent(context);
    }

    @Override
    public void getImages(OnFinishedListener listener) {
        listener.onFinished(imageComponent.getImages());
    }

}
