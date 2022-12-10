package com.example.perqaraassessment.presentation.home.activity.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.perqaraassessment.R
import com.example.perqaraassessment.databinding.FragmentFavoriteBinding
import com.example.perqaraassessment.databinding.FragmentGameBinding

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding as FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initInstance(inflater, container)
        return binding.root
    }

    private fun initInstance(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
    }
}