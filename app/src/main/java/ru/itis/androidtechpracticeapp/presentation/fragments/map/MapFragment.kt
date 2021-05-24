package ru.itis.androidtechpracticeapp.presentation.fragments.map

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import ru.itis.androidtechpracticeapp.R

class MapFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_google_map) as SupportMapFragment

        supportMapFragment.getMapAsync { googleMap ->
            googleMap.setOnMapClickListener {
                val marker = MarkerOptions()
                marker.position(it)
                marker.title("${it.latitude} : ${it.longitude}")
                googleMap.clear()
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 10F))
                googleMap.addMarker(marker)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}