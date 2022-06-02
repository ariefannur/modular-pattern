package com.github.ariefannur.modular.features.search.presentation

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ariefannur.modular.core.extension.gone
import com.github.ariefannur.modular.core.extension.textChanges
import com.github.ariefannur.modular.core.extension.visible
import com.github.ariefannur.modular.core.ui.BaseActivity
import com.github.ariefannur.modular.features.search.R
import com.github.ariefannur.modular.features.search.databinding.LayoutSearchBinding
import com.github.ariefannur.modular.features.search.domain.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity: BaseActivity<LayoutSearchBinding>() {

    override fun bindLayout(): LayoutSearchBinding = LayoutSearchBinding.inflate(layoutInflater)

    private val searchViewModel: SearchViewModel by viewModels()
    private val loadingDialog by lazy {  LoadingDialog(this) }
    override fun onBind(binding: LayoutSearchBinding) {
        super.onBind(binding)
        binding.etSearch.textChanges().debounce(1000).onEach {
           if(!it.isNullOrEmpty()) {
               searchViewModel.doSearchUsers(it.toString())
           } else {
               binding.rvSearch.adapter = SearchAdapter(listOf())
               enableEmpty(true)
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

    private fun enableEmpty(enable: Boolean) {
        val emptyBinding = viewBinding.empty
        if (enable)
            emptyBinding.rlEmpty.visible()
        else
            emptyBinding.rlEmpty.gone()
    }

    private fun setUpList() {
        enableEmpty(true)
        val decorator = DividerItemDecoration(this@SearchActivity, DividerItemDecoration.VERTICAL)
        decorator.apply {
            ContextCompat.getDrawable(this@SearchActivity, R.color.divider)?.let {
                setDrawable(it)
            }
        }
        with(viewBinding.rvSearch) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(
                decorator
            )
        }
    }

    private fun render(it: SearchState) {
        enableEmpty(false)
        when(it) {
            is SearchState.Loading -> {
                showLoading(true)
            }
            is SearchState.Success -> arrangeList(it.list)
            is SearchState.Failed -> showError(it.message)
        }
    }

    private fun arrangeList(list: List<User>) {
        showLoading(false)
        viewBinding.rvSearch.adapter = SearchAdapter(list)

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading)
            loadingDialog.show()
        else
            loadingDialog.dismiss()
    }

    private fun showError(message: String) {
        showLoading(false)
    }

}