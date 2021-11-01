package com.alinecruz.geolocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alinecruz.geolocation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonMainWhereAmI.setOnClickListener {
            setupVisibility()
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