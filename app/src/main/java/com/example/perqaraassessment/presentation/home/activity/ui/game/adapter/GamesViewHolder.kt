package com.example.perqaraassessment.presentation.home.activity.ui.game.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.perqaraassessment.databinding.ItemGamesLayoutBinding
import com.example.perqaraassessment.domain.model.Game
import com.example.perqaraassessment.util.loadImage

class GamesViewHolder(
    val binding: ItemGamesLayoutBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(game: Game) {
        with(binding) {
            ivImage.loadImage(game.backgroundImage)
            tvTitle.text = game.name
            tvReleaseDate.text = String.format("Released date ${game.released}")
            tvRating.text = game.rating.toString()
        }
    }
}