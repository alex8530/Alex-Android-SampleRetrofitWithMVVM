package com.example.alexandroidsampleretrofitwithmvvm.model

import com.google.gson.annotations.SerializedName

class ResponseUserLoginError (


    //i think this class should be similar for all other response



    @SerializedName("status")
    var status: Boolean ,
    @SerializedName("message")
    var message: String
)