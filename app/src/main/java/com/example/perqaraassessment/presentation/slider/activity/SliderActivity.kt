package com.example.perqaraassessment.presentation.slider.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.example.perqaraassessment.R
import com.example.perqaraassessment.databinding.ActivitySliderBinding
import com.example.perqaraassessment.presentation.home.activity.HomeActivity
import com.example.perqaraassessment.presentation.slider.adapter.SliderAdapter
import com.example.perqaraassessment.presentation.slider.viewmodel.SliderViewModel
import com.example.perqaraassessment.util.removeView
import com.example.perqaraassessment.util.setOnClickListenerWithDebounce
import com.example.perqaraassessment.util.showView
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SliderActivity : AppCompatActivity() {

    private var _binding: ActivitySliderBinding? = null

    private val binding get() = _binding as ActivitySliderBinding

    private val viewModel: SliderViewModel by inject()

    private lateinit var sliderAdapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInstance()
        setContentView(binding.root)
        initViewPager()
    }

    private fun initViewPager() {
        with(binding) {
            vpSlider.apply {
                adapter = sliderAdapter
                addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {}

                    override fun onPageSelected(position: Int) {
                        TransitionManager.beginDelayedTransition(container)
                        when(position) {
                            2 -> {
                                lifecycleScope.launch {
                                    viewModel.setFirstInstall(true)
                                }
                                btnNext.showView()
                            }
                            else -> {
                                btnNext.removeView()
                            }
                        }
                    }
                    override fun onPageScrollStateChanged(state: Int) {}

                })
            }
            indicator.attachTo(
                viewPager = vpSlider
            )

            btnNext.setOnClickListenerWithDebounce {
                directionNavigation()
            }
        }
    }

    private fun directionNavigation() {
        startActivity(Intent(this, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
        finish()
    }

    private fun initInstance() {
        _binding = ActivitySliderBinding.inflate(layoutInflater)
        sliderAdapter = SliderAdapter.instance()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}