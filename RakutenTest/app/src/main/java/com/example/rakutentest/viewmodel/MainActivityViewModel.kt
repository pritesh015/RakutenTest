package com.example.rakutentest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rakutentest.api.models.ResponseListProducts
import com.example.rakutentest.api.models.ResponseProduct
import com.example.rakutentest.models.ProductRepositoryImpl
import io.reactivex.rxkotlin.subscribeBy

class MainActivityViewModel: ViewModel() {
    private val productRepositoryImpl: ProductRepositoryImpl = ProductRepositoryImpl()

    private var liveProductList: MutableLiveData<List<ResponseListProducts>> = MutableLiveData()
    private var liveNextProductList: MutableLiveData<List<ResponseListProducts>> = MutableLiveData()
    private var liveError: MutableLiveData<String> = MutableLiveData()

    private var page: Int = 1
    private lateinit var keywordSearched: String
    private var lastPage = 0

    fun getProducts(keyword: String) {
        page = 1
        keywordSearched = keyword

        productRepositoryImpl.getProductList(keyword, page)
            .run {
                subscribeBy(
                    onSuccess = { response ->
                        val list: MutableList<ResponseListProducts> = mutableListOf()
                        response.first.map {
                            list.add(it)
                        }
                        println(response.second)
                        lastPage = response.second
                        liveProductList.postValue(list)
                    },
                    onError = {
                        liveError.postValue(it.toString())
                    }
                )
            }
    }

    fun getNextProducts() {
        page++

        if (page <= lastPage) {
            productRepositoryImpl.getProductList(keywordSearched, page)
                    .run {
                        subscribeBy(
                                onSuccess = { response ->
                                    val list: MutableList<ResponseListProducts> = mutableListOf()
                                    response.first.map {
                                        list.add(it)
                                    }
                                    liveNextProductList.postValue(list)
                                },
                                onError = {
                                    liveError.postValue(it.toString())
                                }
                        )
                    }
        }
    }

    fun onAllProductsLoaded(): LiveData<List<ResponseListProducts>> {
        return liveProductList
    }

    fun onNextProductsLoaded(): LiveData<List<ResponseListProducts>> {
        return liveNextProductList
    }

    fun onErrorLoaded(): MutableLiveData<String> {
        return liveError
    }

}