package com.github.ariefannur.modular.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T: ViewBinding> : AppCompatActivity(){

    val viewBinding: T by lazy { bindLayout() }
    abstract fun bindLayout(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            setContentView(this.root)
            onBind(this)
        }
    }

    open fun onBind(binding: T) {

    }



}