package dev.matheusvict.astrosnews.presentation.adapter

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.google.android.material.appbar.MaterialToolbar
import dev.matheusvict.astrosnews.R
import dev.matheusvict.astrosnews.data.SpaceFlightNewsCategory

@BindingAdapter("toolbarTitle")
fun MaterialToolbar.setToolbarTitleFromCategory(category: LiveData<SpaceFlightNewsCategory>?) {
    category?.let {
        val stringResourse = when(it.value) {
            SpaceFlightNewsCategory.ARTICLES -> R.string.latest_news
            SpaceFlightNewsCategory.BLOGS -> R.string.latest_blogs
            SpaceFlightNewsCategory.REPORTS -> R.string.latest_reports
            else -> 0
        }

        this.title = context.getString(stringResourse)
    }

}