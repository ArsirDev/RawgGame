package com.example.perqaraassessment.presentation.splash.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.perqaraassessment.databinding.ActivitySplashBinding
import com.example.perqaraassessment.presentation.home.activity.HomeActivity
import com.example.perqaraassessment.presentation.splash.viewmodel.SplashViewModel
import com.example.perqaraassessment.presentation.welcome.WelcomeActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class Splash : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null

    private val binding get() = _binding as ActivitySplashBinding

    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        initInstallSplashScreen()
        super.onCreate(savedInstanceState)
        initInstance()
        setContentView(binding.root)
        initStartLoading()
        initOnBackPressed()
    }

    private fun initOnBackPressed() {
        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                exitOnBackPressed()
            }
        } else {
            onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        exitOnBackPressed()
                    }
                })
        }
    }

    private fun exitOnBackPressed() {
        finishAffinity()
    }

    private fun initStartLoading() {
        findViewById<View>(android.R.id.content)?.apply {
            viewTreeObserver.addOnPreDrawListener (
                object: ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        return if (viewModel.isLoading.value) {
                            viewTreeObserver.removeOnPreDrawListener(this)
                            true
                        } else false
                    }
                }
            )
        }
    }

    private fun initInstallSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
            directionNavigation()
            setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(
                    splashScreenView.view,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.view.height.toFloat()
                ).apply {
                    interpolator = AnticipateInterpolator()
                    duration = 1000L
                    doOnEnd {
                        splashScreenView.remove()
                    }
                }.start()
            }
        }
    }

    private fun directionNavigation() {
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collectLatest { status ->
                        if (status) {
                            startActivity(Intent(this@Splash, HomeActivity::class.java))
                            finish()
                        } else {
                            startActivity(Intent(this@Splash, WelcomeActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun initInstance() {
        _binding = ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}