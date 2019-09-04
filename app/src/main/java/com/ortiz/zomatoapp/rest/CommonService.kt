package com.ortiz.zomatoapp.rest

import com.google.gson.annotations.Expose
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CommonService {
    @Headers("user-key: 924296f640073404055d106770fcb261")
    @GET("categories")
    fun getCategories(): Call<Categories>
}

class Categories {
    @Expose
    var categories: List<middleCategory>? = null
}

class middleCategory {
    var categories: Category? = null
}

class Category {
    //@Expose
    var id: Int? = null
    //@Expose
    var name: String? = null

}
