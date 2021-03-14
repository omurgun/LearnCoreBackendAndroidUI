package com.omurgun.learncorebackendandroidui.service.learnCoreBackend.auth


import com.omurgun.learncorebackendandroidui.model.request.UserLoginRequest
import com.omurgun.learncorebackendandroidui.model.request.UserRegisterRequest
import com.omurgun.learncorebackendandroidui.model.response.UserLoginResponse
import com.omurgun.learncorebackendandroidui.model.response.UserRegisterResponse
import com.omurgun.learncorebackendandroidui.util.constant.ProjectConstants.Companion.PROJECT_USER_LOGIN
import com.omurgun.learncorebackendandroidui.util.constant.ProjectConstants.Companion.PROJECT_USER_REGISTER
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface UserAPI {

    @POST(PROJECT_USER_REGISTER)
    fun registerUser(@Body userRegisterRequest: UserRegisterRequest): Single<UserRegisterResponse>


    @POST(PROJECT_USER_LOGIN)
    fun loginUser(@Body userLoginRequest: UserLoginRequest): Single<UserLoginResponse>


}