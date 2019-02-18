package com.sambatech.popmovies.Service;

import com.sambatech.popmovies.Model.GenreResponse;
import com.sambatech.popmovies.Model.MoviesResponse;
import com.sambatech.popmovies.Model.TrailerResponse;
import com.sambatech.popmovies.Rest.TmdbApi;
import com.sambatech.popmovies.Util.RestUtil;

import org.androidannotations.annotations.EBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@EBean
public class MovieService {

    public synchronized Observable<MoviesResponse> getMoviesOrderBy(Long page, String query, String order_by)  {
        return RestUtil.api.searchMoviesOrderBy(order_by, TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page, TmdbApi.DEFAULT_REGION,query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public synchronized Observable<MoviesResponse> getMovies(Long page, String query)  {
        return RestUtil.api.searchMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, page, TmdbApi.DEFAULT_REGION,query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public synchronized Observable<GenreResponse> getGenres(){
        return RestUtil.api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public synchronized Observable<TrailerResponse> getTrailers(long movie_id){
        return RestUtil.api.getVideos(movie_id, TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
