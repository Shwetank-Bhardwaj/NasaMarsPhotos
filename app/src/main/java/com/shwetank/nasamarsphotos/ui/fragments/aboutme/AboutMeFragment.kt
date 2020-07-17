package com.shwetank.nasamarsphotos.ui.fragments.aboutme

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.shwetank.nasamarsphotos.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.about_me_fragment_layout.*

@AndroidEntryPoint
class AboutMeFragment : Fragment(R.layout.about_me_fragment_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.about_developer)
        val pathUri = "file:///android_asset/profile_pic.jpg"
        Glide.with(this)
            .load(pathUri)
            .circleCrop()
            .into(profile_pic)
        textView5.movementMethod = LinkMovementMethod.getInstance()
        textView6.movementMethod = LinkMovementMethod.getInstance()

    }
}