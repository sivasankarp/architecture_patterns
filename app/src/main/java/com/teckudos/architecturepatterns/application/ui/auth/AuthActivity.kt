package com.teckudos.architecturepatterns.application.ui.auth

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.teckudos.architecturepatterns.R
import com.teckudos.architecturepatterns.application.ui.BaseActivity
import com.teckudos.architecturepatterns.application.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class AuthActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
    }
}

















