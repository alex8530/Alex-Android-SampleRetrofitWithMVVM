package com.example.alexandroidsampleretrofitwithmvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataUser (

    @SerializedName("user")
      var user: User,

    @SerializedName("api_token")
      var api_token: String
)