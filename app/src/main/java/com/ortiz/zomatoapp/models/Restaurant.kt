package com.ortiz.zomatoapp.models

import com.google.gson.annotations.Expose

class Restaurant {
    @Expose
    var name: String? = null
    @Expose
    var location: Place? = null
    @Expose
    var url: String? = null
}

class Place {
    @Expose
    var city: String? = null
}