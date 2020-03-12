package com.example.alexandroidsampleretrofitwithmvvm.classes

import com.google.gson.annotations.SerializedName

class Store(

    @SerializedName("id")
    var id: Int,
    @SerializedName("place_id")
    var place_id: String,
    @SerializedName("name")
    var name: String

)