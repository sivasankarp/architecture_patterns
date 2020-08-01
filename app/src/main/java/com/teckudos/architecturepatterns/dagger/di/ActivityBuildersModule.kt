package com.teckudos.architecturepatterns.dagger.di

import com.teckudos.architecturepatterns.dagger.ui.authentication.AuthenticationActivity
import com.teckudos.architecturepatterns.dagger.di.authentication.AuthenticationViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
* All this classes needs to be abstract which uses ContributesAndroidInjector
* */
@Module
abstract class ActivityBuildersModule {

    /*
    * only the authentication activity submodule can use authentication view model
    * we want to make subcomponent for organization and scoping so that dependency inside that subcomponent
    * only exist in this subcomponent
    * */
    @ContributesAndroidInjector(
        modules = [
            AuthenticationViewModelsModule::class
        ]
    )
    abstract fun contributeAuthenticationActivity(): AuthenticationActivity

}