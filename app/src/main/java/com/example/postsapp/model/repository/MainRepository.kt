package com.example.postsapp.model.repository

import com.example.postsapp.model.Comment
import com.example.postsapp.model.Post
import com.example.postsapp.requests.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiInterface: ApiInterface,
) {

    suspend fun getPostsObservable(): List<Post> {
        return apiInterface.getPosts()
    }

    suspend fun getComments(): List<Comment> {
        return apiInterface.getComments()

    }

}