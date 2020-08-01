package com.teckudos.architecturepatterns.application.di.auth

import com.teckudos.architecturepatterns.application.api.auth.OpenApiAuthService
import com.teckudos.architecturepatterns.application.persistence.AccountPropertiesDao
import com.teckudos.architecturepatterns.application.persistence.AuthTokenDao
import com.teckudos.architecturepatterns.application.repository.auth.AuthRepository
import com.teckudos.architecturepatterns.application.session.SessionManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    @AuthScope
    @Provides
    fun provideOpenApiAuthService(retrofitBuilder: Retrofit.Builder): OpenApiAuthService {
        return retrofitBuilder
            .build()
            .create(OpenApiAuthService::class.java)
    }

    @AuthScope
    @Provides
    fun provideAuthRepository(
        sessionManager: SessionManager,
        authTokenDao: AuthTokenDao,
        accountPropertiesDao: AccountPropertiesDao,
        openApiAuthService: OpenApiAuthService
    ): AuthRepository {
        return AuthRepository(
            authTokenDao,
            accountPropertiesDao,
            openApiAuthService,
            sessionManager
        )
    }

}