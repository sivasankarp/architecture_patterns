package com.teckudos.architecturepatterns.dagger.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    val email: String = "",
    val name: String = "",
    val phone: String = "",
    val username: String = "",
    val website: String = ""
)