package com.example.postsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.postsapp.model.repository.MainRepository
import com.example.postsapp.requests.ApiInterface
import com.example.postsapp.ui.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import timber.log.Timber


@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Rule @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()
    @Mock
    lateinit var api : ApiInterface
    private lateinit var mainRepository: MainRepository
    private lateinit var mainViewModel: MainViewModel
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainRepository = MainRepository(api)
        mainViewModel = MainViewModel(mainRepository)
    }


    @Test
    fun testApi() {
        mainViewModel.getPostsObservable().observeForever {}
        Assert.assertNotNull(mainViewModel.getPostsObservable().value)
        Timber.d("${mainViewModel.getPostsObservable().value}")
    }

}