package com.omurgun.learncorebackendandroidui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.omurgun.learncorebackendandroidui.R
import com.omurgun.learncorebackendandroidui.databinding.ActivityLoginBinding
import com.omurgun.learncorebackendandroidui.model.request.UserLoginRequest
import com.omurgun.learncorebackendandroidui.viewModel.activity.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        observeLiveData()
    }


    fun loginClicked(view: View) {
        val email:String = binding.txtEmail.text.toString()
        val password:String = binding.txtPassword.text.toString()


        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            println("gerekli alanları doldurun")
            Toast.makeText(this,"Gerekli alanları doldurun",Toast.LENGTH_LONG).show()
        }
        else{
            login(UserLoginRequest(email, password))
        }

    }




    private fun login(userLogin: UserLoginRequest){

        viewModel.loginWithEmailAndPassword(userLogin)
    }


    private fun observeLiveData() {

        viewModel.loading.observe(this, { loading->
            loading?.let {
                if (it) {
                    loginLoading.visibility = View.VISIBLE
                } else {
                    loginLoading.visibility = View.GONE
                }
            }
        })

        //viewLifecycleOwner
        viewModel.container.observe(this, { container->
            container?.let {
                if(it) {
                    loginContainer.visibility = View.VISIBLE
                } else {
                    loginContainer.visibility = View.INVISIBLE
                }
            }
        })

        viewModel.isLoginWithEmailAndPassword.observe(this, { withEmailAndPassword->
            withEmailAndPassword?.let {
                if (it) {
                    goMain()
                }
            }
        })
    }

    private fun goMain(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}