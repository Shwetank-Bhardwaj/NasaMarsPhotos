package com.shwetank.nasamarsphotos.ui.fragments.config

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shwetank.nasamarsphotos.R
import com.shwetank.nasamarsphotos.repo.network.entity.roverimages.Photo
import kotlinx.android.synthetic.main.item_rover_image_layout.view.*

class RoverImageAdapter constructor(val onImageClickListener: OnImageClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var photoList: List<Photo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RoverImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_rover_image_layout,
                parent,
                false
            ),
            onImageClickListener
        )
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RoverImageViewHolder).bind(photoList[position])
    }

    fun setList(photos: List<Photo>) {
        photoList = photos
        notifyDataSetChanged()
    }

    class RoverImageViewHolder(itemView: View, val onImageClickListener: OnImageClickListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo) {
            with(itemView) {
                Glide.with(itemView)
                    .load(photo.imgSrc)
                    .placeholder(R.drawable.ic_placeholder_black)
                    .into(iv_image)
                tv_sol.text = "${photo.rover.name} with ${photo.cameraDetails.fullName}"
                setOnClickListener { onImageClickListener.onImageClicked(photo.imgSrc) }
            }
        }

    }

    interface OnImageClickListener {
        fun onImageClicked(url: String)
    }
}