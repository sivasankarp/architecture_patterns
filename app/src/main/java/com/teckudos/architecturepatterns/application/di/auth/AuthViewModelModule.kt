package com.teckudos.architecturepatterns.application.di.auth

import androidx.lifecycle.ViewModel
import com.teckudos.architecturepatterns.application.di.ViewModelKey
import com.teckudos.architecturepatterns.application.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}