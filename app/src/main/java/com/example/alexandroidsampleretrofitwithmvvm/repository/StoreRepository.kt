package com.example.alexandroidsampleretrofitwithmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.alexandroidsampleretrofitwithmvvm.NetworkState
import com.example.alexandroidsampleretrofitwithmvvm.classes.Store
import com.example.alexandroidsampleretrofitwithmvvm.irepository.IStoreRepository
import com.example.alexandroidsampleretrofitwithmvvm.model.APIWraper
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseGetStore
import com.example.alexandroidsampleretrofitwithmvvm.views.StoreScreen.StoreDataSource
import com.example.alexandroidsampleretrofitwithmvvm.views.StoreScreen.StoreDataSourceFactory

class StoreRepository :
    IStoreRepository {

    //this line will create live data data source
    var itemDataSourceFactory: StoreDataSourceFactory =
        StoreDataSourceFactory()

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