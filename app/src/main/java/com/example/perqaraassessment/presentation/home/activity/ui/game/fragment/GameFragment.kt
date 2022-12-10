package com.example.perqaraassessment.presentation.home.activity.ui.game.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perqaraassessment.databinding.FragmentGameBinding
import com.example.perqaraassessment.presentation.home.activity.ui.game.adapter.GamesAdapter
import com.example.perqaraassessment.presentation.home.activity.ui.game.event.GameEvent
import com.example.perqaraassessment.presentation.home.activity.ui.game.viewmodel.GameViewModel
import com.example.perqaraassessment.util.GridMarginItemDecoration
import com.example.perqaraassessment.util.isLastVisible
import com.example.perqaraassessment.util.removeView
import com.example.perqaraassessment.util.showView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null

    private val binding get() = _binding as FragmentGameBinding

    private val viewModel: GameViewModel by inject()

    private lateinit var gamesAdapter: GamesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initInstance(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initPagination()
        initLaunch()
        initView()
    }

    private fun initView() {
        viewModel.onEvent(
            event = GameEvent.DefaultPage
        )
    }

    private fun initPagination() {
        binding.rvGame.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastVisible(recyclerView)) {
                    viewModel.onEvent(GameEvent.LoadMore)
                }
            }
        })
    }

    private fun initLaunch() {
        lifecycleScope.launchWhenStarted {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collectLatest { state ->
                        if (state.isLoading) {
                            binding.pbLoading.showView()
                        } else {
                            binding.pbLoading.removeView()
                        }

                        state.game.let { item ->
                            Log.e("TAG", "initLaunch: $item", )
                            gamesAdapter.differ.submitList(item)
                        }
                    }
                }
            }
        }
    }

    private fun initAdapter() {
        gamesAdapter.let { adapter ->
            binding.rvGame.apply {
                this.adapter = adapter
                layoutManager = GridLayoutManager(requireContext(), 2)
                overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                addItemDecoration(GridMarginItemDecoration(8))
                ViewCompat.setNestedScrollingEnabled(this, true)
            }
        }
    }

    private fun initInstance(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        gamesAdapter = GamesAdapter.instance()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
