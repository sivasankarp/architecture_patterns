package com.teckudos.architecturepatterns.application.di.auth

import com.teckudos.architecturepatterns.application.ui.auth.ForgotPasswordFragment
import com.teckudos.architecturepatterns.application.ui.auth.LauncherFragment
import com.teckudos.architecturepatterns.application.ui.auth.LoginFragment
import com.teckudos.architecturepatterns.application.ui.auth.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeLauncherFragment(): LauncherFragment

    @ContributesAndroidInjector()
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector()
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector()
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment

}