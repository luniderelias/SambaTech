package com.sambatech.popmovies.Rest;

import com.sambatech.popmovies.Model.GenreResponse;
import com.sambatech.popmovies.Model.Movie;
import com.sambatech.popmovies.Model.MoviesResponse;
import com.sambatech.popmovies.Model.TrailerResponse;
import com.sambatech.popmovies.Model.UpcomingMoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbApi {

    String URL = "https://api.themoviedb.org/3/";
    String API_KEY = "chave_aqui";
    String DEFAULT_LANGUAGE = "pt-BR";
    String DEFAULT_REGION = "BR";

    @GET("movie/upcoming")
    Observable<UpcomingMoviesResponse> upcomingMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") Long page,
            @Query("region") String region
    );

    @GET("search/movie")
    Observable<MoviesResponse> searchMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") Long page,
            @Query("region") String region,
            @Query("query") String query
    );

    @GET("genre/movie/list")
    Observable<GenreResponse> genres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{order_by}")
    Observable<MoviesResponse> searchMoviesOrderBy(
            @Path("order_by") String order_by,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") Long page,
            @Query("region") String region,
            @Query("query") String query
    );

    @GET("movie/{id}")
    Observable<Movie> movie(
            @Path("id") Long id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/{movie_id}/videos")
    Observable<TrailerResponse> getVideos(
            @Path("movie_id") Long movie_id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
