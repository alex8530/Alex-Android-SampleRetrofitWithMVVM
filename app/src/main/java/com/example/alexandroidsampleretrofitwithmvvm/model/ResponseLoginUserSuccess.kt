package com.example.alexandroidsampleretrofitwithmvvm.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseLoginUserSuccess (

    @SerializedName("status")
     var status: Boolean ,
    @SerializedName("data")
     var data: DataUser
)