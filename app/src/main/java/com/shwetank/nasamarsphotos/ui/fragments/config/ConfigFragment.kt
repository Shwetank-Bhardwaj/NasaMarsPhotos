package com.shwetank.nasamarsphotos.ui.fragments.config

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.shwetank.nasamarsphotos.R
import com.shwetank.nasamarsphotos.repo.network.entity.roverimages.Photos
import com.shwetank.nasamarsphotos.ui.MarsViewModel
import com.shwetank.nasamarsphotos.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.config_fragment_layout.*

@AndroidEntryPoint
class ConfigFragment : Fragment(R.layout.config_fragment_layout) {

    private val viewModel: MarsViewModel by viewModels()
    private val args: ConfigFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        setUpRecyclerView()
        val cameraCode = activity?.getPreferences(Context.MODE_PRIVATE)!!
            .getString(getString(R.string.camera_code), getString(R.string.all))
        if (cameraCode.equals(getString(R.string.all))) {
            viewModel.getRoverImages(args.date)
        } else {
            viewModel.getRoverImages(args.date, cameraCode)
        }
    }

    private fun setUpRecyclerView() {
        rv_images.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = RoverImageAdapter()
        }
    }

    private fun subscribeObservers() {
        viewModel.marsRoverPhotos.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it) {
                is DataState.Success<Photos> -> {
                    displayProgressBar(false)
                    (rv_images.adapter as RoverImageAdapter).setList(it.data.photos)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(it.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            tv_error.text = message
            Snackbar.make(tv_error, message, Snackbar.LENGTH_SHORT).show()
        } else {
            tv_error.text = "Unknown Error"
            Snackbar.make(tv_error, "Unknown Error", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

}