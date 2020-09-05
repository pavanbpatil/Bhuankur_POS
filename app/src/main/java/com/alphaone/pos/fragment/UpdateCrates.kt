package com.alphaone.pos.fragment

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.alphaone.pos.R
import com.alphaone.pos.adapter.UpdateCratesAdapter
import kotlinx.android.synthetic.main.fragment_update_crates.*

class UpdateCrates : Fragment() {

    data class UpdateCratesItems(
        val srno: String,
        val rfid: String,
        val product: String,
        val total: String,
        val sold: String,
        val balance: String
    )

    private val updateCratesItemsList = arrayListOf<UpdateCratesItems>(
        UpdateCratesItems("1","1111", "Potato", "50", "10","400"),
        UpdateCratesItems("2","2222", "Onion", "20", "5","15"),
        UpdateCratesItems("3","3333", "Cabbage", "30", "30","00"),
        UpdateCratesItems("4","4444", "Carrot", "50", "20","30")

    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_crates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartItemRemoved: MediaPlayer = MediaPlayer.create(context, R.raw.cart_item_removed)
        rvCrateList.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = UpdateCratesAdapter(updateCratesItemsList, object : OnCrateListAction{
                override fun onCancelClicked(position: Int) {
                    super.onCancelClicked(position)
                    cartItemRemoved.start()
                    updateCratesItemsList.removeAt(position)
                    adapter?.notifyDataSetChanged()
                }
            })
        }
    }
    interface OnCrateListAction {
        fun onCancelClicked(position: Int) {
        }

    }
}
