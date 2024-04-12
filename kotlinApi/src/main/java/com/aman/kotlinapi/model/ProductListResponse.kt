package com.cynoteck.kotlinapi.models.d2v


import com.google.gson.annotations.SerializedName

data class ProductListResponse(
    @SerializedName("data")
    val `data`: List<ProductListResponseData>?
)