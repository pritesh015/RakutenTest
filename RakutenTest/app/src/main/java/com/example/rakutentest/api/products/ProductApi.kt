package com.example.rakutentest.api.products

import com.example.rakutentest.api.models.ResponseProduct
import com.example.rakutentest.api.models.ResponseProductDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("search?")
    fun getProducts(@Query("keyword") keyword: String, @Query("page") page: Int): Single<ResponseProduct>

    @GET("details")
    fun getProductDetail(@Query("id") id: String): Single<ResponseProductDetail>
}