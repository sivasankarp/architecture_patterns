package com.teckudos.architecturepatterns.mvi.api

import androidx.lifecycle.LiveData
import com.teckudos.architecturepatterns.mvi.model.BlogPost
import com.teckudos.architecturepatterns.mvi.model.User
import com.teckudos.architecturepatterns.mvi.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/blogs")
    fun getBlogPosts(): LiveData<GenericApiResponse<List<BlogPost>>>

    @GET("placeholder/user/{userId}")
    fun getUser(@Path("userId") userId: String): LiveData<GenericApiResponse<User>>

}