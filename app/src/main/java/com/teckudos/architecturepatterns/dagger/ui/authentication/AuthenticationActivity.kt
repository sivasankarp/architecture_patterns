package com.teckudos.architecturepatterns.dagger.ui.authentication

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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

        binding.loginButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.userIdInput.text.toString())) return@setOnClickListener
            authenticationViewModel.authenticateWithId(binding.userIdInput.text.toString().toInt())
        }

        subscribeObserver()
    }

    private fun subscribeObserver() {
        authenticationViewModel.observeUser().observe(this, Observer { authResource ->
            if (authResource != null) {
                when (authResource.status) {
                    AuthenticationResource.AuthStatus.LOADING -> {
                        setProgressBar(true)
                    }
                    AuthenticationResource.AuthStatus.AUTHENTICATED -> {
                        println("DEBUG ${authResource.data?.email}")
                        setProgressBar(false)
                    }
                    AuthenticationResource.AuthStatus.ERROR -> {
                        setProgressBar(false)
                        Toast.makeText(
                            this@AuthenticationActivity,
                            "${authResource.message} enter no b/w 0 - 10",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    AuthenticationResource.AuthStatus.NOT_AUTHENTICATED -> {
                        setProgressBar(false)
                    }
                }
            }
        })
    }

    private fun setProgressBar(isVisible: Boolean) {
        if (isVisible) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun setLogo() {
        requestManager.load(logo).into(binding.loginLogo)
    }
}