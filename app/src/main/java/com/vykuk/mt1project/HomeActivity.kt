package com.vykuk.mt1project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import com.vykuk.mt1project.databinding.ActivityHomeBinding
import kotlin.math.log

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        binding.getMyIP.setOnClickListener{
            Log.i("IPLOCATOR", "Stisknuto tlačítko zjistit IP")
        }

        binding.locateIP.setOnClickListener{
            Log.i("IPLOCATOR", "Strisknuto tlačítko lokalizuj IP")
        }
    }
}