package com.omurgun.learncorebackendandroidui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.omurgun.learncorebackendandroidui.R
import com.omurgun.learncorebackendandroidui.databinding.ActivityLoginBinding
import com.omurgun.learncorebackendandroidui.databinding.ActivityWelcomeBinding
import com.omurgun.learncorebackendandroidui.viewModel.activity.LoginViewModel
import com.omurgun.learncorebackendandroidui.viewModel.activity.WelcomeViewModel

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var viewModel : WelcomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)
    }


}