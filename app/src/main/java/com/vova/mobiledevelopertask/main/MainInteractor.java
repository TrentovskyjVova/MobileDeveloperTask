package com.vova.mobiledevelopertask.main;

import com.vova.mobiledevelopertask.model.Image;

public interface MainInteractor {

    interface OnFinishedListener {
        void onFinished(int index, Image image);
    }

    void getImage(MainInteractor.OnFinishedListener listener, int index);

    void getRandomImage(MainInteractor.OnFinishedListener listener);

    void setImage(int index, Image image);
}
