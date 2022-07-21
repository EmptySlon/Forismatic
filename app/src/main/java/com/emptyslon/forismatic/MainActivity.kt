package com.emptyslon.forismatic

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.SyncStateContract.Helpers.update
import android.widget.ProgressBar
import android.widget.Toast
import com.emptyslon.forismatic.dataBase.Quote
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

        Thread {

            repeat(10) {
                Thread.sleep(1000)
                handler.post { updateCounter(it) }
            }
        }.start()


    }

    private fun updateCounter(count: Int) {
        binding.txCounter.text = count.toString()
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