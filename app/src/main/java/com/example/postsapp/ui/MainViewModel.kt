package com.example.postsapp.ui

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.example.postsapp.model.Post
import com.example.postsapp.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val posts = MutableLiveData<List<Post>>()

    @SuppressLint("CheckResult")
    fun getPosts(): LiveData<List<Post>> {
        repository.getPostsObservable()
            .subscribeOn(Schedulers.io())
            .flatMap(Function<Post, ObservableSource<Post>> {
                return@Function repository.getComments(post = it)
            })
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                posts.value = listOf(it)
                Timber.d("Post with id: ${it.id} has ${it.comment.size} comments")
            }, {
                Timber.e(it)
            }
            )
        return posts
    }
}