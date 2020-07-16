package com.shwetank.nasamarsphotos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.shwetank.nasamarsphotos.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel: MarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (viewModel.isDarkMode) {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        } else {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        closeOptionsMenu()

        when(item.itemId){
            R.id.dark -> {
                viewModel.isDarkMode = true
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            }
            R.id.light -> {
                viewModel.isDarkMode = false
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
                return true
            }
        }
        return false
    }
}