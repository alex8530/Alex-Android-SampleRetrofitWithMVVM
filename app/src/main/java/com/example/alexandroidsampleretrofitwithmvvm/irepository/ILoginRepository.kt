package com.example.alexandroidsampleretrofitwithmvvm.irepository

interface ILoginRepository {

    fun login(country_id: Int, phone: String, password: String)
 }