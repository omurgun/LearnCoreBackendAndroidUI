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
import com.omurgun.learncorebackendandroidui.databinding.ActivityRegisterBinding
import com.omurgun.learncorebackendandroidui.model.request.UserRegisterRequest
import com.omurgun.learncorebackendandroidui.util.CustomSharedPreferences
import com.omurgun.learncorebackendandroidui.viewModel.activity.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var customPreferences: CustomSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        customPreferences = CustomSharedPreferences(application)
    }

    override fun onStart() {
        super.onStart()
        observeLiveData()
    }


    private fun register(userRegister: UserRegisterRequest){
        viewModel.registerWithEmailAndPassword(userRegister)
    }

    fun registerClicked(view: View) {

        val firstName:String = binding.txtFirstName.text.toString()
        val lastName:String = binding.txtLastName.text.toString()
        val email:String = binding.txtEmail.text.toString()
        val password:String = binding.txtPassword.text.toString()

        if (TextUtils.isEmpty(firstName) ||TextUtils.isEmpty(lastName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            println("gerekli alanları doldurun")
            Toast.makeText(this,"Gerekli alanları doldurun", Toast.LENGTH_LONG).show()
        } else{
            register(UserRegisterRequest(firstName,lastName,email,password))
        }
    }

    private fun goWelcome(){
        val intent = Intent(this,WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goLogin(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }



    private fun observeLiveData() {


        viewModel.loading.observe(this, { loading->
            loading?.let {
                if (it) {
                    registerLoading.visibility = View.VISIBLE
                } else {
                    registerLoading.visibility = View.GONE
                }
            }
        })

        viewModel.container.observe(this, { container->
            container?.let {
                if(it) {
                    registerContainer.visibility = View.VISIBLE
                } else {
                    registerContainer.visibility = View.INVISIBLE
                }
            }
        })

        viewModel.isRegisterWithEmailAndPassword.observe(this, { withEmailAndPassword->
            withEmailAndPassword?.let {
                if (it) {
                    goWelcome()
                }
            }
        })
    }
}