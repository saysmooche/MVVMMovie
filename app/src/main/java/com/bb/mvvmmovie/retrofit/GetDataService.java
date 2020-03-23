package com.bb.mvvmmovie.retrofit;

import com.bb.mvvmmovie.model.RetroMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

        @GET("/3/discover/movie?api_key=fe30bbf240c2720e6435f43967fef0f7&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
        Call<List<RetroMovie>> getAllMovies();
    }

