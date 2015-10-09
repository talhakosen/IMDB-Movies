package com.fleetmatrics.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fleetmatrics.R;
import com.fleetmatrics.core.Constants;
import com.fleetmatrics.modal.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private List<Movie> items;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(List<Movie> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(holder.image.getContext()).load(String.format(Constants.MOVIES_IMAGE_LARGE, items.get(position).getBackdrop_path())).into(holder.image);
        holder.itemView.setTag(items.get(position));
        holder.name.setText(items.get(position).getOriginal_title());
        holder.popularity.setText("a " + String.valueOf(items.get(position).getPopularity()));
        holder.year.setText("a " + items.get(position).getRelease_date());
        holder.category.setText("a " + items.get(position).getGenresAsString());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (Movie) v.getTag());
                }
            }, 200);
        }
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView category;
        public TextView popularity;
        public TextView year;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            popularity = (TextView) itemView.findViewById(R.id.popularity);
            category = (TextView) itemView.findViewById(R.id.category);
            year = (TextView) itemView.findViewById(R.id.year);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Movie movie);
    }
}
