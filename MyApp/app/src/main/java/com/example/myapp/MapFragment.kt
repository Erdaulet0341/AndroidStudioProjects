package com.example.myapp

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
import kotlinx.coroutines.flow.callbackFlow


class MapFragment : Fragment() {
//    lateinit var googleMap: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)

//        val supportMap:SupportMapFragment = SupportMapFragment().childFragmentManager.findFragmentById(R.id.my_map) as SupportMapFragment
//
////        supportMap.getMapAsync(OnMapReadyCallback() {mMap->
////            mMap.setOnMapClickListener(GoogleMap.OnMapClickListener(){
////                var marker = MarkerOptions()
////                marker.position(it)
////                marker.title(it.latitude.toString()+ " CarZone " + it.longitude.toString())
////                mMap.clear()
////                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 20F))
////                mMap.addMarker(marker)
////            })
////        } )
        return view
    }
}