package com.teckudos.architecturepatterns.dagger.di.authentication

import androidx.lifecycle.ViewModel
import com.teckudos.architecturepatterns.application.di.ViewModelKey
import com.teckudos.architecturepatterns.dagger.ui.authentication.AuthenticationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthenticationViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthenticationViewModel::class)
    abstract fun bindAuthenticationViewModel(authenticationViewModel: AuthenticationViewModel): ViewModel

}