package com.example.rakutentest.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseProduct(
    @field:Json(name = "totalResultProductsCount")
    val totalResultProductsCount: Int = 0,

    @field:Json(name = "resultProductsCount")
    val resultProductsCount: Int = 0,

    @field:Json(name = "pageNumber")
    val pageNumber: Int = 0,

    @field:Json(name = "title")
    val title: String? = null,

    @field:Json(name = "maxProductsPerPage")
    val maxProductsPerPage: Int = 0,

    @field:Json(name = "maxPageNumber")
    val maxPageNumber: Int = 0,

    @field:Json(name = "products")
    val responseListProducts: List<ResponseListProducts> = listOf()
)

@JsonClass(generateAdapter = true)
data class ResponseListProducts(
    @field:Json(name = "id")
    val id: Long = 0,

    @field:Json(name = "newBestPrice")
    val newBestPrice: Float = 0.0F,

    @field:Json(name = "usedBestPrice")
    val usedBestPrice: Float = 0.0F,

    @field:Json(name = "headline")
    val headline: String? = null,

    @field:Json(name = "reviewsAverageNote")
    val reviewsAverageNote: Float = 0.0F,

    @field:Json(name = "nbReviews")
    val nbReviews: Int = 0,

    @field:Json(name = "categoryRef")
    val categoryRef: Int = 0,

    @field:Json(name = "imagesUrls")
    val imagesUrls: List<String> = listOf(),

    @field:Json(name = "buybox")
    val buyBox: ResponseBuyBox
)

@JsonClass(generateAdapter = true)
data class ResponseBuyBox(
    @field:Json(name = "salePrice")
    val salePrice: Float = 0.0F,

    @field:Json(name = "advertType")
    val advertType: String? = null,

    @field:Json(name = "advertQuality")
    val advertQuality: String? = null,

    @field:Json(name = "saleCrossedPrice")
    val saleCrossedPrice: Float = 0.0F,

    @field:Json(name = "salePercentDiscount")
    val salePercentDiscount: Int = 0
)


