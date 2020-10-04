package com.example.madlevel4task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            menuBtnToggle(menu)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_history -> {
                navController.navigate(R.id.action_FirstFragment_to_resultsFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun menuBtnToggle(menu: Menu) {
        val historyButton = menu.findItem(R.id.btn_history)
        val clearFights = menu.findItem(R.id.clear_fights)
        navController.addOnDestinationChangedListener { _, destination,
                                                        _ ->
            if (destination.id in arrayOf(R.id.resultsFragment)) {
                historyButton.isVisible = false
                clearFights.isVisible = true
            } else if (destination.id in arrayOf(R.id.FirstFragment)) {
                historyButton.isVisible = true
                clearFights.isVisible = false
            }
        }
    }
}
