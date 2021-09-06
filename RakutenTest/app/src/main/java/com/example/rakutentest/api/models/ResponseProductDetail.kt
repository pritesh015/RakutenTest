package com.example.rakutentest.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseProductDetail(
    @field:Json(name = "productId")
    val productId: Long = 0,

    @field:Json(name = "salePrice")
    val salePrice: Float = 0.0F,

    @field:Json(name = "newBestPrice")
    val newBestPrice: Float = 0.0F,

    @field:Json(name = "usedBestPrice")
    val usedBestPrice: Float? = 0.0F,

    @field:Json(name = "seller")
    val seller: ResponseSeller? = null,

    @field:Json(name = "quality")
    val quality: String? = null,

    @field:Json(name = "type")
    val type: String? = null,

    @field:Json(name = "sellerComment")
    val sellerComment: String? = null,

    @field:Json(name = "headline")
    val headline: String? = null,

    @field:Json(name = "description")
    val description: String? = null,

    @field:Json(name = "categories")
    val categories: List<String> = listOf(),

    @field:Json(name = "globalRating")
    val globalRating: ResponseGlobalRating? = null,

    @field:Json(name = "images")
    val images: List<ResponseImages> = listOf()
)

@JsonClass(generateAdapter = true)
data class ResponseSeller(
    @field:Json(name = "id")
    val id: Long = 0,

    @field:Json(name = "login")
    val login: String? = null
)

@JsonClass(generateAdapter = true)
data class ResponseGlobalRating(
    @field:Json(name = "score")
    val score: Float = 0.0F,

    @field:Json(name = "nbReviews")
    val nbReviews: Int = 0
)

@JsonClass(generateAdapter = true)
data class ResponseImages(
    @field:Json(name = "imagesUrls")
    val imagesUrls: ResponseImagesUrls? = null,

    @field:Json(name = "id")
    val id: Long = 0
)

@JsonClass(generateAdapter = true)
data class ResponseImagesUrls(
    @field:Json(name = "entry")
    val entry: List<ResponseEntryImageUrls> = listOf()
)

@JsonClass(generateAdapter = true)
data class ResponseEntryImageUrls(
    @field:Json(name = "size")
    val size: String? = null,

    @field:Json(name = "url")
    val url: String? = null
)