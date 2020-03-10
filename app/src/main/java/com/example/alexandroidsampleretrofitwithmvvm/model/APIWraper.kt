package com.example.alexandroidsampleretrofitwithmvvm.model

import com.google.gson.Gson

//check this web site https://medium.com/@sriramr083/error-handling-in-retrofit2-in-mvvm-repository-pattern-a9c13c8f3995

data class APIWraper<T>  (
    var data: T?=null,
    var error:String ?=null,
    var gsonError: String?= null,
    var code: Int?= null
)