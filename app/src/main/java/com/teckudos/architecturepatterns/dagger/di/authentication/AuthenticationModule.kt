package com.teckudos.architecturepatterns.dagger.di.authentication

import com.teckudos.architecturepatterns.dagger.network.authentication.AuthenticationAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthenticationModule {

    @Provides
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationAPI {
        return retrofit.create(AuthenticationAPI::class.java)
    }
}