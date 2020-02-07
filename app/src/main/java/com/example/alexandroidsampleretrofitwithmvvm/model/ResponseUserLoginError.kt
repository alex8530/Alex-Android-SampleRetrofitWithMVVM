package com.example.alexandroidsampleretrofitwithmvvm.model

import com.google.gson.annotations.SerializedName

class ResponseUserLoginError (

    @SerializedName("status")
    var status: Boolean ,
    @SerializedName("message")
    var message: String
)