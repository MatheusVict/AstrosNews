package dev.matheusvict.astrosnews.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.matheusvict.astrosnews.data.model.Post
import dev.matheusvict.astrosnews.data.respository.MockAPIService
import dev.matheusvict.astrosnews.data.respository.PostRepository
import dev.matheusvict.astrosnews.data.respository.PostRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PostRepository): ViewModel() {


    private val _listPost = MutableLiveData<List<Post>>()
    val listPost
        get() = _listPost

    init {
        fetchPosts()
    }

    /**
     * Esse método coleta o fluxo do repositorio e atribui
     * o seu valor ao campo _listPost
     */
    private fun fetchPosts() {
        viewModelScope.launch {
            repository.listPosts().collect {
                _listPost.value = it
            }
        }
    }

}
