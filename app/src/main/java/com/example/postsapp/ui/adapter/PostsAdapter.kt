package com.example.postsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapp.databinding.ListItemsBinding
import com.example.postsapp.model.Post

class PostsAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostsAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.VH {
        val binding =ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostsAdapter.VH, position: Int) {
        val currentItem = posts[position]
        holder.bind(currentItem)
    }

    inner class VH(private val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(post: Post){

            binding.apply {
                title.text = post.title
                body.text = post.body
                comments.text = post.comment.size.toString()
            }
        }
    }
}