package com.teckudos.architecturepatterns.dagger.network.authentication

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface AuthenticationAPI {

    @GET
    fun getData() : Call<ResponseBody>
}