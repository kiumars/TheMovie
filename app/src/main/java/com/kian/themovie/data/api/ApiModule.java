package com.kian.themovie.data.api;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by kiumars on 15-04-19.
 */
@Module(
        complete = false,
        library = true
)
public class ApiModule {
    public static final String API_URL = "http://www.omdbapi.com";

    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(API_URL);
    }


    @Provides
    @Singleton
    @Named("Api")
    OkHttpClient provideApiClient(OkHttpClient client) {
        return client.clone();
    }


    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint,
                                   @Named("Api") OkHttpClient client, Gson gson) {
        return new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
//                .setConverter(new GsonConverter(gson))
                .build();
    }


    @Provides
    @Singleton
    MovieService provideGithubService(RestAdapter restAdapter) {
        return restAdapter.create(MovieService.class);
    }
}
