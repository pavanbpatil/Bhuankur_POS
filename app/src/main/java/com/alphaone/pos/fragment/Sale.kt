package com.alphaone.pos.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.alphaone.pos.R
import com.alphaone.pos.activity.CheckoutAndPrint
import com.alphaone.pos.adapter.PreviewListAdapter
import com.alphaone.pos.adapter.SaleByProductAdapter
import com.alphaone.pos.adapter.SaleByReceiptAdapter
import kotlinx.android.synthetic.main.fragment_sale.*

class Sale : Fragment() {


    data class SaleByProductItems(
        val srno: String,
        val product: String,
        val sold: String,
        val amount: String
    )

    private val saleByProductItemsList = arrayListOf<SaleByProductItems>(
        SaleByProductItems("1","Potato", "12", "450"),
        SaleByProductItems("2","Onion", "20", "685"),
        SaleByProductItems("3","Cabbage", "15", "850"),
        SaleByProductItems("4","Carrot", "12", "150")

    )

    data class SaleByReceiptItems(
        val srno: String,
        val receipt: String,
        val sold: String,
        val amount: String
    )

    private val SaleByReceiptItemsList = arrayListOf<SaleByReceiptItems>(
        SaleByReceiptItems("1","1234", "12", "450"),
        SaleByReceiptItems("2","5678", "20", "685"),
        SaleByReceiptItems("3","8765", "15", "850"),
        SaleByReceiptItems("4","4567", "12", "150")

    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSaleByProduct.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = SaleByProductAdapter(saleByProductItemsList)
        }
        rvSaleByReceipts.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = SaleByReceiptAdapter(SaleByReceiptItemsList, object : OnItemListAction{
                override fun onViewReceiptClicked(position: Int) {
                    super.onViewReceiptClicked(position)
                    previewReceipt()
                }
            })
        }
    }

    interface OnItemListAction {
        fun onViewReceiptClicked(position: Int) {
        }

    }
    private fun previewReceipt() {
        data class CartItems(
            val product: String,
            val quantity: String,
            val rate: String,
            val total: String
        )
        val cartItemsList = arrayListOf<CheckoutAndPrint.CartItems>(
            CheckoutAndPrint.CartItems("Potato", "2", "50", "100"),
            CheckoutAndPrint.CartItems("Cabbage", "1", "20", "20"),
            CheckoutAndPrint.CartItems("Carrot", "3", "20", "60"),
            CheckoutAndPrint.CartItems("Cucumber", "1", "30", "30")

        )
        val dialog = Dialog(this.context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.customer_receipt_preview_layout)
        val rvPreview = dialog.findViewById(R.id.rvPreview) as RecyclerView
        rvPreview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = PreviewListAdapter(cartItemsList)
        }

        /*body.text = title
        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }*/
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.getWindow()?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show()

    }

}
