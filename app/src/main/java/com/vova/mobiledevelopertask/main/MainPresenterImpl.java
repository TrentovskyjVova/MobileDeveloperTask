package com.vova.mobiledevelopertask.main;

import com.vova.mobiledevelopertask.model.Image;

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnFinishedListener {

    private MainView mainView;
    private MainInteractor interactor;

    private int imageIndex;
    private Image currentImage;

    public MainPresenterImpl(MainView mainView, MainInteractor interactor) {
        this.mainView = mainView;
        this.interactor = interactor;
    }

    @Override
    public void setImage() {
        if (imageIndex == -1) {
            interactor.getRandomImage(this);
        } else {
            interactor.getImage(this, imageIndex);
        }
    }

    @Override
    public void saveImageTitle(CharSequence text) {
        if (!text.toString().equals(currentImage.getName())) {
            currentImage.setName(text.toString().trim());
            interactor.setImage(imageIndex, currentImage);
        }
    }

    @Override
    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    @Override
    public void setRandomImage() {
        interactor.getRandomImage(this);
    }

    @Override
    public void openImageList() {
        if (mainView != null) {
            mainView.navigateToImageList();
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    public MainView getMainView() {
        return mainView;
    }

    @Override
    public void onFinished(int index, Image image) {
        imageIndex = index;
        currentImage = image;
        if (mainView != null) {
            mainView.setImage(image);
        }
    }
}
