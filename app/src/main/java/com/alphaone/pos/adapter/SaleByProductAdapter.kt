package com.alphaone.pos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.alphaone.pos.R
import com.alphaone.pos.fragment.Dashboard
import com.alphaone.pos.fragment.Sale
import com.alphaone.pos.fragment.UpdateCrates

class SaleByProductAdapter(
    private val list: List<Sale.SaleByProductItems>) : RecyclerView.Adapter<SaleByProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleByProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SaleByProductViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SaleByProductViewHolder, position: Int) {
        val saleByProductItems: Sale.SaleByProductItems = list[position]
        holder.bind(saleByProductItems, position)

    }

    override fun getItemCount(): Int = list.size


}

class SaleByProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_sale_by_product, parent, false)) {
    private var tvSrno: TextView? = null
    private var tvProductName: TextView? = null
    private var tvTotalSold: TextView? = null
    private var tvSoldAmount: TextView? = null

    init {
        tvSrno = itemView.findViewById(R.id.tvSrno)
        tvProductName = itemView.findViewById(R.id.tvProductName)
        tvTotalSold = itemView.findViewById(R.id.tvTotalSold)
        tvSoldAmount = itemView.findViewById(R.id.tvSoldAmount)

    }

    fun bind(
        saleByProductItems: Sale.SaleByProductItems,
        position: Int
    ) {
        tvSrno?.text = saleByProductItems.srno
        tvProductName?.text = saleByProductItems.product
        tvTotalSold?.text = saleByProductItems.sold
        tvSoldAmount?.text = saleByProductItems.amount
    }

}