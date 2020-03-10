package com.example.alexandroidsampleretrofitwithmvvm.LoginScreen

interface ILoginRepository {

    fun login(country_id: Int, phone: String, password: String)
 }