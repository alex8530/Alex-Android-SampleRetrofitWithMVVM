package com.example.alexandroidsampleretrofitwithmvvm.LoginScreen


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.alexandroidsampleretrofitwithmvvm.ConstatVal
import com.example.alexandroidsampleretrofitwithmvvm.ConstatVal.SERVER_ERROR_CODE
import com.example.alexandroidsampleretrofitwithmvvm.ConstatVal.UNAUTHORIZED_CODE

import com.example.alexandroidsampleretrofitwithmvvm.R
import com.example.alexandroidsampleretrofitwithmvvm.Utils
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseLoginUserSuccess
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseUserLoginError
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by lazy {

        ViewModelProviders.of(this)
            .get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view =  inflater.inflate(R.layout.fragment_login, container, false)


        viewModel.isLoading.observe(this, Observer {

            if (it){
                progress.visibility=View.VISIBLE
            }else{
                progress.visibility=View.GONE
            }
        })

        viewModel.apiWraperLogin.observe(this, Observer {
             if (it.data!=null){
                // when success code 200
                 if (it.data?.status==true){
                     print("print the result list or anything..")
                 }else{
                     print("")
                 }
            }else if (it.error !=null  ){
                //this error happend when somting wrong in the server for example no internet
                print("print the error" + it.error)

            }else if (it.gsonError!=null){
                //this error happend when the request code is not from 200..300 >> ex.400 to 500

                 if (it.code!=null && it.code == UNAUTHORIZED_CODE){//401
                     //unAuthorize >> restart the app and clean the savedUser data
                    print("")

                 }else{

                     //todo this ResponseUserLoginError should be same for all request..
                     print("print the error")
                     var gson= Gson()
                     val responseObject = gson.fromJson(it.gsonError,  ResponseLoginUserSuccess::class.java)
//                     Utils.showFailAlert(activity,responseObject.message)
                     Utils.showFailAlert(activity,responseObject.errors[0][0])

                 }
            }else{
                print("")
            }
        })

//        viewModel.sendLogin()
        viewModel.sendLogin2()
        return  view
    }
}
