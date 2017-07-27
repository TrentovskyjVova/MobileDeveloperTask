package com.vova.mobiledevelopertask.main;

public interface MainPresenter {

    void setImageIndex(int index);

    void setRandomImage();

    void setImage();

    void saveImageTitle(CharSequence text);

    void openImageList();

    void onDestroy();

}
