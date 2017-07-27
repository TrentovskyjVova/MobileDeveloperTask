package com.vova.mobiledevelopertask.main;

import android.content.Context;

import com.vova.mobiledevelopertask.model.Image;
import com.vova.mobiledevelopertask.utils.ImageComponent;

import java.util.Random;

public class MainInteractorImpl implements MainInteractor {

    private ImageComponent imageComponent;
    private Random randomGenerator;

    public MainInteractorImpl(Context context) {
        this.imageComponent = new ImageComponent(context);
        this.randomGenerator = new Random();
    }

    @Override
    public void getImage(OnFinishedListener listener, int index) {
        listener.onFinished(index, imageComponent.getImage(index));
    }

    @Override
    public void getRandomImage(MainInteractor.OnFinishedListener listener) {
        int index = randomGenerator.nextInt(imageComponent.size());
        getImage(listener, index);
    }

    @Override
    public void setImage(int index, Image image) {
        imageComponent.saveImage(index, image);
    }

}
