package com.alphaone.pos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alphaone.pos.R
import com.alphaone.pos.fragment.PendingCart
import com.alphaone.pos.fragment.Stock

class StockAdapter(
    private val list: List<Stock.StockItems>
) : RecyclerView.Adapter<StockAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StockAdapterViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: StockAdapterViewHolder, position: Int) {
        val cartItems: Stock.StockItems = list[position]
        holder.bind(cartItems, position)

    }

    override fun getItemCount(): Int = list.size


}

class StockAdapterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_stock_list, parent, false)) {
    private var tvSrno: TextView? = null
    private var tvProductName: TextView? = null
    private var tvInward: TextView? = null
    private var tvSold: TextView? = null
    private var tvBalance: TextView? = null

    init {
        tvSrno = itemView.findViewById(R.id.tvSrno)
        tvProductName = itemView.findViewById(R.id.tvProductName)
        tvInward = itemView.findViewById(R.id.tvInward)
        tvSold = itemView.findViewById(R.id.tvSold)
        tvBalance = itemView.findViewById(R.id.tvBalance)
    }

    fun bind(
        cartItems: Stock.StockItems,
        position: Int
    ) {
        tvSrno?.text = cartItems.srno
        tvProductName?.text = cartItems.product_name
        tvInward?.text = cartItems.inward
        tvSold?.text = cartItems.sold
        tvBalance?.text = cartItems.balance
    }

}