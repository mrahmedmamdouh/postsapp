package com.example.postsapp.model.repository

import com.example.postsapp.model.Comment
import com.example.postsapp.model.Post
import com.example.postsapp.requests.ApiInterface
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiInterface: ApiInterface,
) {

    fun getPostsObservable(): Observable<Post> {
        return apiInterface.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(Function<List<Post>, ObservableSource<Post>> {
                Timber.d("Post with id: ${it[0].id} has ${it.size} comments")
                return@Function Observable.fromIterable(it).subscribeOn(Schedulers.io())
            })
    }

    fun getComments(post: Post): Observable<Post> {
        return apiInterface.getComments()
            .subscribeOn(Schedulers.io())
            .map(Function<List<Comment>, Post> {
                val commonList: MutableList<Comment> = mutableListOf()
                it.map {
                    if (it.postId == post.id) commonList.add(it)
                }
                post.comment = commonList
                return@Function post
            })
    }

}