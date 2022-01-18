package com.example.chik_chika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class TimelineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val controller = findNavController(R.id.nav_host_fragment_timeline)

        val appBarConfig = AppBarConfiguration(

            setOf(
                R.id.timeline,
                R.id.profile
            )
        )

//        setupActionBarWithNavController(controller, appBarConfig)
//        navView.setupWithNavController(controller)

        setupActionBarWithNavController(controller, appBarConfig)
        navView.setupWithNavController(controller)

    }


}