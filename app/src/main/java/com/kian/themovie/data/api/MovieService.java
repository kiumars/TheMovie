package com.kian.themovie.data.api;

import com.kian.themovie.data.api.model.SearchResponse;

import java.util.Map;

import retrofit.http.GET;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by kiumars on 15-04-19.
 */
public interface MovieService {
    @GET("/")
    Observable<SearchResponse> searchMovies(
            @QueryMap Map<String, String> params
    );

}
