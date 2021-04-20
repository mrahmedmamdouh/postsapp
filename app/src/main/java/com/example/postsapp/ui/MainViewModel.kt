package com.example.postsapp.ui

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.example.postsapp.model.Post
import com.example.postsapp.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val posts = MutableLiveData<List<Post>>()

    init {
        getPosts()
    }
    @SuppressLint("CheckResult")
    fun getPosts() {
        repository.getPostsObservable()
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                posts.postValue(it)
            }, {
                Timber.e(it)
            }
            )
    }


    fun getPostsObservable() : LiveData<List<Post>>{
        return posts
    }
}