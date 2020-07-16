package com.shwetank.nasamarsphotos.ui.fragments.config

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shwetank.nasamarsphotos.R
import com.shwetank.nasamarsphotos.repo.network.entity.roverimages.Photos
import com.shwetank.nasamarsphotos.ui.MainActivity
import com.shwetank.nasamarsphotos.ui.MarsViewModel
import com.shwetank.nasamarsphotos.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.config_fragment_layout.*

@AndroidEntryPoint
class ConfigFragment : Fragment(R.layout.config_fragment_layout) {

    private lateinit var viewModel: MarsViewModel
    private val args: ConfigFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        subscribeObservers()
        setUpRecyclerView()
        viewModel.getRoverImages(args.date)
    }

    private fun setUpRecyclerView() {
        rv_images.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.HORIZONTAL
                )
            )
            val roverAdapter = RoverImageAdapter()
            roverAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
            adapter = roverAdapter
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