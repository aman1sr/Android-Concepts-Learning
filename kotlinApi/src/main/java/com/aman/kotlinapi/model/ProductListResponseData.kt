package com.cynoteck.kotlinapi.models.d2v


import com.google.gson.annotations.SerializedName

data class ProductListResponseData(
    @SerializedName("bestBefore")
    val bestBefore: String?,
    @SerializedName("cart")
    val cart: List<Any?>?,
    @SerializedName("cityId")
    val cityId: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Double?,
    @SerializedName("isDeleted")
    val isDeleted: Boolean?,
    @SerializedName("merchant")
    val merchant: Any?,
    @SerializedName("merchantId")
    val merchantId: Double?,
    @SerializedName("mrp")
    val mrp: Double?,
    @SerializedName("orderSlave")
    val orderSlave: List<Any?>?,
    @SerializedName("petType")
    val petType: Any?,
    @SerializedName("petTypeId")
    val petTypeId: Int?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("productCompany")
    val productCompany: Any?,
    @SerializedName("productCompanyId")
    val productCompanyId: Double?,
    @SerializedName("productFeature")
    val productFeature: List<Any?>?,
    @SerializedName("productFor")
    val productFor: String?,
    @SerializedName("productImage")
    val productImage: String?,
    @SerializedName("productImageNavigation")
    val productImageNavigation: List<ProductImageNavigation?>?,
    @SerializedName("productName")
    val productName: String?,
    @SerializedName("productRating")
    val productRating: List<Any?>?,
    @SerializedName("productSize")
    val productSize: String?,
    @SerializedName("quantity")
    val quantity: Int?,
    @SerializedName("size")
    val size: Any?,
    @SerializedName("sizeId")
    val sizeId: Double?,
    @SerializedName("status")
    val status: Boolean?,
    @SerializedName("subCategory")
    val subCategory: Any?,
    @SerializedName("subCategoryId")
    val subCategoryId: Double?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("unit")
    val unit: Any?,
    @SerializedName("unitId")
    val unitId: Double?
)