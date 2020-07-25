package com.teckudos.architecturepatterns.mvi.ui.main.state

import com.teckudos.architecturepatterns.mvi.model.BlogPost
import com.teckudos.architecturepatterns.mvi.model.User

data class MainViewState(
    var user: User? = null,
    var blogPosts: List<BlogPost>? = null
) {

}