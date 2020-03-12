package com.example.alexandroidsampleretrofitwithmvvm.views.LoginScreen


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.alexandroidsampleretrofitwithmvvm.ConstatVal.UNAUTHORIZED_CODE

import com.example.alexandroidsampleretrofitwithmvvm.R
import com.example.alexandroidsampleretrofitwithmvvm.Utils
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseLoginUser
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by lazy {

        ViewModelProviders.of(this)
            .get(LoginViewModel::class.java)
    }

    lateinit var navController : NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var view =  inflater.inflate(R.layout.fragment_login, container, false)

        navController=Navigation.findNavController(activity!!,R.id.my_nav_host_fragment)
        view.btn_goToStore.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_storeFragment)
        }
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
                     Utils.showSuccessAlert(activity,"hi " + it?.data?.dataUser?.user?.name)

                 }else{
                     print("")
                 }
            }else if (it.error !=null  ){
                //this error happened when something wrong in the server for example no internet
                print("print the error" + it.error)
                 Utils.showFailAlert(activity, it.error!!)


             }else if (it.gsonError!=null){
                //this error happend when the request code is not from 200..300 >> ex.400 to 500

                 if (it.code!=null && it.code == UNAUTHORIZED_CODE){//401
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

//        viewModel.sendLogin()
        viewModel.sendLogin2(1,"0598530950","12345678")


//

        return  view
    }


}
