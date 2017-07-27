package com.vova.mobiledevelopertask.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vova.mobiledevelopertask.MainActivity;
import com.vova.mobiledevelopertask.R;
import com.vova.mobiledevelopertask.R2;
import com.vova.mobiledevelopertask.model.Image;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements MainView {

    private static final String ARG_IMAGE_INDEX = "ARG_IMAGE_INDEX";

    @BindView(R2.id.imageTitle)
    EditText title;
    @BindView(R2.id.imageView)
    ImageView imageView;
    private Unbinder unbinder;

    private MainPresenter presenter;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setIndexArgument(int index) {
        getArguments().putInt(ARG_IMAGE_INDEX, index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        presenter = new MainPresenterImpl(this, new MainInteractorImpl(getContext()));
        if(getArguments() != null) {
            int index = getArguments().getInt(ARG_IMAGE_INDEX, -1);
            presenter.setImageIndex(index);
        }
        presenter.setImage();

    }

    @OnClick(R2.id.buttonRandom)
    void onRandomButtonClicked() {
        presenter.setRandomImage();
    }

    @OnClick(R2.id.buttonSelect)
    void onSelectButtonClicked() {
        presenter.openImageList();
    }

    @OnTextChanged(value = R2.id.imageTitle, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onTextChangedListener(CharSequence text) {
        presenter.saveImageTitle(text);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        presenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void setImage(Image image) {
        if (image == null) return;

        title.setText(image.getName());

        Glide.with(getContext()).load(image.getPath()).into(imageView);
    }

    @Override
    public void navigateToImageList() {
        ((MainActivity) getActivity()).showImagesFragment();
    }


}
