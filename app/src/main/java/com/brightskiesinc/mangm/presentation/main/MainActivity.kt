package com.brightskiesinc.mangm.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.brightskiesinc.mangmapp.R
import com.brightskiesinc.mangmapp.databinding.ActivityMangmMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMangmMainBinding

    private lateinit var mController: NavController


    private val mNavListener =
        NavController.OnDestinationChangedListener { controller, destination, _ ->
            if (destination.label != null) {
              //  binding.toolbar.title = destination.label
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangmMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavController()
        binding.root.isActivated
        setSupportActionBar(binding.toolbar)

    }

    private fun setupNavController() {
        val host =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        mController = host.navController
        mController.addOnDestinationChangedListener(mNavListener)
    }
}
