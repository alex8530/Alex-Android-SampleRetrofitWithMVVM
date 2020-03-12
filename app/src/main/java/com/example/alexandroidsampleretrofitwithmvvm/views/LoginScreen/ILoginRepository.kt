package com.example.alexandroidsampleretrofitwithmvvm.views.LoginScreen

interface ILoginRepository {

    fun login(country_id: Int, phone: String, password: String)
 }