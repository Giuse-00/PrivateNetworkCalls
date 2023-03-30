package com.android.example.private_network_class.network.dto

import com.android.example.private_network_class.network.dto.X1
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("1") val first: X1,
    @SerializedName("2") val second: X1,
    @SerializedName("3") val third: X1
)