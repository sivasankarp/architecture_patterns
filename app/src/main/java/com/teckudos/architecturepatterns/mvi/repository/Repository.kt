package com.teckudos.architecturepatterns.mvi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.teckudos.architecturepatterns.mvi.api.RetrofitBuilder
import com.teckudos.architecturepatterns.mvi.model.BlogPost
import com.teckudos.architecturepatterns.mvi.model.User
import com.teckudos.architecturepatterns.mvi.ui.main.state.MainViewState
import com.teckudos.architecturepatterns.mvi.util.*

object Repository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<List<BlogPost>, MainViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {
                result.value = DataState.data(
                    null,
                    data = MainViewState(
                        blogPosts = response.body,
                        user = null
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {
                return RetrofitBuilder.apiService.getBlogPosts()
            }

        }.asLiveData()
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<User, MainViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState.data(
                    null,
                    MainViewState(
                        blogPosts = null,
                        user = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {
                return RetrofitBuilder.apiService.getUser(userId)
            }

        }.asLiveData()

        /*return Transformations
            .switchMap(RetrofitBuilder.apiService.getUser(userId)) { apiResponse ->
                object : LiveData<DataState<MainViewState>>() {
                    override fun onActive() {
                        super.onActive()
                        when (apiResponse) {
                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    data = MainViewState(
                                        user = apiResponse.body
                                    )
                                )
                            }
                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    message = apiResponse.errorMessage
                                )
                            }

                            is ApiEmptyResponse -> {
                                value = DataState.error(
                                    message = "HTTP 204. Returned Nothing!"
                                )
                            }
                        }
                    }
                }
            }*/
    }
}




























