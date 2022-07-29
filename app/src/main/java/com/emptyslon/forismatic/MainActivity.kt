package com.emptyslon.forismatic

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.emptyslon.forismatic.dataBase.QuotesList
import com.emptyslon.forismatic.databinding.ActivityMainBinding
import com.emptyslon.forismatic.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        getRandomQuote()
        binding.btGetRandomQuote.setOnClickListener {
            getRandomQuote()
        }
        binding.btQuotesList.setOnClickListener {
            val intent = Intent(this, QuotesListActivity::class.java)
            startActivity(intent)
        }




    }

    private fun updateCounter(count: Int) {
        binding.txCounter.text = count.toString()
    }

    private fun getRandomQuote() {
        val retrofitData = RetrofitClient.retrofit.getData()
        retrofitData.enqueue(object : Callback<QuotesList?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<QuotesList?>,
                response: Response<QuotesList?>
            ) {
                val quote = response.body()!!.first()
                binding.quoteAuthor.text = quote.a
                binding.quoteText.text = quote.q
            }

            override fun onFailure(call: Call<QuotesList?>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Failed to establish connection",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}