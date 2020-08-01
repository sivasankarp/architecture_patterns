package com.teckudos.architecturepatterns.application.di

import com.teckudos.architecturepatterns.application.di.auth.AuthFragmentBuildersModule
import com.teckudos.architecturepatterns.application.di.auth.AuthModule
import com.teckudos.architecturepatterns.application.di.auth.AuthScope
import com.teckudos.architecturepatterns.application.di.auth.AuthViewModelModule
import com.teckudos.architecturepatterns.application.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthModule::class, AuthFragmentBuildersModule::class, AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

}