package com.kodingwithkyle.raceresults.services;

import com.google.gson.Gson;
import com.kodingwithkyle.raceresults.models.Results;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ResultsService {

    @GET("/mobile/runners.json")
    Call<Results> fetchResults();

    class ServiceCreator {
        public static ResultsService newService() {
            OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
            httpBuilder.connectTimeout(45, TimeUnit.SECONDS);
            httpBuilder.readTimeout(45, TimeUnit.SECONDS);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://849fairmount.com")
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .client(httpBuilder.build())
                    .build();
            return retrofit.create(ResultsService.class);
        }
    }
}
