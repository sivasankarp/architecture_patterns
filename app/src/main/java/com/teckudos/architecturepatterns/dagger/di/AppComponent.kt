package com.teckudos.architecturepatterns.dagger.di

import android.app.Application
import com.teckudos.architecturepatterns.dagger.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/*
* It persist entire lifetime of the application
* AndroidInjector<BaseApplication> we are telling dagger to inject base application to AppComponent
* BaseApplication is the client of the AppComponent service
* the purpose of adding singleton in appcomponent to know dagger that this kept in memory as long as app alive
* */
@Singleton // AppComponent owns singleton scope
@Component(
    modules = [
        AndroidSupportInjectionModule::class, // it need to specify for dagger support in android
        AppModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    // overriding the component builder method to instantiate AppComponent
    @Component.Builder
    interface Builder {

        @BindsInstance // used to bind a particular instance of object to the component at the time of construction
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}