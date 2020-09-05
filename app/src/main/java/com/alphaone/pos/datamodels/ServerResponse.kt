package com.alphaone.pos.datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ServerResponse {

    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

}