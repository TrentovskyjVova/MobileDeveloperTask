package com.vova.mobiledevelopertask.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vova.mobiledevelopertask.model.Image;
import com.vova.mobiledevelopertask.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private OnClickImageListener listener;
    private SparseArray<Image> images;
    private Context context;

    public ImageAdapter(Context context, SparseArray<Image> images, OnClickImageListener listener) {
        this.context = context;
        this.images = images;
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtNumber)
        TextView txtNumber;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.listItem)
        void onClick() {
            listener.onClickImageListener(getAdapterPosition());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Image image = images.get(position);

        holder.txtTitle.setText(image.getName());
        holder.txtNumber.setText(context.getString(R.string.image_number, position));

        Glide.with(context).load(image.getPath()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public interface OnClickImageListener {
        void onClickImageListener(int imageIndex);
    }
}
