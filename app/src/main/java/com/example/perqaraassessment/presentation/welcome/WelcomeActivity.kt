package com.example.perqaraassessment.presentation.welcome

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.example.perqaraassessment.R
import com.example.perqaraassessment.databinding.ActivityWelcomeBinding
import com.example.perqaraassessment.presentation.slider.activity.SliderActivity
import com.example.perqaraassessment.util.*

class WelcomeActivity : AppCompatActivity() {

    private var _binding: ActivityWelcomeBinding? = null

    private val binding get() = _binding as ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInstance()
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.circle_explode_animation).apply {
            duration = 900
            interpolator = AccelerateDecelerateInterpolator()
        }

        binding.ivDirection.setOnClickListenerWithDebounce {
            with(binding) {
                arrayListOf(ivDirection, tvStart, tvDescription).removeAllView()
                vCircleExplode.showView()
                vCircleExplode.startAnimation(animation) {
                    startActivity(Intent(this@WelcomeActivity, SliderActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(this@WelcomeActivity).toBundle())
                    binding.root.setBackgroundColor(ContextCompat.getColor(this@WelcomeActivity, R.color.white))
                    vCircleExplode.removeView()
                }
            }
        }
    }

    private fun initInstance() {
        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}