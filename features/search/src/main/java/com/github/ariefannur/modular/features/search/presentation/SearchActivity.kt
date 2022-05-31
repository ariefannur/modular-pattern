package com.github.ariefannur.modular.features.search.presentation

import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ariefannur.modular.core.extension.textChanges
import com.github.ariefannur.modular.core.ui.BaseActivity
import com.github.ariefannur.modular.features.search.databinding.LayoutSearchBinding
import com.github.ariefannur.modular.features.search.domain.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity: BaseActivity<LayoutSearchBinding>() {

    override fun bindLayout(): LayoutSearchBinding = LayoutSearchBinding.inflate(layoutInflater)

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onBind(binding: LayoutSearchBinding) {
        super.onBind(binding)
        binding.etSearch.textChanges().debounce(1000).onEach {
           if(!it.isNullOrEmpty()) {
               searchViewModel.doSearchUsers(it.toString())
           } else {
               binding.rvSearch.adapter = SearchAdapter(listOf())
           }
        }.launchIn(lifecycleScope)

        setUpList()

        lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.STARTED) {
               searchViewModel.listUser.collect {
                   render(it)
               }
           }
       }
    }

    private fun setUpList() {
        with(viewBinding.rvSearch) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(
                DividerItemDecoration(this@SearchActivity, DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun render(it: SearchState) {
        when(it) {
            is SearchState.Loading -> showLoading(true)
            is SearchState.Success -> arrangeList(it.list)
            is SearchState.Failed -> showError(it.message)
        }
    }

    private fun arrangeList(list: List<User>) {
        showLoading(false)
        viewBinding.rvSearch.adapter = SearchAdapter(list)

    }

    private fun showLoading(isLoading: Boolean) {

    }

    private fun showError(message: String) {
        showLoading(false)
    }

}