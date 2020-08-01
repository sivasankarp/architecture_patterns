package com.teckudos.architecturepatterns.dagger.network.authentication

import com.teckudos.architecturepatterns.dagger.model.User
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthenticationAPI {

    @GET("users/{id}")
    fun getData(@Path("id") id: Int): Flowable<User>
}