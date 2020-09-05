package com.alphaone.pos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.alphaone.pos.R
import com.alphaone.pos.fragment.Dashboard
import com.alphaone.pos.fragment.UpdateCrates

class UpdateCratesAdapter(
    private val list: List<UpdateCrates.UpdateCratesItems>,
    private val onCrateListAction: UpdateCrates.OnCrateListAction
) : RecyclerView.Adapter<UpdateCratesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpdateCratesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UpdateCratesViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: UpdateCratesViewHolder, position: Int) {
        val updateCratesItems: UpdateCrates.UpdateCratesItems = list[position]
        holder.bind(updateCratesItems, onCrateListAction, position)

    }

    override fun getItemCount(): Int = list.size


}

class UpdateCratesViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_update_crates, parent, false)) {
    private var tvSrno: TextView? = null
    private var tvRfid: TextView? = null
    private var tvProduct: TextView? = null
    private var tvTotal: TextView? = null
    private var tvSold: TextView? = null
    private var tvBalance: TextView? = null
    private var ivDeleteRecord: ImageView

    init {
        tvSrno = itemView.findViewById(R.id.tvSrno)
        tvRfid = itemView.findViewById(R.id.tvRfid)
        tvProduct = itemView.findViewById(R.id.tvProduct)
        tvTotal = itemView.findViewById(R.id.tvTotal)
        tvSold = itemView.findViewById(R.id.tvSold)
        tvBalance = itemView.findViewById(R.id.tvBalance)
        ivDeleteRecord = itemView.findViewById(R.id.ivDeleteRecord)

    }

    fun bind(
        updateCratesItems: UpdateCrates.UpdateCratesItems,
        onCrateListAction: UpdateCrates.OnCrateListAction,
        position: Int
    ) {
        tvSrno?.text = updateCratesItems.srno
        tvRfid?.text = updateCratesItems.rfid
        tvProduct?.text = updateCratesItems.product
        tvTotal?.text = updateCratesItems.total
        tvSold?.text = updateCratesItems.sold
        tvBalance?.text = updateCratesItems.balance
        ivDeleteRecord.setOnClickListener {
            onCrateListAction.onCancelClicked(position)
        }
    }

}