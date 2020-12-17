package eif.viko.lt.focustimer.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import eif.viko.lt.focustimer.Models.Quote
import eif.viko.lt.focustimer.R
import kotlinx.android.synthetic.main.quote_item_layout.view.*

class QuotesListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Quote, QuotesListAdapter.QuoteViewHolder>(QuoteDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuoteViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.quote_item_layout, parent, false), interaction
    )

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Quote>) {
        submitList(data.toMutableList())
    }

    inner class QuoteViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)
        }

        fun bind(item: Quote) = with(itemView) {
            quote_text.text = item.quote_text
            author.text = item.author
        }
    }

    interface Interaction {

    }

    private class QuoteDC : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(
            oldItem: Quote,
            newItem: Quote
        ) = oldItem.quote_text == newItem.quote_text

        override fun areContentsTheSame(
            oldItem: Quote,
            newItem: Quote
        ) = oldItem == newItem
    }
}