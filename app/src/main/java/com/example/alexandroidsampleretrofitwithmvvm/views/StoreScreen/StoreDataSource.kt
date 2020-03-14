package com.example.alexandroidsampleretrofitwithmvvm.views.StoreScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.alexandroidsampleretrofitwithmvvm.JavaUtils
import com.example.alexandroidsampleretrofitwithmvvm.NetworkState
import com.example.alexandroidsampleretrofitwithmvvm.Status
import com.example.alexandroidsampleretrofitwithmvvm.classes.Store
import com.example.alexandroidsampleretrofitwithmvvm.model.APIWraper
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseGetStore
import com.example.androidnamechk.api.APIService
import com.example.androidnamechk.api.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreDataSource : PageKeyedDataSource<Int, Store>(){


    val  networkState =  MutableLiveData<NetworkState>()
    val initialLoading  =  MutableLiveData<NetworkState>()

    val apiWraperStore : MutableLiveData<APIWraper<ResponseGetStore>> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Store>) {


        var apiService= ServiceGenerator.createService(APIService::class.java)
        var call = apiService.getStores("1")
 
        initialLoading.postValue(NetworkState.LOADING)
        networkState.postValue(NetworkState.LOADING)

        call.enqueue(object : Callback<ResponseGetStore> {

            // If you receive a HTTP Response, then this method is executed
            override fun onResponse(call: Call<ResponseGetStore>, response: Response<ResponseGetStore>) {

                if (response.isSuccessful) {
                    apiWraperStore.value= APIWraper( response.body() ,null,null,null)


                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data
                    responseItems.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }

                    initialLoading.postValue(NetworkState.LOADED)
                    networkState.postValue(NetworkState.LOADED)


                }else{
                    //any code except 200..300
                    val errorBodyJson = response.errorBody()!!.string()
                    apiWraperStore.value= APIWraper( null, null ,errorBodyJson,response.code())




                    Log.d("","null")
                    initialLoading.postValue(NetworkState.FALIED)
//                    networkState.postValue(NetworkState(Status.FAILED, response.message()))
                    networkState.postValue(NetworkState.FALIED)


                }
            }

            // Invoked in case of Network Error or Establishing connection with Server
            // or Error Creating Http Request or Error Processing Http Response
            override fun onFailure(call: Call<ResponseGetStore>, t: Throwable) {
                val errorMessage: String = t.message.toString()
                networkState.postValue(NetworkState.FALIED)
                initialLoading.postValue(NetworkState.FALIED)


                apiWraperStore.value=
                    APIWraper( null, JavaUtils.checkErrorRequest(t.message) ,null, null)


            }
        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Store>) {

        var apiService= ServiceGenerator.createService(APIService::class.java)
        var call = apiService.getStores(params.key.toString())
 

        networkState.postValue(NetworkState.LOADING)

        call.enqueue(object : Callback<ResponseGetStore> {

            override fun onResponse(call: Call<ResponseGetStore>, response: Response<ResponseGetStore>) {

                if (response.isSuccessful) {
                    apiWraperStore.value= APIWraper( response.body() ,null,null,null)



                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data

                    //if we add this condition or not >>
                    //the library will automatic know when the pagelist is finish>>
                    //if the result return empty array so it know this is last page>>
//                    or i can this condition to prevent it to request last request to know if list is empty or not
//                    val key = if (max_page> params.key) params.key + 1 else max_page
                    val key=params.key + 1
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                    networkState.postValue(NetworkState.LOADED)

                }else{
                    //any code except 200..300
                    val errorBodyJson = response.errorBody()!!.string()
                    apiWraperStore.value= APIWraper( null, null ,errorBodyJson,response.code())






                    networkState.postValue(NetworkState(Status.FAILED, response.message()))
                }
            }

            override fun onFailure(call: Call<ResponseGetStore>, t: Throwable) {
                val errorMessage: String = t.message.toString()
                //send message here if you want show error in adapter
                networkState.postValue(NetworkState(Status.FAILED, errorMessage))
                initialLoading.postValue(NetworkState.FALIED)





                apiWraperStore.value=
                    APIWraper( null, JavaUtils.checkErrorRequest(t.message) ,null, null)

            }
        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Store>) {
        var apiService= ServiceGenerator.createService(APIService::class.java)
        var call = apiService.getStores(params.key.toString())

        
        var p = params.key;
        var p2 = params.key;
        call.enqueue(object : Callback<ResponseGetStore> {

            override fun onResponse(call: Call<ResponseGetStore>, response: Response<ResponseGetStore>) {

                if (response.isSuccessful) {
                    apiWraperStore.value= APIWraper( response.body() ,null,null,null)






                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data
                    val key = if (params.key > 1) params.key - 1 else 0

                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }else{
                    //any code except 200..300
                    val errorBodyJson = response.errorBody()!!.string()
                    apiWraperStore.value= APIWraper( null, null ,errorBodyJson,response.code())






                    networkState.postValue(NetworkState(Status.FAILED, response.message()))

                }
            }

            override fun onFailure(call: Call<ResponseGetStore>, t: Throwable) {
                val errorMessage: String = t.message.toString()
                networkState.postValue(NetworkState(Status.FAILED, errorMessage))





                apiWraperStore.value=
                    APIWraper( null, JavaUtils.checkErrorRequest(t.message) ,null, null)

            }
        })
    }

    companion object {
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 1
        const val TOPIC = "alexxx"  //becase this retuen 52 recordes>> so i will pagenate 5 times then stop>>
    }
}