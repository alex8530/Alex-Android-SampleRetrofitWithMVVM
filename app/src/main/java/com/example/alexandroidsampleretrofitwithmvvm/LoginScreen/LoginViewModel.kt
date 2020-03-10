package com.example.alexandroidsampleretrofitwithmvvm.LoginScreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.alexandroidsampleretrofitwithmvvm.ConstatVal
import com.example.alexandroidsampleretrofitwithmvvm.ConstatVal.SERVER_ERROR_CODE
import com.example.alexandroidsampleretrofitwithmvvm.JavaUtils
import com.example.alexandroidsampleretrofitwithmvvm.model.APIWraper
 import com.example.alexandroidsampleretrofitwithmvvm.model.DataUser
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseLoginUserSuccess
import com.example.androidnamechk.api.APIService
import com.example.androidnamechk.api.ServiceGenerator
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel (application: Application) : AndroidViewModel(application) ,ILoginViewModel {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    var userLogin :MutableLiveData<DataUser> = MutableLiveData()
    var apiWraperLogin :MutableLiveData<APIWraper<DataUser>> = MutableLiveData()
    var mApplication: Application = application


     override fun sendLogin() {
        isLoading.value=true
        var apiService= ServiceGenerator.createService(APIService::class.java)
        var call = apiService.login("c13c6f150e@mailboxok.club1","123123")
        call.enqueue(object : Callback<ResponseLoginUserSuccess> {
            override fun onFailure(call: Call<ResponseLoginUserSuccess>, t: Throwable) {
                isLoading.value=false

                Log.d("error",t.message)
                apiWraperLogin.value=APIWraper( null, JavaUtils.checkErrorRequest(mApplication,t.message) ,null, null)


            }
            override fun onResponse(
                call: Call<ResponseLoginUserSuccess>,
                response: Response<ResponseLoginUserSuccess>
            ) {
                 isLoading.value=false
                Log.d("log", "log: " + GsonBuilder().setPrettyPrinting().create().toJson(response.body() ))
                if (response.isSuccessful){
                     apiWraperLogin.value=APIWraper( response.body()!!.data ,null,null,null)
                }else{
                    val errorBodyJson = response.errorBody()!!.string()
                     apiWraperLogin.value=APIWraper( null, null ,errorBodyJson,response.code())

                }
            }

        })
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}