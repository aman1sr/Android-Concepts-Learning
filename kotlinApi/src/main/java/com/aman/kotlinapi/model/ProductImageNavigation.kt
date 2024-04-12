package com.cynoteck.kotlinapi.models.d2v


import com.google.gson.annotations.SerializedName

data class ProductImageNavigation(
    @SerializedName("id")
    val id: Double?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("productId")
    val productId: Double?,
    @SerializedName("status")
    val status: Boolean?
)