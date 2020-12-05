package eif.viko.lt.focustimer.Adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import eif.viko.lt.focustimer.Models.Item
import eif.viko.lt.focustimer.R
import kotlinx.android.synthetic.main.list_item_layout.view.*

class ItemListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Item, ItemListAdapter.ItemViewHolder>(ItemDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout, parent, false), interaction
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Item>) {
        submitList(data.toMutableList())
    }

    inner class ItemViewHolder(
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

        fun bind(item: Item) = with(itemView) {
            item_title.text = item.title
            item_description.text = item.description
            item_dueDate.text = item.dueDate
        }
    }

    interface Interaction {

    }

    private class ItemDC : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean = oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ) = oldItem == newItem
    }
}