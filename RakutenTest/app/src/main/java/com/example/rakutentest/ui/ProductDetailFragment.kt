package com.example.rakutentest.ui

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.rakutentest.R
import com.example.rakutentest.api.models.ResponseProductDetail
import com.example.rakutentest.databinding.FragmentProductDetailBinding
import com.example.rakutentest.ui.adapter.ViewPagerAdapter
import com.example.rakutentest.viewmodel.MainActivityViewModel


class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainActivityViewModel
    private var viewPagerAdapter: ViewPagerAdapter? = null
    private lateinit var responseProductDetail: ResponseProductDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBinding()
    }

    private fun setUpBinding() {
        viewModel.onProductDetailLoaded().observe(requireActivity(), { productDetail ->
            viewPagerAdapter = ViewPagerAdapter(requireActivity(), productDetail)
            binding.viewPager.adapter = viewPagerAdapter
            binding.tabLayout.setupWithViewPager(binding.viewPager, true)

            binding.tvHeadline.text = productDetail.headline
            binding.tvCategories.text = productDetail.categories.toString()
            binding.rbProductRate.rating = productDetail.globalRating!!.score
            binding.tvNbReviews.text = String.format(getString(R.string.reviews), productDetail.globalRating.nbReviews)
            binding.tvNewBestPrice.text = String.format(getString(R.string.best_price), productDetail.newBestPrice)
            binding.tvState.text = getString(R.string.new_best_price)
            binding.tvSellerLogin.text = productDetail.seller!!.login
            binding.tvSellerComment.text = productDetail.sellerComment

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                binding.tvProductDescription.text = Html.fromHtml(productDetail.description, Html.FROM_HTML_MODE_COMPACT)
            } else {
                binding.tvProductDescription.text = Html.fromHtml(productDetail.description)
            }

            responseProductDetail = productDetail
        })

        binding.toggleBtnGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_new -> {
                        binding.tvNewBestPrice.text = String.format(getString(R.string.best_price), responseProductDetail.newBestPrice)
                        binding.tvState.text = getString(R.string.new_best_price)
                        binding.tvNewBestPrice.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                        binding.tvState.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
                    }
                    R.id.btn_used -> {
                        binding.tvNewBestPrice.text = String.format(getString(R.string.best_price), responseProductDetail.usedBestPrice)
                        binding.tvState.text = getString(R.string.used_best_price)
                        binding.tvNewBestPrice.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                        binding.tvState.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    }
                }
            }
        }

        binding.ibBackArrow.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}