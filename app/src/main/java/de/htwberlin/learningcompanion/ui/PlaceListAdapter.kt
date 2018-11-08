package de.htwberlin.learningcompanion.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.htwberlin.learningcompanion.R
import de.htwberlin.learningcompanion.model.Place
import de.htwberlin.learningcompanion.myplace.details.MyPlaceFragment

class PlaceListAdapter(private val placeDataSet: ArrayList<Place>, val supportFragmentManager: FragmentManager) : RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder>() {

    class PlaceViewHolder(val rootView: View, private val supportFragmentManager: FragmentManager) : RecyclerView.ViewHolder(rootView), View.OnClickListener {
        private lateinit var place: Place

        private val tvName: TextView
        private val tvAddress: TextView
        private val ivPlacePreview: ImageView

        init {
            rootView.setOnClickListener(this)
            tvName = rootView.findViewById(R.id.tv_name)
            tvAddress = rootView.findViewById(R.id.tv_address)
            ivPlacePreview = rootView.findViewById(R.id.iv_place_preview)
        }

        override fun onClick(v: View) {
            navigateToPlaceDetailFragment()
        }

        fun bindPlace(place: Place) {
            this.place = place
            tvName.text = place.name
            tvAddress.text = place.addressString

            val uri = Uri.parse(place.imageUri)
            Picasso.get().load(uri).fit().into(ivPlacePreview)
        }

        private fun navigateToPlaceDetailFragment() {
            val fragment = MyPlaceFragment()
            val bundle = Bundle()
            bundle.putLong("ID", place.id)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction().addToBackStack("detailfragment").replace(R.id.content_main, fragment).commit()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceListAdapter.PlaceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_list_item, parent, false) as View
        return PlaceViewHolder(view, supportFragmentManager)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = placeDataSet[position]
        holder.bindPlace(place)
    }

    override fun getItemCount() = placeDataSet.size

}