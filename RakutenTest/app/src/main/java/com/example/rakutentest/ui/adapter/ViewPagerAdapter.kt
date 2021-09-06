package com.example.rakutentest.ui.adapter

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.rakutentest.R
import com.example.rakutentest.api.models.ResponseProductDetail
import com.example.rakutentest.databinding.ViewPagerItemBinding

class ViewPagerAdapter(var context: Context, private val responseProductDetail: ResponseProductDetail) : PagerAdapter() {
    private val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
    var layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return responseProductDetail.images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.view_pager_item, container, false)
        val imageView = view.findViewById<View>(R.id.iv_view_pager) as ImageView

        //Use the Large image url
        Glide.with(context)
            .load(responseProductDetail.images[position].imagesUrls!!.entry[3].url)
            .apply(requestOptions)
            .into(imageView)
        container.addView(view)

        imageView.setOnClickListener {
            Toast.makeText(context, "image", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}
