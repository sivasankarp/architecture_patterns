package com.teckudos.architecturepatterns.dagger.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    /*
     * same as above
     * @Singleton
     * @Provides
     * fun bindFactory(factory: ViewModelProviderFactory) : ViewModelProvider.Factory{
     *    return factory
     *  }
     */
}