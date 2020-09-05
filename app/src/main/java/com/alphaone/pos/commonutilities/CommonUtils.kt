package com.alphaone.pos.commonutilities

import android.app.Dialog
import android.content.Context
import android.util.Base64
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.alphaone.pos.R
import com.google.gson.JsonObject
import java.util.Base64.getDecoder

class CommonUtils {
    companion object {
        fun showLoadingDialog(
            context: Context?,
            message: String?
        ): Dialog? {
            val dialog = Dialog(context!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.layout_waiting_loader_dialog)
            val tvMessage = dialog.findViewById<TextView>(R.id.tvMessage)
            tvMessage.text = message
            dialog.setCancelable(false)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            //lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.window!!.attributes = lp
            return dialog
        }

        fun showMessageDialog(
            context: Context?,
            message: String?,
            status: String?
        ) {
            val dialog = Dialog(context!!)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            if (status.equals("true")) {
                dialog.setContentView(R.layout.layout_success_message_dialog)
            } else {
                dialog.setContentView(R.layout.layout_error_message_dialog)
            }
            val tvMessage = dialog.findViewById<TextView>(R.id.tvMessage)
            val btOk = dialog.findViewById<Button>(R.id.btOk)
            btOk.setOnClickListener {
                dialog.dismiss()
            }
            tvMessage.text = message
            dialog.setCancelable(true)
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.window!!.attributes = lp
            dialog.show()
        }

        fun encodeString(JsonData: String): String {
            return Base64.encodeToString(JsonData.toByteArray(charset("UTF-8")), Base64.DEFAULT)
        }

        fun decodeString(JsonData: String): String {
            return Base64.decode(JsonData, Base64.DEFAULT).toString(charset("UTF-8"))
        }

        fun sendFromSecret(JsonData: String): JsonObject {
            var raw = JsonObject()
            raw.addProperty("secret", JsonData)
            return raw
        }
    }


}