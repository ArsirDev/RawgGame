package com.example.perqaraassessment.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.perqaraassessment.R
import com.example.perqaraassessment.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null

    private val binding get() = _binding as ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInstance()
        setContentView(binding.root)
    }

    private fun initInstance() {
        _binding = ActivitySplashBinding.inflate(layoutInflater)
    }
}