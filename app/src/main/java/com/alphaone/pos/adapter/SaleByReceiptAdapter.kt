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

class SaleByReceiptAdapter(
    private val list: List<Sale.SaleByReceiptItems>,
    private val onItemListAction: Sale.OnItemListAction
) : RecyclerView.Adapter<SaleByReceiptViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleByReceiptViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SaleByReceiptViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SaleByReceiptViewHolder, position: Int) {
        val saleByReceiptItems: Sale.SaleByReceiptItems = list[position]
        holder.bind(saleByReceiptItems, onItemListAction, position)

    }

    override fun getItemCount(): Int = list.size


}

class SaleByReceiptViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_sale_by_receipt, parent, false)) {
    private var tvSrno: TextView? = null
    private var tvReceiptno: TextView? = null
    private var tvTotalSold: TextView? = null
    private var tvSoldAmount: TextView? = null
    private var ivView: ImageView

    init {
        tvSrno = itemView.findViewById(R.id.tvSrno)
        tvReceiptno = itemView.findViewById(R.id.tvReceiptno)
        tvTotalSold = itemView.findViewById(R.id.tvTotalSold)
        tvSoldAmount = itemView.findViewById(R.id.tvSoldAmount)
        ivView = itemView.findViewById(R.id.ivView)

    }

    fun bind(
        saleByReceiptItems: Sale.SaleByReceiptItems,
        onItemListAction: Sale.OnItemListAction,
        position: Int
    ) {
        tvSrno?.text = saleByReceiptItems.srno
        tvReceiptno?.text = saleByReceiptItems.receipt
        tvTotalSold?.text = saleByReceiptItems.sold
        tvSoldAmount?.text = saleByReceiptItems.amount
        ivView.setOnClickListener {
            onItemListAction.onViewReceiptClicked(position)
        }
    }

}