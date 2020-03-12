package com.example.alexandroidsampleretrofitwithmvvm.views.StoreScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.alexandroidsampleretrofitwithmvvm.model.APIWraper
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseGetStore

class StoreViewModel(application: Application) :IStoreViewModel, AndroidViewModel(application)  {



    var storeRepository=StoreRepository()
    val isLoading=storeRepository.isLoading
    val apiWraperStore =storeRepository.apiWraperStore

    override fun getStore() {
        storeRepository.getStore()
    }

}