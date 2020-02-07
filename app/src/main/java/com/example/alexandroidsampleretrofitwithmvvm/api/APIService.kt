package com.example.androidnamechk.api

import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseLoginUserSuccess
import retrofit2.Call
import retrofit2.http.*


interface APIService {


    @POST("login")
    @FormUrlEncoded
    fun  login (
        @Field("email")   email:String ,
        @Field("password")   password :String
    ): Call<ResponseLoginUserSuccess>
}