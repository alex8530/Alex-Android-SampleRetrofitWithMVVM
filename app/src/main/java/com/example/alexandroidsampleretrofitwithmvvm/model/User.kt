package com.example.alexandroidsampleretrofitwithmvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("id")
    private var id: Int,
    @SerializedName("name")
    private var name: String



)