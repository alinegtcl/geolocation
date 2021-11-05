package com.alinecruz.geolocation

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.alinecruz.geolocation.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private var cancellationTokenSource = CancellationTokenSource()

    companion object {
        const val LOCATION_CODE_REQUEST = 1
    }

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

        val currentLocationTask = fusedLocationProviderClient.getCurrentLocation(
            PRIORITY_HIGH_ACCURACY,
            cancellationTokenSource.token
        )

        checkLocationPermission()

        currentLocationTask.addOnSuccessListener {
            if (it != null) {
                setupVisibility()
                binding.contentMainCoordinators.textMainLatitudeNumber.text =
                    it.latitude.toString()
                binding.contentMainCoordinators.textMainLongitudeNumer.text =
                    it.longitude.toString()
            }
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_CODE_REQUEST
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