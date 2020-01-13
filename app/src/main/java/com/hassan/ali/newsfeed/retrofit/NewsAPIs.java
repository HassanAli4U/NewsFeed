package com.hassan.ali.newsfeed.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPIs {
    /*
    Get request to fetch city weather.Takes in two parameter-city name and API key.
    */




    @GET("v2/top-headlines")
    Call< ArticlesResponse > getArticlesByCountry(@Query("country") String country, @Query("category") String category,@Query("apiKey") String apiKey);


    @GET("/v2/top-headlines")
    Call< ArticlesResponse > getArticlesNoCategory(@Query("country") String country,@Query("apiKey") String apiKey);

}
