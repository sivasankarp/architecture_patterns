package com.teckudos.architecturepatterns.application.repository.auth

import androidx.lifecycle.LiveData
import com.teckudos.architecturepatterns.application.api.auth.OpenApiAuthService
import com.teckudos.architecturepatterns.application.api.auth.network_responses.LoginResponse
import com.teckudos.architecturepatterns.application.api.auth.network_responses.RegistrationResponse
import com.teckudos.architecturepatterns.application.persistence.AccountPropertiesDao
import com.teckudos.architecturepatterns.application.persistence.AuthTokenDao
import com.teckudos.architecturepatterns.application.session.SessionManager
import com.teckudos.architecturepatterns.application.util.GenericApiResponse
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val openApiAuthService: OpenApiAuthService,
    val sessionManager: SessionManager
)
{

    fun testLoginRequest(email: String, password: String): LiveData<GenericApiResponse<LoginResponse>>{
        return openApiAuthService.login(email, password)
    }

    fun testRegistrationRequest(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<GenericApiResponse<RegistrationResponse>>{
        return openApiAuthService.register(email, username, password, confirmPassword)
    }
}







