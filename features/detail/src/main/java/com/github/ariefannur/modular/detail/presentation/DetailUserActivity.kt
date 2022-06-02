package com.github.ariefannur.modular.detail.presentation

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ariefannur.modular.core.ui.BaseActivity
import com.github.ariefannur.modular.detail.R
import com.github.ariefannur.modular.detail.databinding.DetailUserActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailUserActivity: BaseActivity<DetailUserActivityBinding>() {

    private val viewModel: DetailUserViewModel by viewModels()
    private val headerAdapter by lazy { AdapterHeader() }
    private val listAdapter by lazy { AdapterListDetail(listOf(), "") }
    private val concatAdapter by lazy { ConcatAdapter(headerAdapter, listAdapter) }

    private val username by lazy { intent.getStringExtra("username") }
    override fun bindLayout(): DetailUserActivityBinding {
        return DetailUserActivityBinding.inflate(layoutInflater, null, false)
    }

    override fun onBind(binding: DetailUserActivityBinding) {
        super.onBind(binding)
        setUplist()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailUser.collect {
                    when (it) {
                        is UserDetailState.Success -> {
                            headerAdapter.apply {
                                data = it.detail
                                notifyDataSetChanged()
                            }
                            concatAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listRepo.collect {
                    when (it) {
                        is UserDetailRepoState.Success -> {
                            listAdapter.apply {
                                list = it.data
                                avatar = viewModel.avatar
                                notifyDataSetChanged()
                            }
                            concatAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }

    }

    private fun setUplist() {
        val decorator = DividerItemDecoration(this@DetailUserActivity, DividerItemDecoration.VERTICAL)
            decorator.apply {
                ContextCompat.getDrawable(this@DetailUserActivity, R.color.divider)?.let {
                    setDrawable(it)
                }
            }
       viewBinding.rvDetail.apply {
           layoutManager = LinearLayoutManager(this@DetailUserActivity)
           adapter =  concatAdapter
           addItemDecoration(
               decorator
           )
       }
        username?.let {
            viewModel.getDetailUser(it)
        }
    }
}