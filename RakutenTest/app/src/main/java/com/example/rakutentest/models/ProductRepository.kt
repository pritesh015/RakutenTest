package com.example.rakutentest.models

import com.example.rakutentest.api.models.ResponseListProducts
import com.example.rakutentest.api.models.ResponseProductDetail
import io.reactivex.Single

interface ProductRepository {

    fun getProductList(keyword: String, page: Int): Single<Pair<List<ResponseListProducts>, Int>>
    fun getProductDetail(id: Long): Single<ResponseProductDetail>
}