package com.emptyslon.forismatic

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.emptyslon.forismatic.dataBase.Quote
import com.emptyslon.forismatic.databinding.ActivityMainBinding
import com.emptyslon.forismatic.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        getRandomQuote()
        binding.btGetRandomQuote.setOnClickListener {
            getRandomQuote()
        }


    }

    private fun getRandomQuote() {
        val retrofitData = RetrofitClient.retrofit.getData()
        retrofitData.enqueue(object : Callback<Quote?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<Quote?>,
                response: Response<Quote?>
            ) {
                val quote = response.body()!!.first()
                binding.quoteAuthor.text = quote.a
                binding.quoteText.text = quote.q
            }

            override fun onFailure(call: Call<Quote?>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Failed to establish connection",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}