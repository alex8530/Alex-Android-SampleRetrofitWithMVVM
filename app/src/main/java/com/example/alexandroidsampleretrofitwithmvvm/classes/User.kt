package com.example.alexandroidsampleretrofitwithmvvm.classes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("id")
      var id: Int,
    @SerializedName("name")
      var name: String



)