package com.example.alexandroidsampleretrofitwithmvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseLoginUserSuccess (

    @SerializedName("status")
     var status: Boolean ,
    @SerializedName("data")
     var dataUser: DataUser,



    //this var appear when get an errors response
    @SerializedName("message")
    var message: String,

    @SerializedName("errors")
    var errors : List<List<String>>

)