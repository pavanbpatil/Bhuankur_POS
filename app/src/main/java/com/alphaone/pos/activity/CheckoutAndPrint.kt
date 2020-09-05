package com.alphaone.pos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alphaone.pos.R
import com.alphaone.pos.adapter.CartAdapter
import com.alphaone.pos.adapter.DivLikeAdapter
import com.alphaone.pos.adapter.PreviewListAdapter
import com.alphaone.pos.fragment.Dashboard
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_checkout_and_print.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import com.alphaone.pos.fragment.Dashboard.CartItems as CartItems1

class CheckoutAndPrint : AppCompatActivity() {

    data class CartItems(
        val product: String,
        val quantity: String,
        val rate: String,
        val total: String
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_and_print)
        val recyclerView = findViewById<RecyclerView>(R.id.rvFlex)
        val layoutManagerFlex = FlexboxLayoutManager(this)
        layoutManagerFlex.flexDirection = FlexDirection.ROW
        layoutManagerFlex.justifyContent = JustifyContent.CENTER
        recyclerView.layoutManager = layoutManagerFlex


        val divLikeContent = ArrayList<String>()
        divLikeContent.add("Potato 1 Kg")
        divLikeContent.add("Cucumber 2 Kg")
        divLikeContent.add("Onion 5 Kg")
        divLikeContent.add("Carrot 1.5 Kg")
        divLikeContent.add("Tomato 3 Kg")

        val adapter1 = DivLikeAdapter(divLikeContent)

       // recyclerView.adapter = adapter
      //  divLikeContent.add(generateRandonText())
        recyclerView.adapter = adapter1



        val cartItemsList = arrayListOf<CartItems>(
            CartItems("Potato", "2", "50", "100"),
            CartItems("Cabbage", "1", "20", "20"),
            CartItems("Carrot", "3", "20", "60"),
            CartItems("Cucumber", "1", "30", "30")

        )

        rvPreview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = PreviewListAdapter(cartItemsList)
        }

        btGoBack.setOnClickListener {
            finish()
        }
    }

    private fun generateRandonText(): String {
        val sb = StringBuilder()
        for (i in 1 + (Math.random() * 20).toInt() downTo 1) {
            sb.append("a")
        }
        return sb.toString()
    }
}
