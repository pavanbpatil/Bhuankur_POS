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

class FruitsAdapter(
    private val list: List<Dashboard.Fruits>,
    private val onProductClicked: Dashboard.OnProductClicked
)
    : RecyclerView.Adapter<FruitsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FruitsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: FruitsViewHolder, position: Int) {
        val fruits: Dashboard.Fruits = list[position]
        holder.bind(fruits,onProductClicked)
    }

    override fun getItemCount(): Int = list.size

}

class FruitsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
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
        fruits: Dashboard.Fruits,
        onProductClicked: Dashboard.OnProductClicked
    ) {
        loadWithCoil(ivProductImage,fruits.image)
        tvProductName?.text = fruits.name
        tvProductPrice?.text = fruits.price
        llProduct.setOnClickListener {
            onProductClicked.onClicked(
                fruits.id,
                fruits.image,
                fruits.name,
                fruits.price
            )
        }
    }

    fun loadWithCoil(imageView: ImageView, imageUrl: String){
        imageView.load(imageUrl){
            placeholder(R.drawable.ic_vegetable)
            error(R.drawable.ic_error)
        }
    }


}