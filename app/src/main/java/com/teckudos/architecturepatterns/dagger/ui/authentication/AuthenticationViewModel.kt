package com.teckudos.architecturepatterns.dagger.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.teckudos.architecturepatterns.dagger.model.User
import com.teckudos.architecturepatterns.dagger.network.authentication.AuthenticationAPI
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(private val authenticationAPI: AuthenticationAPI) :
    ViewModel() {

    private val authUser: MediatorLiveData<AuthenticationResource<User>> = MediatorLiveData()

    init {
        if (authenticationAPI == null) {
            print("DEBUG authenticationAPI null")
        } else {
            print("DEBUG authenticationAPI not null")
        }

        authenticationAPI.getData(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<User> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: User) {
                    println("DEBUG success ${t.email}")
                }

                override fun onError(e: Throwable) {
                    println("DEBUG error $e")
                }

            })
    }

    fun authenticateWithId(userId: Int) {
        authUser.value = AuthenticationResource.loading(null)
        val source: LiveData<AuthenticationResource<User>> =
            LiveDataReactiveStreams.fromPublisher(
                authenticationAPI.getData(userId)
                    // instead of calling onError, do this
                    .onErrorReturn {
                        val errorUser = User()
                        errorUser.id = -1
                        errorUser
                    }
                    // wrap User object in AuthResource
                    .map(object :
                        io.reactivex.functions.Function<User, AuthenticationResource<User>> {
                        override fun apply(user: User): AuthenticationResource<User> {
                            return if (user.id == -1) {
                                AuthenticationResource.error("Could not authenticate", null)
                            } else AuthenticationResource.authenticated(user)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        authUser.addSource(
            source
        ) { authResource ->
            authUser.value = authResource
            authUser.removeSource(source)
        }
    }

    fun observeUser(): LiveData<AuthenticationResource<User>> {
        return authUser
    }
}