package com.sambatech.popmovies.View.Movie;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.sambatech.popmovies.Model.Genre;
import com.sambatech.popmovies.R;
import com.sambatech.popmovies.Model.Cache;
import com.sambatech.popmovies.Model.Movie;
import com.sambatech.popmovies.Model.Trailer;
import com.sambatech.popmovies.Service.MovieService;
import com.sambatech.popmovies.Util.MovieImageUrlBuilder;
import com.sambatech.popmovies.View.home.HomeActivity_;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_movie)
public class MovieFragment extends Fragment {

    @ViewById(R.id.posterImageView)
    ImageView posterImageView;

    @ViewById(R.id.backdropImageView)
    ImageView backdropImageView;

    @ViewById(R.id.titleTextView)
    TextView titleTextView;

    @ViewById(R.id.descriptionTextView)
    TextView descriptionTextView;

    @ViewById(R.id.genresTextView)
    TextView genresTextView;

    @ViewById(R.id.releaseDateTextView)
    TextView releaseDateTextView;

    @ViewById(R.id.trailersRecyclerView)
    RecyclerView trailersRecyclerView;

    @Bean
    MovieService movieService;

    Movie movie;
    com.sambatech.popmovies.View.home.Trailer.TrailerAdapter trailerAdapter;
    private final MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();

    @AfterViews
    void afterViews() {
        movie = ((HomeActivity_) getActivity()).movie;
        loadImages();
        configureRecyclerView();
        setTrailersAdapter();
        loadTrailers();
        setTexts();
    }

    private void loadImages() {
        Picasso.with(getActivity())
                .load(movieImageUrlBuilder.buildPosterUrl(movie.posterPath))
                .placeholder(R.drawable.movie_placeholder)
                .into(posterImageView);
        Picasso.with(getActivity())
                .load(movieImageUrlBuilder.buildBackdropUrl(movie.backdropPath))
                .placeholder(R.drawable.movie_placeholder)
                .into(backdropImageView);
    }

    @Background
    void loadTrailers(){
        Log.d("ID DO FILME", "loadTrailers: "+movie.id);
        movieService.getTrailers(movie.id)
                .onErrorResumeNext(response -> {
        }).subscribe(response -> {
            Cache.setTrailers(response.results);
            updateList();
        }).isDisposed();

    }

    private void updateList() {
        trailerAdapter.setTrailers(Cache.getTrailers());
        trailerAdapter.notifyDataSetChanged();
    }

    private void setTexts() {
        titleTextView.setText(movie.title);
        genresTextView.setText(getGendersText());
        releaseDateTextView.setText(movie.releaseDate.split("-")[0]);
        descriptionTextView.setText(movie.overview);
    }

    private StringBuilder getGendersText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Genre genre : Cache.getGenres()) {
            if (movie.genreIds.contains(genre.id)) {
                stringBuilder.append(genre.name + "  ");
            }
        }
        return stringBuilder;
    }

    private void configureRecyclerView() {
        trailersRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(
                getActivity(), 1);
        trailersRecyclerView.setLayoutManager(layoutManager);

    }


    void setTrailersAdapter() {
        trailerAdapter = new com.sambatech.popmovies.View.home.Trailer.TrailerAdapter(getActivity(), Cache.getTrailers(),
                item ->
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://www.youtube.com/watch?v="+item.key))));
        trailersRecyclerView.setAdapter(trailerAdapter);
    }
}
