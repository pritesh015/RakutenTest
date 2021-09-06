package com.example.rakutentest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

import com.example.rakutentest.R
import com.example.rakutentest.api.models.ResponseListProducts
import com.example.rakutentest.databinding.ItemsListProductBinding

class MainActivityAdapter(var items: List<ResponseListProducts>, val context: Context): RecyclerView.Adapter<ProductHolder>() {

    var listener:OnBottomReachedListener? = null
    private val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ItemsListProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        Glide.with(context)
            .load(items[position].imagesUrls[0])
            .apply(requestOptions)
            .into(holder.ivProduct)

        holder.tvHeadline.text = items[position].headline
        holder.rbRatingRate.rating = items[position].reviewsAverageNote
        holder.tvNbReviews.text = String.format(context.getString(R.string.reviews), items[position].nbReviews)
        holder.tvNewBestPrice.text = String.format(context.getString(R.string.best_price), items[position].newBestPrice)
        holder.tvCrossPrice.text = String.format(context.getString(R.string.best_price), items[position].buyBox.saleCrossedPrice)
        holder.tvPercentReduce.text = String.format(context.getString(R.string.percent_reduce), items[position].buyBox.salePercentDiscount)

        if (items[position].usedBestPrice != (0.0).toFloat()) {
            holder.tvUsedBestPrice.text = String.format(context.getString(R.string.best_price), items[position].usedBestPrice)
            holder.tvUsed.visibility = View.VISIBLE
        } else {
            holder.tvUsed.visibility = View.GONE
        }

        if ((position + 1) == items.size) {
            listener!!.onBottomReached()
        }

        holder.itemProductView.setOnClickListener {
            Toast.makeText(context, items[position].headline, Toast.LENGTH_SHORT).show()
        }
    }

    fun setList(list: List<ResponseListProducts>) {
        items = list
        notifyDataSetChanged()
    }

    fun addList(list: List<ResponseListProducts>) {
        items += list
        notifyDataSetChanged()
    }

    interface OnBottomReachedListener {
        fun onBottomReached()
    }
}

class ProductHolder(binding: ItemsListProductBinding): RecyclerView.ViewHolder(binding.root) {
    val itemProductView = binding.clProductItemView
    val ivProduct = binding.ivProduct
    val tvHeadline = binding.tvHeadline
    val rbRatingRate = binding.rbProductRate
    val tvNbReviews = binding.tvNbReviews
    val tvUsedBestPrice = binding.tvUsedBestPrice
    val tvUsed = binding.tvUsed
    val tvNewBestPrice = binding.tvNewBestPrice
    val tvCrossPrice = binding.tvCrossPrice
    val tvPercentReduce = binding.tvPercentReduce
}