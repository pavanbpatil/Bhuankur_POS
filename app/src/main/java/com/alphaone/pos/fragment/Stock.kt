package com.alphaone.pos.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.alphaone.pos.R
import com.alphaone.pos.adapter.StockAdapter
import kotlinx.android.synthetic.main.fragment_stock.*

class Stock : Fragment() {

    data class StockItems(
        val srno: String,
        val product_name: String,
        val inward: String,
        val sold: String,
        val balance: String
    )

    private val stockItemsList = arrayListOf<StockItems>(
        StockItems("1","Potato", "150", "50", "100"),
        StockItems("2","Onion", "100", "90", "10"),
        StockItems("3","Cabbage", "160", "50", "90"),
        StockItems("4","Carrot", "190", "150", "40")

    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvStock.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = StockAdapter(stockItemsList)
        }
    }


}
