package com.omurgun.learncorebackendandroidui.service.learnCoreBackend.auth

import com.omurgun.learncorebackendandroidui.model.request.UserLoginRequest
import com.omurgun.learncorebackendandroidui.model.request.UserRegisterRequest
import com.omurgun.learncorebackendandroidui.model.response.UserLoginResponse
import com.omurgun.learncorebackendandroidui.model.response.UserRegisterResponse
import com.omurgun.learncorebackendandroidui.util.constant.ProjectConstants.Companion.PROJECT_BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserAPIService {
    private val api = Retrofit.Builder()
            .baseUrl(PROJECT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(UserAPI::class.java)


    fun register(userRegisterRequest: UserRegisterRequest) : Single<UserRegisterResponse> {
        return api.registerUser(userRegisterRequest)
    }

    fun login(userLoginRequest: UserLoginRequest) : Single<UserLoginResponse> {
        return api.loginUser(userLoginRequest)
    }




}