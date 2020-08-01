package com.teckudos.architecturepatterns.dagger.ui.authentication

class AuthenticationResource<T>(
    val status: AuthStatus,
    val data: T?,
    val message: String?
) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    companion object {
        fun <T> authenticated(data: T?): AuthenticationResource<T> {
            return AuthenticationResource(AuthStatus.AUTHENTICATED, data, null)
        }

        fun <T> error(msg: String, data: T?): AuthenticationResource<T> {
            return AuthenticationResource(AuthStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): AuthenticationResource<T> {
            return AuthenticationResource(AuthStatus.LOADING, data, null)
        }

        fun <T> logout(): AuthenticationResource<T> {
            return AuthenticationResource(AuthStatus.NOT_AUTHENTICATED, null, null)
        }
    }

}