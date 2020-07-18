package com.shwetank.nasamarsphotos.ui.fragments.manifest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shwetank.nasamarsphotos.R
import com.shwetank.nasamarsphotos.repo.network.entity.manifest.SolManifest
import kotlinx.android.synthetic.main.item_rover_manifest_sol_layout.view.*

class ManifestAdapter constructor(val onSolClickListener: OnSolClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<SolManifest> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RoverManifestViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_rover_manifest_sol_layout,
                parent,
                false
            ),
            onSolClickListener
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RoverManifestViewHolder).bind(list[position])
    }

    fun setList(photos: List<SolManifest>){
        list = photos
        notifyDataSetChanged()
    }

    class RoverManifestViewHolder(itemView: View, val onSolClickListener: OnSolClickListener) : RecyclerView.ViewHolder(itemView) {

        fun bind(solManifest: SolManifest) {
            with(itemView){
                tv_sol.text = "Sol: ${solManifest.sol.toString()}"
                tv_earth_date.text = "Earth Date: ${solManifest.earthDate}"
                tv_total_photos.text = "Total photos: ${solManifest.totalPhotos.toString()}"
                tv_cameras.text = "Cameras: ${solManifest.cameras.joinToString()}"
                setOnClickListener {
                    onSolClickListener.onSolClicked(solManifest.earthDate)
                }
            }
        }

    }

    interface OnSolClickListener{
        fun onSolClicked(earthDate: String)
    }

}