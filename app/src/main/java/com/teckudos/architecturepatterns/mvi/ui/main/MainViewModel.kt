package com.teckudos.architecturepatterns.mvi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.teckudos.architecturepatterns.mvi.model.BlogPost
import com.teckudos.architecturepatterns.mvi.model.User
import com.teckudos.architecturepatterns.mvi.repository.Repository
import com.teckudos.architecturepatterns.mvi.ui.main.state.MainStateEvent
import com.teckudos.architecturepatterns.mvi.ui.main.state.MainStateEvent.*
import com.teckudos.architecturepatterns.mvi.ui.main.state.MainViewState
import com.teckudos.architecturepatterns.mvi.util.AbsentLiveData
import com.teckudos.architecturepatterns.mvi.util.DataState

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData<MainStateEvent>()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData<MainViewState>()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            when (stateEvent) {
                is GetUserEvent -> {
                    // return@switchMap AbsentLiveData.create()
                    /*return@switchMap object : LiveData<MainViewState>() {
                        override fun onActive() {
                            super.onActive()
                            val user = User(
                                email = "mitch@tabian.ca",
                                username = "mitch",
                                image = "https://cdn.open-api.xyz/open-api-static/static-random-images/logo_1080_1080.png"
                            )
                            value = MainViewState(
                                user = user
                            )
                        }
                    }*/
                    return@switchMap Repository.getUser(stateEvent.userId)
                }
                is GetBlogPostsEvent -> {
                    return@switchMap Repository.getBlogPosts()
                }
                is None -> {
                    return@switchMap AbsentLiveData.create<DataState<MainViewState>>()
                }
            }
        }

    fun setBlogListData(blogPosts: List<BlogPost>) {
        val update = getCurrentViewStateOrNew()
        update.blogPosts = blogPosts
        _viewState.value = update
    }

    fun setUser(user: User) {
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun getCurrentViewStateOrNew(): MainViewState {
        val value = viewState.value ?: MainViewState()
        return value
    }

    fun setStateEvent(event: MainStateEvent) {
        // val state: MainStateEvent = event
        _stateEvent.value = event
    }

}