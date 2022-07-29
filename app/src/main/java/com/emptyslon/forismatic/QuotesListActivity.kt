package com.emptyslon.forismatic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.emptyslon.forismatic.databinding.ActivityQuotesListBinding

class QuotesListActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    lateinit var binding: ActivityQuotesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    Thread {

        repeat(10) {

            handler.post { updateCounter(it) }
        }
    }.start()




}