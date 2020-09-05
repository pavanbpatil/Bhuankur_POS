package com.alphaone.pos.adapter

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.alphaone.pos.R
import com.alphaone.pos.fragment.Dashboard

class CartAdapter(
    private val list: List<Dashboard.CartItems>,
    private val onCartItemAction: Dashboard.OnCartItemAction
) : RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CartViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItems: Dashboard.CartItems = list[position]
        holder.bind(cartItems, onCartItemAction, position)

    }

    override fun getItemCount(): Int = list.size


}

class CartViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_cart, parent, false)) {
    private var tvProduct: TextView? = null
    private var tvQuantity: TextView? = null
    private var tvRate: TextView? = null
    private var tvTotal: TextView? = null
    private var ivCancel: ImageView

    init {
        tvProduct = itemView.findViewById(R.id.tvProduct)
        tvQuantity = itemView.findViewById(R.id.tvQuantity)
        tvRate = itemView.findViewById(R.id.tvRate)
        tvTotal = itemView.findViewById(R.id.tvTotal)
        ivCancel = itemView.findViewById(R.id.ivCancel)

    }

    fun bind(
        cartItems: Dashboard.CartItems,
        onCartItemAction: Dashboard.OnCartItemAction,
        position: Int
    ) {
        tvProduct?.text = cartItems.product
        tvQuantity?.text = cartItems.quantity
        tvRate?.text = cartItems.rate
        tvTotal?.text = cartItems.total
        ivCancel.setOnClickListener {
            onCartItemAction.onCartItemcancelled(position)
        }
    }

    fun loadWithCoil(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            placeholder(R.drawable.ic_vegetable)
            error(R.drawable.ic_error)
        }
    }
}