package com.example.neven.moviezilla.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.example.neven.moviezilla.R;
import com.example.neven.moviezilla.models.Movies;

import java.util.List;

/**
 * Created by Neven on 17.1.2017..
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movies> listMovies;
    private Context context;

    public MoviesAdapter(List<Movies> listMovies, Context context) {
        this.listMovies = listMovies;
        this.context = context;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivLogo)
        ImageView ivLogo;

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvRating)
        TextView tvRating;

        @BindView(R.id.tvGenre)
        TextView tvGenre;


        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Movies movie = listMovies.get(position);
        holder.tvName.setText(movie.getTitle());
        holder.tvRating.setText(String.valueOf(movie.getRating()));
        for (String singleGenre : movie.getGenre()) {


            holder.tvGenre.setText(singleGenre);
        }

        Glide
                .with(context)
                .load(movie.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.ivLogo);


    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }
}
