package com.example.alexandroidsampleretrofitwithmvvm.classes

import com.example.alexandroidsampleretrofitwithmvvm.classes.User
import com.google.gson.annotations.SerializedName

data class DataUser (

    @SerializedName("user")
      var user: User,

    @SerializedName("api_token")
      var api_token: String
)