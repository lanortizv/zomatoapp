package com.ortiz.zomatoapp.rest

import com.google.gson.annotations.Expose
import com.ortiz.zomatoapp.models.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantService {
    @Headers("user-key: 924296f640073404055d106770fcb261")
    @GET("search")
    fun getRestaurants(@Query("q") q: String): Call<Restaurants>
}

class Restaurants {
    @Expose
    var restaurants: List<MiddleRestaurant>? = null
}

class MiddleRestaurant {
    @Expose
    var restaurant: Restaurant? = null
}