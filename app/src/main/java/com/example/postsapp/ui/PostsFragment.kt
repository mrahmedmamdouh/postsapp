package com.example.postsapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsapp.R
import com.example.postsapp.databinding.FragmentPostsBinding
import com.example.postsapp.ui.adapter.PostsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(R.layout.fragment_posts) {

    private lateinit var binding: FragmentPostsBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostsBinding.bind(view)
        binding.lifecycleOwner = this

        binding.apply {
            list.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.getPostsObservable().observe(viewLifecycleOwner, Observer {
                val postAdapter = PostsAdapter(it)
                binding.list.adapter = postAdapter
        })
    }
}