package com.teckudos.architecturepatterns.application.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.teckudos.architecturepatterns.application.api.auth.network_responses.LoginResponse
import com.teckudos.architecturepatterns.application.api.auth.network_responses.RegistrationResponse
import com.teckudos.architecturepatterns.application.repository.auth.AuthRepository
import com.teckudos.architecturepatterns.application.util.GenericApiResponse
import javax.inject.Inject

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
): ViewModel(){


    fun testLogin(): LiveData<GenericApiResponse<LoginResponse>>{
        return authRepository.testLoginRequest(
            "mitchelltabian@gmail.com",
            "codingwithmitch1"
        )
    }

    fun testRegister(): LiveData<GenericApiResponse<RegistrationResponse>>{
        return authRepository.testRegistrationRequest(
            "mitchelltabian1234@gmail.com",
            "mitchelltabian1234",
            "codingwithmitch1",
            "codingwithmitch1"
        )
    }

}





























