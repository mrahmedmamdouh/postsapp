package com.example.postsapp.requests

import com.example.postsapp.model.Comment
import com.example.postsapp.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("posts")
    fun getPosts() : Observable<List<Post>>

    @GET("comments")
    fun getComments() : Observable<List<Comment>>

}