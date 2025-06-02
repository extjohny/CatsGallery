package com.example.catsgallery.data;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("cat?json=true")
    Single<Cat> getCat();
}
