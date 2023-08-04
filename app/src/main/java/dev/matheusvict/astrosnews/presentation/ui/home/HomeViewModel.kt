package dev.matheusvict.astrosnews.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.matheusvict.astrosnews.R
import dev.matheusvict.astrosnews.core.RemoteException
import dev.matheusvict.astrosnews.core.State
import dev.matheusvict.astrosnews.data.SpaceFlightNewsCategory
import dev.matheusvict.astrosnews.data.model.Post
import dev.matheusvict.astrosnews.domain.GetLatestPostUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(private val getLatestPostUseCase: GetLatestPostUseCase) : ViewModel() {


    /*
    * Controla a visibilidade da progressbar
    * */
    private val _progressBarVisible = MutableLiveData<Boolean>(false)
    val progressBarVisible: LiveData<Boolean> get() = _progressBarVisible

    fun showProgressBar() {
        _progressBarVisible.value = true
    }

    fun hideProgressBar() {
        _progressBarVisible.value = false
    }


    private val _snackBar = MutableLiveData<String?>(null)
    val snackbar: LiveData<String?> get() = _snackBar

    private val _category = MutableLiveData<SpaceFlightNewsCategory>().apply {
        value = SpaceFlightNewsCategory.ARTICLES
    }

    val category: LiveData<SpaceFlightNewsCategory> get() = _category

    fun onSnackBarShown() {
        _snackBar.value = null
    }

    private val _listPost = MutableLiveData<State<List<Post>>>()
    val listPost: LiveData<State<List<Post>>>
        get() = _listPost

    init {
        fetchLatest(_category.value ?: SpaceFlightNewsCategory.ARTICLES)
    }

    fun fetchLatest(category: SpaceFlightNewsCategory) {
        fetchPosts(category.value)
/*
        _category.value = category
*/
    }

    /**
     * Esse método coleta o fluxo do repositorio e atribui
     * o seu valor ao campo _listPost
     */
    private fun fetchPosts(query: String) {
        viewModelScope.launch {
            getLatestPostUseCase(query)
                .onStart {
                    _listPost.postValue(State.Loading)
                    delay(800)
                }
                .catch {
                    val exception = RemoteException("Unable to connect to SpaceFlightNews API")
                    _listPost.postValue(State.Error(exception))
                    _snackBar.value = exception.message
                }
                .collect { listPost ->
                    _listPost.postValue(State.Success(listPost))
                    _category.value = enumValueOf<SpaceFlightNewsCategory>(query.uppercase())
                }
        }
    }

    val helloText = Transformations.map(listPost) { state ->
        when (state) {
            State.Loading -> {
                " \uD83D\uDE80 Loading latest news..."
            }

            is State.Error -> {
                "Houston, we've had a problem"
            }

            else -> {
                ""
            }
        }
    }

}
