package com.example.neven.moviezilla.network;

import com.example.neven.moviezilla.models.Movies;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * Created by Neven on 17.1.2017..
 */
public interface RestAPI {

    @GET("movies.json")
    Call<List<Movies>> getMovies();


}
