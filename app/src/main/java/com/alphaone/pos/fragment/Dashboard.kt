package com.alphaone.pos.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.alphaone.pos.R
import com.alphaone.pos.activity.CheckoutAndPrint
import com.alphaone.pos.adapter.*
import kotlinx.android.synthetic.main.fragment_dashboard.*


class Dashboard : Fragment() {


    data class RecentProducts(
        val id: String,
        val image: String,
        val name: String,
        val price: String
    )

    private val recentProductsLIst = arrayListOf<RecentProducts>(
        RecentProducts(
            "1",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaeJ-8sjFG5rM4kQj1KSjTod2xPI5_5Z8Nm3Ye0lq43hvmcxK5SF1rLCjTWWO_Bz8nb6o&s=0",
            "Cucumber",
            "₹ 50"
        ),
        RecentProducts(
            "2",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmGsfn5lF8dr3Fo1CujFyHU-Lp2h5haoCc8MU7cR9muJQrikR0raw&s=0",
            "Broccoli",
            "₹ 70"
        ),
        RecentProducts(
            "3",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6gzP4ZLG2Uh08mPapD9OKkrOgGVPAnwrOyJJ42hDGJdBXnCfEITRI7LSc7r1TjCYm0XA&s=0",
            "Grapefruit",
            "₹ 35"
        ),
        RecentProducts(
            "4",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJDx6vfd3XAfhaVgJ5ePaRIlqCTKLDAjeSFwumeKObG2ucc3Isvw4&s=0",
            "Potato",
            "₹ 30"
        ),
        RecentProducts(
            "5",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTo4Hi33vhoFvA3Jn5T1F6XFOLEed3negEiFLoiSoV7ipQtBoI15OU&s=0",
            "Cabbage",
            "₹ 10"
        ),
        RecentProducts(
            "6",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUz-EPX7zvgwM_tfBoXeOt8gaTS6lGRLSIpVHDI-7YUw3CSJNPcTtVAve4CbmnBHkJYVY&s=0",
            "Carrot",
            "₹ 20"
        ),
        RecentProducts(
            "7",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUAdjlGtiSE3ddWStaKa4l9gbciL_f-Nx0bmLZ7cZRQdKgYU8OgVJomCn_ehDE0MzqYKQ&s=0",
            "Avocado",
            "₹ 50"
        )

    )

    data class Vegetables(val id: String, val image: String, val name: String, val price: String)

    private val vegetablesLIst = arrayListOf<Vegetables>(
        Vegetables(
            "1",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQaeJ-8sjFG5rM4kQj1KSjTod2xPI5_5Z8Nm3Ye0lq43hvmcxK5SF1rLCjTWWO_Bz8nb6o&s=0",
            "Cucumber",
            "₹ 50"
        ),
        Vegetables(
            "2",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmGsfn5lF8dr3Fo1CujFyHU-Lp2h5haoCc8MU7cR9muJQrikR0raw&s=0",
            "Broccoli",
            "₹ 70"
        ),
        Vegetables(
            "4",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJDx6vfd3XAfhaVgJ5ePaRIlqCTKLDAjeSFwumeKObG2ucc3Isvw4&s=0",
            "Potato",
            "₹ 30"
        ),
        Vegetables(
            "5",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTo4Hi33vhoFvA3Jn5T1F6XFOLEed3negEiFLoiSoV7ipQtBoI15OU&s=0",
            "Cabbage",
            "₹ 10"
        ),
        Vegetables(
            "6",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUz-EPX7zvgwM_tfBoXeOt8gaTS6lGRLSIpVHDI-7YUw3CSJNPcTtVAve4CbmnBHkJYVY&s=0",
            "Carrot",
            "₹ 20"
        )


    )

    data class Fruits(val id: String, val image: String, val name: String, val price: String)

    private val fruitsLIst = arrayListOf<Fruits>(
        Fruits(
            "3",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6gzP4ZLG2Uh08mPapD9OKkrOgGVPAnwrOyJJ42hDGJdBXnCfEITRI7LSc7r1TjCYm0XA&s=0",
            "Grapefruit",
            "₹ 35"
        ),
        Fruits(
            "7",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUAdjlGtiSE3ddWStaKa4l9gbciL_f-Nx0bmLZ7cZRQdKgYU8OgVJomCn_ehDE0MzqYKQ&s=0",
            "Avocado",
            "₹ 50"
        ),
        Fruits(
            "8",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRj0KxlYkAyJLfrs1kklgz4wh8PYeXQjds7EayNQ_wbNAc3cEWZH-JP1h_7yNjQ4Zab420&s=0",
            "Pineapple",
            "₹ 85"
        ),
        Fruits(
            "9",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJA2tnGOTDm5KD0KqrBL__4BgFthsr2dD3dtweY61cckVOpFplJ70Y5V1-o4LqFE2eQOI&s=0",
            "Watermelon",
            "₹ 70"
        ),
        Fruits(
            "10",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7LNGq6H30LuP9sKsxkvKs10nzIkyskhLcfYPkofAiKr6uEy18jDk&s=0",
            "Apple",
            "₹ 150"
        )

    )


    data class CartItems(
        val product: String,
        val quantity: String,
        val rate: String,
        val total: String
    )

    private val cartItemsList = arrayListOf<CartItems>(
        CartItems("Potato", "2", "50", "100"),
        CartItems("Cabbage", "1", "20", "20"),
        CartItems("Carrot", "3", "20", "60"),
        CartItems("Cucumber", "1", "30", "30")

    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* val audioManager =
           getSystemService<Any>(Context.AUDIO_SERVICE) as AudioManager?
        audioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0)*/
        val buttonClick: MediaPlayer = MediaPlayer.create(context, R.raw.click)
       // val itemSelected: MediaPlayer = MediaPlayer.create(context, R.raw.item_selected)
        val cartItemRemoved: MediaPlayer = MediaPlayer.create(context, R.raw.cart_item_removed)
        val clearCart: MediaPlayer = MediaPlayer.create(context, R.raw.clear_cart)
        btAddToCart.setOnClickListener { buttonClick.start() }
        ivClearCart.setOnClickListener {
            clearCart.start()
            cartItemsList.clear()
            rvCartItems.adapter?.notifyDataSetChanged()
           }
        ivClearDashboard.setOnClickListener {
            cartItemRemoved.start()
            etQuantity.text.clear()
            etRate.text.clear()
            etTotal.text.clear()
        }
        rvRecentProducts.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = RecentProductsAdapter(recentProductsLIst, object : OnProductClicked {
                override fun onClicked(
                    productImaged: String,
                    productImage: String,
                    productName: String,
                    productPrice: String
                ) {
                    buttonClick.start()
                    tvProductName.text = productName
                    loadWithCoil(ivProductImage, productImage)
                }
            })
        }
        rvVegetables.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = VegetablesAdapter(vegetablesLIst, object : OnProductClicked {
                override fun onClicked(
                    productImaged: String,
                    productImage: String,
                    productName: String,
                    productPrice: String
                ) {
                    buttonClick.start()
                    tvProductName.text = productName
                    loadWithCoil(ivProductImage, productImage)
                }
            })
        }

        rvFruits.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = FruitsAdapter(fruitsLIst, object : OnProductClicked {
                override fun onClicked(
                    productImaged: String,
                    productImage: String,
                    productName: String,
                    productPrice: String
                ) {
                    buttonClick.start()
                    tvProductName.text = productName
                    loadWithCoil(ivProductImage, productImage)
                }
            })
        }

        rvCartItems.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = CartAdapter(cartItemsList, object : OnCartItemAction {
                override fun onCartItemcancelled(position: Int) {
                    cartItemRemoved.start()
                    cartItemsList.removeAt(position)
                    adapter?.notifyDataSetChanged()
                    //Toast.makeText(context,"hii",Toast.LENGTH_LONG).show()
                }
            })
        }

        btCheckout.setOnClickListener {
           startActivity(Intent(this.context,CheckoutAndPrint::class.java))
        }
    }


    interface OnProductClicked {
        fun onClicked(
            productImaged: String,
            productImage: String,
            productName: String,
            productPrice: String
        )

    }

    interface OnCartItemAction {
        fun onCartItemcancelled(position: Int) {
        }
    }

    fun loadWithCoil(imageView: ImageView, imageUrl: String) {
        imageView.load(imageUrl) {
            placeholder(R.drawable.ic_vegetable)
            error(R.drawable.ic_error)
        }
    }
}
