package com.example.alexandroidsampleretrofitwithmvvm.LoginScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.alexandroidsampleretrofitwithmvvm.JavaUtils
import com.example.alexandroidsampleretrofitwithmvvm.model.APIWraper
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseLoginUserSuccess
import com.example.androidnamechk.api.APIService
import com.example.androidnamechk.api.ServiceGenerator
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository  : ILoginRepository{

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var apiWraperLogin :MutableLiveData<APIWraper<ResponseLoginUserSuccess>> = MutableLiveData()

    override fun login(country_id: Int, phone: String, password: String) {

       isLoading.value=true

            var apiService= ServiceGenerator.createService(APIService::class.java)
            var call = apiService.login2(country_id,phone,password)
            call.enqueue(object : Callback<ResponseLoginUserSuccess> {
                override fun onFailure(call: Call<ResponseLoginUserSuccess>, t: Throwable) {
                    isLoading.value=false

                    Log.d("error",t.message)
                    apiWraperLogin.value=
                        APIWraper( null, JavaUtils.checkErrorRequest(t.message) ,null, null)


                }
                override fun onResponse(
                    call: Call<ResponseLoginUserSuccess>,
                    response: Response<ResponseLoginUserSuccess>
                ) {
                    isLoading.value=false
                    Log.d("log", "log: " + GsonBuilder().setPrettyPrinting().create().toJson(response.body() ))
                    if (response.isSuccessful){
                        apiWraperLogin.value= APIWraper( response.body() ,null,null,null)
                    }else{
                        //any code except 200..300
                        val errorBodyJson = response.errorBody()!!.string()
                        apiWraperLogin.value= APIWraper( null, null ,errorBodyJson,response.code())

                    }
                }

            })


    }
}