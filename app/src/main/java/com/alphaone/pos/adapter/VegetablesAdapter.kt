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

class VegetablesAdapter(
    private val list: List<Dashboard.Vegetables>,
    private val onProductClicked: Dashboard.OnProductClicked
) :
    RecyclerView.Adapter<VegetablesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VegetablesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return VegetablesViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: VegetablesViewHolder, position: Int) {
        val vegetables: Dashboard.Vegetables = list[position]
        holder.bind(vegetables,onProductClicked)
    }

    override fun getItemCount(): Int = list.size

}

class VegetablesViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
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
        vegetables: Dashboard.Vegetables,
        onProductClicked: Dashboard.OnProductClicked
    ) {
        loadWithCoil(ivProductImage,vegetables.image)
        tvProductName?.text = vegetables.name
        tvProductPrice?.text = vegetables.price
        llProduct.setOnClickListener {
            onProductClicked.onClicked(
                vegetables.id,
                vegetables.image,
                vegetables.name,
                vegetables.price
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