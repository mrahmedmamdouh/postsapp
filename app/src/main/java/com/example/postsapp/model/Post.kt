package com.example.postsapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Post(
    @SerializedName("userID")
    @Expose
    val userID : Int,
    @SerializedName("id")
    @Expose
    val id : Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("body")
    @Expose
    val body: String,

    var comment: List<Comment>
)