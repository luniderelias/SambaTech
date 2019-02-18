package com.sambatech.popmovies.View.home.Trailer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sambatech.popmovies.R;
import com.sambatech.popmovies.Model.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private List<Trailer> trailers = new ArrayList<>();
    private final OnItemClickListener listener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Trailer item);
    }


    public void setTrailers(List<Trailer> trailers){
        this.trailers = trailers;
    }

    public TrailerAdapter(Context context, List<Trailer> trailers, OnItemClickListener listener) {
        this.listener = listener;
        this.trailers.addAll(trailers);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return trailers != null ? trailers.size():0;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.trailerName.setText(trailers.get(position).name);
        holder.trailerImageView.setOnClickListener(v -> listener.onItemClick(trailers.get(position)));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView trailerImageView;
        private final TextView trailerName;

        public ViewHolder(View itemView) {
            super(itemView);
            trailerImageView = itemView.findViewById(R.id.trailerImageView);
            trailerName = itemView.findViewById(R.id.trailerName);
        }
    }
}
