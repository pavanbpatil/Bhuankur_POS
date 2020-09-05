package com.alphaone.pos.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.alphaone.pos.R
import kotlinx.android.synthetic.main.fragment_settings.*


class Settings : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rlChangePassword.setOnClickListener {
            changePasswordDialog()
        }
    }

    private fun changePasswordDialog() {

        val dialog = Dialog(this.context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.change_password_layout)
       // val rvPreview = dialog.findViewById(R.id.rvPreview) as RecyclerView


        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.getWindow()?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show()

    }
}
