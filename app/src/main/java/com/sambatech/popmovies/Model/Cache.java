package com.sambatech.popmovies.Model;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    private static List<Movie> movies = new ArrayList<>();
    private static List<Genre> genres = new ArrayList<>();
    private static List<Trailer> trailers = new ArrayList<>();

    public static List<Movie> getMovies() {
        return movies;
    }

    public static void setMovies(List<Movie> movies) {
        Cache.movies.clear();
        Cache.movies.addAll(movies);
    }

    public static List<Trailer> getTrailers() {
        return trailers;
    }

    public static void setTrailers(List<Trailer> trailers) {
        Cache.trailers.clear();
        Cache.trailers.addAll(trailers);
    }

    public static List<Genre> getGenres() {
        return genres;
    }

    public static void setGenres(List<Genre> genres) {
        Cache.genres.clear();
        Cache.genres.addAll(genres);
    }
}
