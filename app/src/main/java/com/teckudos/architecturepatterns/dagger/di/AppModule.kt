package com.teckudos.architecturepatterns.dagger.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.teckudos.architecturepatterns.R
import com.teckudos.architecturepatterns.dagger.util.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*
* To put all application level dependency like retrofit instance, glide instance anything which
* exist and not change throughout lifetime of the application.
* was recommended to use static
* with the help of scoping we can reduce singleton variables available at particular time in memory
* */
@Module
class AppModule {

    @Singleton
    @Provides // used to declare dependency it create dependency when string is injected in activity
    fun someString(): String {
        return "test string"
    }

    @Singleton
    @Provides
    fun getApp(application: Application): Boolean {
        return application == null
    }

    @Singleton
    @Provides
    fun check(string: String): Int {
        return if (string == "test string") 1 else 0
    }

    @Singleton
    @Provides
    fun provideRequestOption(): RequestOptions {
        return RequestOptions()
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun provideAppDrawable(application: Application): Drawable {
        return ContextCompat.getDrawable(application, R.drawable.logo)!!
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}