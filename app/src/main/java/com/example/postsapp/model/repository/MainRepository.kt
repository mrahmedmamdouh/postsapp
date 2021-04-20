package com.example.postsapp.model.repository

import com.example.postsapp.model.Comment
import com.example.postsapp.model.Post
import com.example.postsapp.requests.ApiInterface
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiInterface: ApiInterface,
) {

    fun getPostsObservable(): Observable<List<Post>> {
        return apiInterface.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(Function<List<Post>, ObservableSource<List<Post>>> {
                return@Function getComments(it)
            })
    }

    private fun getComments(posts: List<Post>): Observable<List<Post>> {
        return apiInterface.getComments()
            .subscribeOn(Schedulers.io())
            .map(Function<List<Comment>, List<Post>> {
                posts.map { post ->
                    val commonList: MutableList<Comment> = mutableListOf()
                    it.map {
                        if (it.postId == post.id) commonList.add(it)
                    }
                    post.comment =  commonList
                }
                return@Function posts
            })
    }

}