package com.example.alexandroidsampleretrofitwithmvvm.views.StoreScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.alexandroidsampleretrofitwithmvvm.JavaUtils
import com.example.alexandroidsampleretrofitwithmvvm.NetworkState
import com.example.alexandroidsampleretrofitwithmvvm.classes.Store
import com.example.alexandroidsampleretrofitwithmvvm.model.APIWraper
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseGetStore
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseLoginUser
import com.example.androidnamechk.api.APIService
import com.example.androidnamechk.api.ServiceGenerator
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreRepository :IStoreRepository{

    //this line will create live data data source
    var itemDataSourceFactory: StoreDataSourceFactory = StoreDataSourceFactory()

    var storePagedList: LiveData<PagedList<Store>> = MutableLiveData()
    var networkState: LiveData<NetworkState> = MutableLiveData()
    var initialLoading: LiveData<NetworkState> = MutableLiveData()
    var apiWraperStore : LiveData<APIWraper<ResponseGetStore>> = MutableLiveData()



    override fun getStorePageList() {
        apiWraperStore=  Transformations.switchMap(itemDataSourceFactory.storeLiveDataSource ) {
                    dataSource -> dataSource.apiWraperStore
            }

        networkState =
            Transformations.switchMap(itemDataSourceFactory.storeLiveDataSource ) {
                    dataSource -> dataSource.networkState
            }

        initialLoading=
            Transformations.switchMap(itemDataSourceFactory.storeLiveDataSource) {
                    dataSource -> dataSource.initialLoading
            }

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(StoreDataSource.PAGE_SIZE)
            .build()

        storePagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()

    }
}