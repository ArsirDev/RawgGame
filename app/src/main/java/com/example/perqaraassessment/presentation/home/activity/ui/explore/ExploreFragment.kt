package com.example.perqaraassessment.presentation.home.activity.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.perqaraassessment.R
import com.example.perqaraassessment.databinding.FragmentExploreBinding
import com.example.perqaraassessment.databinding.FragmentFavoriteBinding


class ExploreFragment : Fragment() {
    private var _binding: FragmentExploreBinding? = null

    private val binding get() = _binding as FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initInstance(inflater, container)
        return binding.root
    }

    private fun initInstance(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
    }
}