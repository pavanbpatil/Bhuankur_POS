package com.alphaone.pos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.alphaone.pos.R
import com.alphaone.pos.fragment.Dashboard

class RecentProductsAdapter(
    private val list: List<Dashboard.RecentProducts>,
    private val onProductClicked: Dashboard.OnProductClicked
) : RecyclerView.Adapter<RecentProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecentProductViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: RecentProductViewHolder, position: Int) {
        val recentProducts: Dashboard.RecentProducts = list[position]
        holder.bind(recentProducts, onProductClicked)
    }

    override fun getItemCount(): Int = list.size


}

class RecentProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_product, parent, false)) {
    private var llProduct: LinearLayout
    private var ivProductImage: ImageView
    private var tvProductName: TextView? = null
    private var tvProductPrice: TextView? = null

    init {
        llProduct = itemView.findViewById(R.id.llProduct)
        ivProductImage = itemView.findViewById(R.id.ivProductImage)
        tvProductName = itemView.findViewById(R.id.tvProductName)
        tvProductPrice = itemView.findViewById(R.id.tvProductPrice)

    }

    fun bind(
        recentProducts: Dashboard.RecentProducts,
        onProductClicked: Dashboard.OnProductClicked
    ) {
        loadWithCoil(ivProductImage, recentProducts.image)
        tvProductName?.text = recentProducts.name
        tvProductPrice?.text = recentProducts.price

        llProduct.setOnClickListener {
            onProductClicked.onClicked(
                recentProducts.id,
                recentProducts.image,
                recentProducts.name,
                recentProducts.price
            )
        }
    }

    fun loadWithCoil(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            placeholder(R.drawable.ic_vegetable)
            error(R.drawable.ic_error)
        }
    }

}