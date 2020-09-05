package com.alphaone.pos.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.alphaone.pos.R
import com.alphaone.pos.adapter.CartAdapter
import com.alphaone.pos.adapter.PendingCartAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_pending_cart.*


class PendingCart : Fragment() {

    data class PendingCartItems(
        val srno: String,
        val item: String,
        val datetime: String,
        val weight: String,
        val quantity: String,
        val amount: String
    )

    private val pendingCartItemsItemsList = arrayListOf<PendingCartItems>(
        PendingCartItems("1","Potato", "29th May 2020 | 5:23 PM", "5", "10","250"),
        PendingCartItems("1","Tomato", "29th May 2020 | 5:23 PM", "2", "5","50"),
        PendingCartItems("1","Carrot", "29th May 2020 | 5:23 PM", "3", "3","90"),
        PendingCartItems("1","Onion", "29th May 2020 | 5:23 PM", "5", "10","250")

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pending_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPendingCartItems.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = PendingCartAdapter(pendingCartItemsItemsList, object : OnCartItemAction {
                override fun onCloseClicked(position: Int) {
                    super.onCloseClicked(position)
                }

                override fun onOpenClicked(position: Int) {
                    super.onOpenClicked(position)
                }
            })
        }
    }

    interface OnCartItemAction {
        fun onOpenClicked(position: Int) {
        }
        fun onCloseClicked(position: Int) {
        }
    }
}
