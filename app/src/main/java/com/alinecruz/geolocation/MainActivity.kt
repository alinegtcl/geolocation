package com.alinecruz.geolocation

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.alinecruz.geolocation.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        binding.buttonMainWhereAmI.setOnClickListener {
            fetchLocation()
        }
    }

    private fun fetchLocation() {

        val task = fusedLocationProviderClient.lastLocation

        checkLocationPermission()

        task.addOnSuccessListener {
            if (it != null) {
                setupVisibility()
                binding.contentMainCoordinators.textMainLatitudeNumber.text = it.latitude.toString()
                binding.contentMainCoordinators.textMainLongitudeNumer.text =
                    it.longitude.toString()
            }
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }
    }

    private fun setupVisibility() {
        binding.buttonMainWhereAmI.visibility = View.GONE
        binding.contentMainCoordinators.textMainYourCoordinators.visibility = View.VISIBLE
        binding.contentMainCoordinators.textMainLatitude.visibility = View.VISIBLE
        binding.contentMainCoordinators.textMainLongitude.visibility = View.VISIBLE
        binding.contentMainCoordinators.imageMainCoordinators.visibility = View.VISIBLE
    }
}