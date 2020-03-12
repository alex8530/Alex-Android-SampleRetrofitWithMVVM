package com.example.alexandroidsampleretrofitwithmvvm.views.StoreScreen


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.alexandroidsampleretrofitwithmvvm.ConstatVal

import com.example.alexandroidsampleretrofitwithmvvm.R
import com.example.alexandroidsampleretrofitwithmvvm.Utils
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseLoginUser
import com.example.alexandroidsampleretrofitwithmvvm.views.LoginScreen.LoginViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_store.view.*

/**
 * A simple [Fragment] subclass.
 */
class StoreFragment : Fragment() {
    private val viewModel: StoreViewModel by lazy {

        ViewModelProviders.of(this)
            .get(StoreViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_store, container, false)

        viewModel.isLoading.observe(this, Observer {

            if (it){
                view.progress.visibility=View.VISIBLE
            }else{
                view.progress.visibility=View.GONE
            }
        })


        getStores()


        return  view

    }

    private fun getStores() {
        viewModel.getStore()


        viewModel.apiWraperStore.observe(this, Observer {
            if (it.data!=null){
                // when success code 200
                it.data!!.data.forEach {
                    Log.d("dataalex: ",it.name +"\n")
                }

                    print("")
             }else if (it.error !=null  ){
                //this error happened when something wrong in the server for example no internet
                print("print the error" + it.error)
                Utils.showFailAlert(activity, it.error!!)


            }else if (it.gsonError!=null){
                //this error happend when the request code is not from 200..300 >> ex.400 to 500

                if (it.code!=null && it.code == ConstatVal.UNAUTHORIZED_CODE){//401
                    //unAuthorize >> restart the app and clean the savedUser data
                    print("")

                }else{


                    print("print the error")
                    var gson= Gson()
                    val responseObject = gson.fromJson(it.gsonError,  ResponseLoginUser::class.java)
//                     Utils.showFailAlert(activity,responseObject.message)
                    Utils.showFailAlert(activity,responseObject.errors[0][0])

                }
            }else{
                print("")
            }
        })


    }


}
