package com.bb.mvvmmovie.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitInstance {

        private static Retrofit retrofit;
        private static final String BASE_URL = "https://www.themoviedb.org/";

        public static Retrofit getRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
