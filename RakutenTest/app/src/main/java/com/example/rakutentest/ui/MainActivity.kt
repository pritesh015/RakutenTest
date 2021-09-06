package com.example.rakutentest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rakutentest.R
import com.example.rakutentest.databinding.ActivityMainBinding
import com.example.rakutentest.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var productListAdapter: MainActivityAdapter
    private lateinit var productDetailFragment: ProductDetailFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        productListAdapter = MainActivityAdapter(listOf(), this)

        productDetailFragment = ProductDetailFragment()

        setUpBindings()
    }

    private fun setUpBindings() {
        productListAdapter.listener = object : MainActivityAdapter.OnBottomReachedListener {
            override fun onBottomReached() {
                mainActivityViewModel.getNextProducts()
            }

            override fun onItemClicked(id: Long) {
                mainActivityViewModel.getProductDetail(id)
                binding.fragmentContainer.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, productDetailFragment)
                    .addToBackStack("ProductDetail")
                    .commit()
            }
        }
        binding.rvListProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListProduct.adapter = productListAdapter

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainActivityViewModel.getProducts(query ?: "")

                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })

        mainActivityViewModel.onAllProductsLoaded().observe(this, Observer { productsList ->
            productListAdapter.setList(productsList)
        })

        mainActivityViewModel.onNextProductsLoaded().observe(this, { nextProductList ->
            productListAdapter.addList(nextProductList)
        })

        mainActivityViewModel.onErrorLoaded().observe(this, Observer { error ->
            Toast.makeText(this, getString(R.string.error_msg), Toast.LENGTH_SHORT).show()
        })
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
            binding.fragmentContainer.visibility = View.GONE
        }
    }
}