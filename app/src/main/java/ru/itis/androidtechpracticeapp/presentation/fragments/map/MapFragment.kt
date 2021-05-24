package ru.itis.androidtechpracticeapp.presentation.fragments.map

import android.app.Instrumentation
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.SharedViewModel
import ru.itis.androidtechpracticeapp.presentation.ToggleBars
import java.io.IOException

class MapFragment : Fragment() {

    private lateinit var supportMapFragment: SupportMapFragment
    private lateinit var map: GoogleMap
    private var latLng: LatLng? = null

    private lateinit var hideBars: ToggleBars
    private lateinit var sharedViewModel: SharedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hideBars = (activity as MainActivity)
        sharedViewModel = (activity as MainActivity).sharedViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        supportMapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_map_google_map) as SupportMapFragment

        supportMapFragment.getMapAsync { googleMap ->
            map = googleMap
            googleMap.setOnMapClickListener {
                latLng = it
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

        hideBars.hideBottomBar()

        initUi()

    }

    private fun MapFragment.initUi() {
        fragment_map_search_view.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val location = fragment_map_search_view.query.toString()
                    var addressList: List<Address>? = null
                    if (location != null || location != "") {
                        val geocoder = Geocoder(activity as MainActivity)
                        try {
                            addressList = geocoder.getFromLocationName(location, 1)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                        if (addressList?.isEmpty() == false) {
                            val address = addressList[0]
                            latLng = LatLng(address.latitude, address.longitude)
                            map.addMarker(
                                MarkerOptions().position(latLng).title(location)
                            )
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10F))
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            }
        )

        fragment_map_floating_btn.setOnClickListener {
            if (latLng != null) {
                sharedViewModel.setCoords(Pair(latLng?.latitude ?: 0.0, latLng?.longitude ?: 0.0))
                (activity as MainActivity).onBackPressed()
            } else {
                Toast.makeText((activity as MainActivity),
                    "Установите координаты!",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        hideBars.showBottomBar()
    }


}