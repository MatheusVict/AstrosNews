package dev.matheusvict.astrosnews.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dev.matheusvict.astrosnews.core.State
import dev.matheusvict.astrosnews.data.model.Post
import dev.matheusvict.astrosnews.databinding.FragmentHomeBinding
import dev.matheusvict.astrosnews.presentation.adapter.PostListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        initBinding()
        initSnackBar()
        initRecyclerView()
        return binding.root
    }

    private fun initSnackBar() {
        viewModel.snackbar.observe(viewLifecycleOwner) {
            it?.let { errorMessage ->
                Snackbar.make(
                    binding.root,
                    errorMessage,
                    Snackbar.LENGTH_LONG
                ).show()
                viewModel.onSnackBarShown()
            }
        }
    }

    private fun initRecyclerView() {

        val adapter = PostListAdapter()
        binding.homeRv.adapter = adapter

        viewModel.listPost.observe(viewLifecycleOwner) { state ->
            when (state) {
                State.Loading -> {
                    viewModel.showProgressBar()
                }

                is State.Error -> {
                    viewModel.hideProgressBar()
                }

                is State.Success -> {
                    viewModel.hideProgressBar()
                    adapter.submitList(state.result)
                }
            }
        }


    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    companion object {
        fun newInstance() = HomeFragment()
    }


}