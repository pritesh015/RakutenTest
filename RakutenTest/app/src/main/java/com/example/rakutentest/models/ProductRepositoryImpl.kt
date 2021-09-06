package com.example.rakutentest.models

import com.example.rakutentest.api.models.ResponseListProducts
import com.example.rakutentest.api.models.ResponseProductDetail
import com.example.rakutentest.api.products.ProductApiImpl
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class ProductRepositoryImpl: ProductRepository {

    companion object {
        val productApiImpl: ProductApiImpl by lazy {
            ProductApiImpl()
        }
    }

    override fun getProductList(keyword: String, page: Int): Single<Pair<List<ResponseListProducts>, Int>> = Single.create { emitter ->
        productApiImpl.getProducts(keyword, page)
            .subscribeBy(
                onSuccess = {
                    val list: MutableList<ResponseListProducts> = mutableListOf()

                    for (item in it.responseListProducts) {
                        list.add(item)
                    }
                    emitter.onSuccess(Pair(list, it.maxPageNumber))
                },
                onError = {
                    emitter.onError(it)
                }
            )
    }

    override fun getProductDetail(id: Long): Single<ResponseProductDetail> = Single.create { emitter ->
        productApiImpl.getProductDetail(id.toString())
        .subscribeBy(
                onSuccess = {
                    emitter.onSuccess(it)
                },
                onError = {
                    emitter.onError(it)
                }
            )
    }
}