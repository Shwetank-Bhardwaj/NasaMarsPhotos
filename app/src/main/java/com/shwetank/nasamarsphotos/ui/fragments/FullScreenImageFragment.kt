package com.shwetank.nasamarsphotos.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.shwetank.nasamarsphotos.R
import com.shwetank.nasamarsphotos.ui.MainActivity
import com.shwetank.nasamarsphotos.ui.MarsViewModel
import kotlinx.android.synthetic.main.fullscreen_image_fragment_layout.*


class FullScreenImageFragment : Fragment(R.layout.fullscreen_image_fragment_layout) {

    private lateinit var viewModel: MarsViewModel
    private val args: FullScreenImageFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        Glide.with(this).load(args.url).placeholder(R.drawable.ic_placeholder_black)
            .into(ivFullScreenImageView)
    }
}