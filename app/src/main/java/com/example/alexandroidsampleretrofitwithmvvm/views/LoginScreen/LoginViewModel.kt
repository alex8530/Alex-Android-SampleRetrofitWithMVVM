package com.example.alexandroidsampleretrofitwithmvvm.views.LoginScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class LoginViewModel (application: Application) : AndroidViewModel(application) ,ILoginViewModel {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    var mApplication: Application = application


    private val loginRepository = LoginRepository()

    val isLoading=loginRepository.isLoading
    val apiWraperLogin =loginRepository.apiWraperLogin


//    override fun sendLogin2(country_id: Int, phone: String, password: String) {
//        isLoading.value=true
//        var apiService= ServiceGenerator.createService(APIService::class.java)
//        var call = apiService.login2(country_id,phone,password)
//        call.enqueue(object : Callback<ResponseLoginUserSuccess> {
//            override fun onFailure(call: Call<ResponseLoginUserSuccess>, t: Throwable) {
//                isLoading.value=false
//
//                Log.d("error",t.message)
//                apiWraperLogin.value=APIWraper( null, JavaUtils.checkErrorRequest(t.message) ,null, null)
//
//
//            }
//            override fun onResponse(
//                call: Call<ResponseLoginUserSuccess>,
//                response: Response<ResponseLoginUserSuccess>
//            ) {
//                isLoading.value=false
//                Log.d("log", "log: " + GsonBuilder().setPrettyPrinting().create().toJson(response.body() ))
//                if (response.isSuccessful){
//                    apiWraperLogin.value=APIWraper( response.body() ,null,null,null)
//                }else{
//                    //any code except 200..300
//                    val errorBodyJson = response.errorBody()!!.string()
//                    apiWraperLogin.value=APIWraper( null, null ,errorBodyJson,response.code())
//
//                }
//            }
//
//        })
//    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    override fun sendLogin2(country_id: Int, phone: String, password: String) {
        loginRepository.login(country_id,phone,password)
    }

}