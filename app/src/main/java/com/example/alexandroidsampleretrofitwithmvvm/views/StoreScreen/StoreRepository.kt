package com.example.alexandroidsampleretrofitwithmvvm.views.StoreScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.alexandroidsampleretrofitwithmvvm.JavaUtils
import com.example.alexandroidsampleretrofitwithmvvm.model.APIWraper
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseGetStore
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseLoginUser
import com.example.androidnamechk.api.APIService
import com.example.androidnamechk.api.ServiceGenerator
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreRepository  :IStoreRepository{


    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var apiWraperStore : MutableLiveData<APIWraper<ResponseGetStore>> = MutableLiveData()
    override fun getStore() {
        isLoading.value=true

        var apiService= ServiceGenerator.createService(APIService::class.java)
        var call = apiService.getStores("1")
        call.enqueue(object : Callback<ResponseGetStore> {
            override fun onFailure(call: Call<ResponseGetStore>, t: Throwable) {
                isLoading.value=false

                Log.d("error",t.message)
                apiWraperStore.value=
                    APIWraper( null, JavaUtils.checkErrorRequest(t.message) ,null, null)


            }
            override fun onResponse(
                call: Call<ResponseGetStore>,
                response: Response<ResponseGetStore>
            ) {
                isLoading.value=false
                Log.d("log", "log: " + GsonBuilder().setPrettyPrinting().create().toJson(response.body() ))
                if (response.isSuccessful){
                    apiWraperStore.value= APIWraper( response.body() ,null,null,null)
                }else{
                    //any code except 200..300
                    val errorBodyJson = response.errorBody()!!.string()
                    apiWraperStore.value= APIWraper( null, null ,errorBodyJson,response.code())

                }
            }

        })


    }
}