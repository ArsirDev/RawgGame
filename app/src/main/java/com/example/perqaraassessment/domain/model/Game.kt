package com.example.perqaraassessment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Game(

    @field:SerializedName("added")
    val added: Int,

    @field:SerializedName("suggestions_count")
    val suggestionsCount: Int,

    @field:SerializedName("rating")
    val rating: Int,

    @field:SerializedName("metacritic")
    val metacritic: Int,

    @field:SerializedName("playtime")
    val playtime: Int,

    @field:SerializedName("background_image")
    val backgroundImage: String,

    @field:SerializedName("tba")
    val tba: Boolean,

    @field:SerializedName("rating_top")
    val ratingTop: Int,

    @field:SerializedName("reviews_text_count")
    val reviewsTextCount: String,

    @field:SerializedName("name")
    val name: String,

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("ratings_count")
    val ratingsCount: Int,

    @field:SerializedName("updated")
    val updated: String,

    @field:SerializedName("slug")
    val slug: String,

    @field:SerializedName("released")
    val released: String,

    val isFavorite: Boolean = false
)
