package com.teckudos.architecturepatterns.dagger.ui.authentication

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.teckudos.architecturepatterns.R
import com.teckudos.architecturepatterns.dagger.di.ViewModelProviderFactory
import com.teckudos.architecturepatterns.databinding.ActivityAuthenticationBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthenticationActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var injectedString: String

    @JvmField
    @Inject
    var injectedInt: Int = 0

    @JvmField
    @Inject
    var isAppNull: Boolean = false

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    lateinit var binding: ActivityAuthenticationBinding

    lateinit var authenticationViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication)
        authenticationViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(AuthenticationViewModel::class.java)

        println("DEBUG $injectedString")
        println("DEBUG $injectedInt")
        println("DEBUG $isAppNull")

        setLogo()
    }

    private fun setLogo() {
        requestManager.load(logo).into(binding.loginLogo)
    }
}