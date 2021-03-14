package com.omurgun.learncorebackendandroidui.viewModel.base

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.omurgun.learncorebackendandroidui.model.request.UserLoginRequest
import com.omurgun.learncorebackendandroidui.model.request.UserRegisterRequest
import com.omurgun.learncorebackendandroidui.model.response.UserLoginResponse
import com.omurgun.learncorebackendandroidui.model.response.UserRegisterResponse
import com.omurgun.learncorebackendandroidui.service.learnCoreBackend.auth.UserAPIService
import com.omurgun.learncorebackendandroidui.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException

open class BaseUserViewModel(application: Application) : BaseViewModel(application) {


    //(+)loginWithEmailAndPassword


    private val userApiService = UserAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())


    val container = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val isLoginWithEmailAndPassword = MutableLiveData<Boolean>()
    val isLoginWithToken = MutableLiveData<Boolean>()
    val isRegisterWithEmailAndPassword = MutableLiveData<Boolean>()
    val isLogout = MutableLiveData<Boolean>()


    fun loginWithEmailAndPassword(userLogin: UserLoginRequest) {
        loading.value = true
        container.value = false


        disposable.add(
            userApiService.login(userLogin)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserLoginResponse>() {
                    override fun onSuccess(t: UserLoginResponse) {
                        Toast.makeText(
                            getApplication(),
                            "loginWithEmailAndPassword(UserLogin) From API",
                            Toast.LENGTH_LONG
                        ).show()
                        isLoginWithEmailAndPassword.value = true
                        loading.value = false
                        container.value = false
                    }

                    override fun onError(e: Throwable) {
                        container.value = true
                        loading.value = false
                        isLoginWithEmailAndPassword.value = false


                        val responseBody: ResponseBody? =
                            (e as HttpException).response()?.errorBody()
                        if (responseBody != null) {
                            println("error : ${getErrorMessage(responseBody)}")
                        }
                    }

                })
        )
    }

    fun registerWithEmailAndPassword(userRegister: UserRegisterRequest) {
        loading.value = true
        container.value = false

        disposable.add(
            userApiService.register(userRegister)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserRegisterResponse>() {
                    override fun onSuccess(t: UserRegisterResponse) {
                        Toast.makeText(
                            getApplication(),
                            "registerWithEmailAndPassword From API",
                            Toast.LENGTH_LONG
                        ).show()
                        isRegisterWithEmailAndPassword.value = true
                        loading.value = false
                        container.value = false
                    }

                    override fun onError(e: Throwable) {
                        container.value = true
                        isRegisterWithEmailAndPassword.value = false
                        loading.value = false



                        val responseBody: ResponseBody? =
                            (e as HttpException).response()?.errorBody()
                        if (responseBody != null) {
                            println("error : ${getErrorMessage (responseBody)}")
                        }
                    }
                })
        )

    }






    private fun getErrorMessage(responseBody: ResponseBody): String? {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            jsonObject.getString("message")
        } catch (e: Exception) {
            e.message
        }
    }


   /* private fun dropInPreferencesLocalUser() {
        launch {
            customPreferences.removeToLocalUser()
        }

    }

    private fun storeInPreferencesInternalUser(user: InternalUser) {
        launch {
            customPreferences.saveToLocalUser(user)
        }

    }

    private fun restoreInPreferences() {
        user = customPreferences.getToLocalUser()

    }*/

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}