package com.alphaone.pos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alphaone.pos.R
import com.alphaone.pos.activity.CheckoutAndPrint

class PreviewListAdapter(
    private val list: List<CheckoutAndPrint.CartItems>
) : RecyclerView.Adapter<PreviewListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PreviewListViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PreviewListViewHolder, position: Int) {
        val cartItems: CheckoutAndPrint.CartItems = list[position]
        holder.bind(cartItems, position)

    }

    override fun getItemCount(): Int = list.size


}

class PreviewListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_preview_list, parent, false)) {
    private var tvItem: TextView? = null
    private var tvQty: TextView? = null
    private var tvRate: TextView? = null
    private var tvTotal: TextView? = null

    init {
        tvItem = itemView.findViewById(R.id.tvItem)
        tvQty = itemView.findViewById(R.id.tvQty)
        tvRate = itemView.findViewById(R.id.tvRate)
        tvTotal = itemView.findViewById(R.id.tvTotal)

    }

    fun bind(
        cartItems: CheckoutAndPrint.CartItems,
        position: Int
    ) {
        tvItem?.text = cartItems.product
        tvQty?.text = cartItems.quantity
        tvRate?.text = cartItems.rate
        tvTotal?.text = cartItems.total
    }

}