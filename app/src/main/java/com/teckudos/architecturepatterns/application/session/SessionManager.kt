package com.teckudos.architecturepatterns.application.session

import android.app.Application
import com.teckudos.architecturepatterns.application.persistence.AuthTokenDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val application: Application
)
{

}