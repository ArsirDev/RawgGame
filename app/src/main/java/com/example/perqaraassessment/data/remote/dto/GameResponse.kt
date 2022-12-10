package com.example.perqaraassessment.data.remote.dto

import androidx.room.PrimaryKey
import com.example.perqaraassessment.domain.model.Game
import com.google.gson.annotations.SerializedName

data class GameResponse(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("previous")
	val previous: String,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<Game>
) {
	fun getGames(): List<Game> {
		return results.map { game ->
			Game(
				added = game.added,
				suggestionsCount = game.suggestionsCount,
				rating = game.rating,
				metacritic = game.metacritic,
				playtime = game.playtime,
				backgroundImage = game.backgroundImage,
				tba = game.tba,
				ratingTop = game.ratingTop,
				reviewsTextCount = game.reviewsTextCount,
				name = game.name,
				id = game.id,
				ratingsCount = game.ratingsCount,
				updated = game.updated,
				slug = game.slug,
				released = game.released,
			)
		}
	}
}
