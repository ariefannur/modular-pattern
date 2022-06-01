package com.github.ariefannur.modular.features.search.presentation

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.github.ariefannur.modular.features.search.R
import com.github.ariefannur.modular.features.search.databinding.LoadingDialogBinding

class LoadingDialog(context: Context) : Dialog(context, R.style.DialogCustom) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LoadingDialogBinding.inflate(layoutInflater, null, false)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view.root)
        bindView(view)
    }

    private fun bindView(binding: LoadingDialogBinding) {

    }
}