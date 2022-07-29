package com.emptyslon.forismatic.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emptyslon.forismatic.dataBase.Quote
import com.emptyslon.forismatic.databinding.ItemQuoteBinding

class QuotesAdapter(val quotesList: List<Quote>) :
    RecyclerView.Adapter<QuotesAdapter.QuotesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesAdapter.QuotesHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuotesHolder(binding)
    }

    override fun onBindViewHolder(holder: QuotesAdapter.QuotesHolder, position: Int) {
        val quote = quotesList[position]
        holder.bind(quote)
    }

    override fun getItemCount(): Int = quotesList.size

    class QuotesHolder(val binding: ItemQuoteBinding):  RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: Quote) {
            binding.quoteTextItem.text = quote.q
            binding.quoteAuthorItem.text = quote.a
        }
    }

}