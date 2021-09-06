package com.example.rakutentest.models

import com.example.rakutentest.api.models.ResponseListProducts
import io.reactivex.Single

interface ProductRepository {

    fun getProductList(keyword: String, page: Int): Single<Pair<List<ResponseListProducts>, Int>>
}