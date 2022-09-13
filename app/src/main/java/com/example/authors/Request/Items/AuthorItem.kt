package com.example.delivery.Request.Items

import com.google.gson.annotations.SerializedName

object AlbumConstants {
    const val TOPLEVELID_KEY = "feed"
    const val GENRENAME_KEY = "Music"
}

class AuthorItem(
    @SerializedName("author")
    val authorName: String,
    @SerializedName("url")
    val imgUri: String
)