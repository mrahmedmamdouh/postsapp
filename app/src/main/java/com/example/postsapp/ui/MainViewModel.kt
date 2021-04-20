package com.example.postsapp.ui

import androidx.lifecycle.*
import com.example.postsapp.model.Comment
import com.example.postsapp.model.Post
import com.example.postsapp.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val posts = MutableLiveData<List<Post>>()

    init {
        getPosts()
    }

    private fun getPosts() {

        viewModelScope.launch {

            val getPostsFromApi = async { repository.getPostsObservable() }
            val getCommentsFromApi = async { repository.getComments() }

            processData(getPostsFromApi.await(), getCommentsFromApi.await())
        }

    }

    private fun processData(
        postsFlow: List<Post>,
        commentsFlow: List<Comment>
    ) {
        postsFlow.map { post ->
            val commonList: MutableList<Comment> = mutableListOf()
            commentsFlow.map { comment ->
                if (comment.postId == post.id) commonList.add(comment)
            }
            post.comment = commonList
        }
        posts.postValue(postsFlow)
    }


    fun getPostsObservable(): LiveData<List<Post>> {
        return posts
    }
}