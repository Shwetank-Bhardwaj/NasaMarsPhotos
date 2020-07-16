package com.shwetank.nasamarsphotos.ui.fragments.manifest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shwetank.nasamarsphotos.R
import com.shwetank.nasamarsphotos.repo.network.entity.manifest.Manifest
import com.shwetank.nasamarsphotos.ui.MainActivity
import com.shwetank.nasamarsphotos.ui.MarsViewModel
import com.shwetank.nasamarsphotos.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.rover_fragment_layout.*

@AndroidEntryPoint
class ManifestFragment : Fragment(R.layout.rover_fragment_layout) {

    private lateinit var viewModel: MarsViewModel

    private val onSolClickListener =  object : ManifestAdapter.OnSolClickListener {
        override fun onSolClicked(earthDate: String) {
            val bundle: Bundle = Bundle().apply {
                putSerializable("date", earthDate)
            }
            findNavController().navigate(
                R.id.action_manifestFragment_to_configFragment,
                bundle
            )
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        subscribeObservers()
        setUpRecyclerView()
        viewModel.getRoverManifest()
    }

    private fun setUpRecyclerView() {
        rv_sol.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.HORIZONTAL
                )
            )
            val manifestAdapter = ManifestAdapter(onSolClickListener)
            manifestAdapter.stateRestorationPolicy =
                RecyclerView.Adapter.StateRestorationPolicy.ALLOW
            adapter = manifestAdapter
        }
    }

    private fun subscribeObservers() {
        viewModel.marsRoverManifest.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it) {
                is DataState.Success<Manifest> -> {
                    displayProgressBar(false)
                    tv_rover_name.text = "Rover Name: ${it.data.photoManifest.name}"
                    tv_launch_date.text = "Launch Date: ${it.data.photoManifest.launchDate}"
                    tv_landing_date.text = "Landing Date: ${it.data.photoManifest.landingDate}"
                    tv_status.text = "Rover Current Status: ${it.data.photoManifest.status}"
                    (rv_sol.adapter as ManifestAdapter).setList(it.data.photoManifest.solManifests.reversed())
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
            Snackbar.make(rover_frag_tv_error, message, Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(rover_frag_tv_error, "Unknown Error", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        rover_frag_progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }


}