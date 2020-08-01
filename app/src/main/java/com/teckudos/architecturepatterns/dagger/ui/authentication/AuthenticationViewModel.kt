package com.teckudos.architecturepatterns.dagger.ui.authentication

import androidx.lifecycle.ViewModel
import com.teckudos.architecturepatterns.dagger.network.authentication.AuthenticationAPI
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(authenticationAPI: AuthenticationAPI) :
    ViewModel() {

    init {
        if (authenticationAPI == null) {
            print("DEBUG authenticationAPI null")
        } else {
            print("DEBUG authenticationAPI not null")
        }
    }
}