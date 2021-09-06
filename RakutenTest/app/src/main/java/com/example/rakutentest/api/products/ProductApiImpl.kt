package com.example.rakutentest.api.products

import android.util.Log
import com.example.rakutentest.api.models.ResponseProduct
import com.example.rakutentest.retrofit.RetrofitInit
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ProductApiImpl: ProductApi {
    private var productApi: ProductApi = RetrofitInit.builder().create(ProductApi::class.java)
    private val LOGTAG = "productApiImpl"

    // Get products list and return a ResponseProduct
    override fun getProducts(keyword: String, page: Int): Single<ResponseProduct> =
        Single.create { emitter ->
            productApi.getProducts(keyword, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = { response ->
                        emitter.onSuccess(response)
                    },
                    onError = {
                        Log.e(LOGTAG, it.localizedMessage!!)
                        emitter.onError(it)
                    }
                )
        }
}