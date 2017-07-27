package com.vova.mobiledevelopertask.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vova.mobiledevelopertask.MainActivity;
import com.vova.mobiledevelopertask.R;
import com.vova.mobiledevelopertask.R2;
import com.vova.mobiledevelopertask.model.Image;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class ImagesFragment extends Fragment implements ImageListView, ImageAdapter.OnClickImageListener {

    @BindView(R2.id.recyclerViewImages)
    RecyclerView imagesRV;

    private ImageAdapter adapter;

    private Unbinder unbinder;

    private ImageListPresenter presenter;

    public ImagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_images, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        presenter = new ImageListPresenterImpl(this, new ImageListInteractorImpl(getContext()));
        presenter.getImages();
    }

    @Override
    public void setRecyclerViewImages(SparseArray<Image> images) {
        adapter = new ImageAdapter(getContext(), images, this);
        imagesRV.setLayoutManager(new LinearLayoutManager(getContext()));
        imagesRV.setAdapter(adapter);
    }

    @Override
    public void navigateToMain(int imageIndex) {
        ((MainActivity) getActivity()).showMainFragment(imageIndex);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        presenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onClickImageListener(int imageIndex) {
        navigateToMain(imageIndex);
    }
}
