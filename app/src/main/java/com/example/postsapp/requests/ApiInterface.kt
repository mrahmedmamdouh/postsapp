package com.example.postsapp.requests

import com.example.postsapp.model.Comment
import com.example.postsapp.model.Post
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    suspend fun getPosts() : List<Post>

    @GET("comments")
    suspend fun getComments() : List<Comment>

}