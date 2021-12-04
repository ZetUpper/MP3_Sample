package com.cookandroid.mp3

data class MusicEntity(
    @SerializedName("track")val track: String,
    @SerializedName("streamUrl")val streamUrl: String,
    @SerializedName("artist")val artist: String,
    @SerializedName("coverUrl")val coverUrl: String
)
