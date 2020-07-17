package com.shwetank.nasamarsphotos.ui.fragments.settings

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.shwetank.nasamarsphotos.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.settings_fragment_layout.*

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.settings_fragment_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Settings"

        setCamera()
        setListReverse()
    }

    private fun setListReverse() {
        val shouldSort = activity?.getPreferences(Context.MODE_PRIVATE)!!
            .getBoolean(getString(R.string.sort_list), true)
        sw_reverse.isChecked = shouldSort
        sw_reverse.setOnCheckedChangeListener { buttonView, isChecked ->
            activity?.getPreferences(Context.MODE_PRIVATE)!!.edit()
                .putBoolean(getString(R.string.sort_list), isChecked).commit()
        }

    }

    private fun setCamera() {
        val cameraCode = activity?.getPreferences(Context.MODE_PRIVATE)!!
            .getString(getString(R.string.camera_code), getString(R.string.all))

        when (cameraCode) {
            getString(R.string.fhaz) -> {
                radioGroup.check(R.id.rbFhaz)
            }
            getString(R.string.rhaz) -> {
                radioGroup.check(R.id.rbRhaz)
            }
            getString(R.string.mast) -> {
                radioGroup.check(R.id.rbMast)
            }
            getString(R.string.chemcam) -> {
                radioGroup.check(R.id.rbChemCam)
            }
            getString(R.string.mahli) -> {
                radioGroup.check(R.id.rbMahli)
            }
            getString(R.string.mardi) -> {
                radioGroup.check(R.id.rbMardi)
            }
            getString(R.string.navcam) -> {
                radioGroup.check(R.id.rbNavCam)
            }
            getString(R.string.all) -> {
                radioGroup.check(R.id.rbAll)
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val cameraCode: String
            when (checkedId) {
                R.id.rbFhaz -> {
                    cameraCode = getString(R.string.fhaz)
                }
                R.id.rbRhaz -> {
                    cameraCode = getString(R.string.rhaz)
                }
                R.id.rbMast -> {
                    cameraCode = getString(R.string.mast)
                }
                R.id.rbChemCam -> {
                    cameraCode = getString(R.string.chemcam)
                }
                R.id.rbMahli -> {
                    cameraCode = getString(R.string.mahli)
                }
                R.id.rbMardi -> {
                    cameraCode = getString(R.string.mardi)
                }
                R.id.rbNavCam -> {
                    cameraCode = getString(R.string.navcam)
                }
                R.id.rbAll -> {
                    cameraCode = getString(R.string.all)
                }
                else -> cameraCode = getString(R.string.all)
            }
            activity?.getPreferences(Context.MODE_PRIVATE)!!.edit()
                .putString(getString(R.string.camera_code), cameraCode).commit()
        }
    }

}