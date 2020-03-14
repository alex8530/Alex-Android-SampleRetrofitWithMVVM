package com.example.alexandroidsampleretrofitwithmvvm.views.StoreScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.alexandroidsampleretrofitwithmvvm.NetworkState
import com.example.alexandroidsampleretrofitwithmvvm.classes.Store
import com.example.alexandroidsampleretrofitwithmvvm.model.APIWraper
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseGetStore
import com.example.alexandroidsampleretrofitwithmvvm.repository.StoreRepository

class StoreViewModel(application: Application) :IStoreViewModel, AndroidViewModel(application)  {

    private var storeRepository=
        StoreRepository()

    var storePagedList: LiveData<PagedList<Store>> = MutableLiveData()
    var networkState: LiveData<NetworkState> = MutableLiveData()
    var initialLoading: LiveData<NetworkState> = MutableLiveData()
    var apiWraperStore : LiveData<APIWraper<ResponseGetStore>> = MutableLiveData()

    override fun getStore() {
        //should call this line before others..
        storeRepository.getStorePageList()

        //need this Wraper to keep track when an error happened
        apiWraperStore= storeRepository.apiWraperStore

        //need this for using in the adapter for last item progress bar(paging)
        networkState =storeRepository.networkState

        //need this for show dailog in the first time call
        initialLoading=storeRepository.initialLoading

        storePagedList = storeRepository.storePagedList
    }

    //this is for refresh data>>>
    fun refresh() {

        storeRepository.itemDataSourceFactory.storeLiveDataSource.value?.invalidate()
    }
}