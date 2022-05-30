package com.github.ariefannur.modular.features.search.presentation

import android.os.Bundle
import android.util.Log
import com.github.ariefannur.modular.core.base.DataState
import com.github.ariefannur.modular.core.ui.BaseActivity
import com.github.ariefannur.modular.features.search.R
import com.github.ariefannur.modular.features.search.domain.SearchUser
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity: BaseActivity() {
    override fun layoutId(): Int = R.layout.layout_search

    @Inject
    lateinit var searchUser: SearchUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchUser("wang") {
            when(it) {
                is DataState.Loading -> Log.d("AF", "loadinggg")
                is DataState.Success -> Log.d("AF", "SUCCEESS ->    ${it.result}")
            }
        }

    }
}