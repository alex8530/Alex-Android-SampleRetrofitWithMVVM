package com.example.alexandroidsampleretrofitwithmvvm.views.StoreScreen

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.alexandroidsampleretrofitwithmvvm.classes.Store

class StoreDataSourceFactory  : DataSource.Factory<Int, Store>()  {

    val storeLiveDataSource = MutableLiveData<StoreDataSource>()

    override fun create(): DataSource<Int, Store> {
        val storeDataSource = StoreDataSource()
        storeLiveDataSource.postValue(storeDataSource)
        return storeDataSource
    }

}