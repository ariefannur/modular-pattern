package com.github.ariefannur.modular.pattern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val intent = Intent(
                this,
                Class.forName("com.github.ariefannur.modular.features.search.presentation.SearchActivity")
            )
            startActivity(intent)
        } catch (e: Exception) {
            Log.d("AF", "error intent ${e.message}")
        }
    }
}