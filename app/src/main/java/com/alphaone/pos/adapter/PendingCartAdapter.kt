package com.alphaone.pos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alphaone.pos.R
import com.alphaone.pos.fragment.PendingCart

class PendingCartAdapter(
    private val list: List<PendingCart.PendingCartItems>,
    private val onCartItemAction: PendingCart.OnCartItemAction
) : RecyclerView.Adapter<PendingCartAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PendingCartAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PendingCartAdapterViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PendingCartAdapterViewHolder, position: Int) {
        val cartItems: PendingCart.PendingCartItems = list[position]
        holder.bind(cartItems, onCartItemAction, position)

    }

    override fun getItemCount(): Int = list.size


}

class PendingCartAdapterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_pending_cart, parent, false)) {
    private var tvSrno: TextView? = null
    private var tvItem: TextView? = null
    private var tvDateTime: TextView? = null
    private var tvWeight: TextView? = null
    private var tvQuantity: TextView? = null
    private var tvAmount: TextView? = null
    private var btClose: Button
    private var btOpen: Button

    init {
        tvSrno = itemView.findViewById(R.id.tvSrno)
        tvItem = itemView.findViewById(R.id.tvItem)
        tvDateTime = itemView.findViewById(R.id.tvDateTime)
        tvWeight = itemView.findViewById(R.id.tvWeight)
        tvQuantity = itemView.findViewById(R.id.tvQuantity)
        tvAmount = itemView.findViewById(R.id.tvAmount)
        btClose = itemView.findViewById(R.id.btClose)
        btOpen = itemView.findViewById(R.id.btOpen)
    }

    fun bind(
        cartItems: PendingCart.PendingCartItems,
        onCartItemAction: PendingCart.OnCartItemAction,
        position: Int
    ) {
        tvSrno?.text = cartItems.srno
        tvItem?.text = cartItems.item
        tvDateTime?.text = cartItems.datetime
        tvWeight?.text = cartItems.weight
        tvQuantity?.text = cartItems.quantity
        tvAmount?.text = cartItems.amount
        btClose.setOnClickListener {
            onCartItemAction.onCloseClicked(position)
        }
        btOpen.setOnClickListener {
            onCartItemAction.onOpenClicked(position)
        }
    }

}