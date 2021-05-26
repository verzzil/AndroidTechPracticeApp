package ru.itis.androidtechpracticeapp.presentation.fragments.map

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.responses.ApprovedProofsResponse
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.SharedViewModel
import ru.itis.androidtechpracticeapp.presentation.ToggleBars
import ru.itis.androidtechpracticeapp.utils.Key
import javax.inject.Inject

class MyMapFragment : Fragment() {

    private lateinit var supportMapFragment: SupportMapFragment
    private lateinit var map: GoogleMap

    private lateinit var hideBars: ToggleBars
    private lateinit var sharedViewModel: SharedViewModel
    private var userId: Int = 0

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MyMapViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hideBars = (activity as MainActivity)
        sharedViewModel = (activity as MainActivity).sharedViewModel
        userId = (activity as MainActivity).sp.getInt(Key.USER_ID, 0)

        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MyMapViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_map, container, false)

        supportMapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_my_map_google_map) as SupportMapFragment

        supportMapFragment.getMapAsync { googleMap ->
            map = googleMap
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideBars.hideBottomBar()

        viewModel.getPoints().observe(viewLifecycleOwner, {
            for (apr: ApprovedProofsResponse in it) {
                map.addMarker(
                    MarkerOptions().position(LatLng(apr.latitude, apr.longitude))
                )
            }
        })

        viewModel.findApprovedProofs(userId)

    }

    override fun onPause() {
        super.onPause()
        hideBars.showBottomBar()
    }

}