package com.example.alexandroidsampleretrofitwithmvvm.model

import com.example.alexandroidsampleretrofitwithmvvm.classes.Store
import com.google.gson.annotations.SerializedName

class ResponseGetStore (

    @SerializedName("data")
    var data: List<Store>
)