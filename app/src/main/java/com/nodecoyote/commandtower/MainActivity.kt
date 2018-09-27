package com.nodecoyote.commandtower

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val _tag = "MainActivity"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.formatFragmentAction -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.playerListAction -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.historyFragmentAction-> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val finalHost = NavHostFragment.create(R.navigation.navigation_graph)
        supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, finalHost)
                .setPrimaryNavigationFragment(finalHost)
                .commit()

        val navController = findNavController(R.id.nav_host_fragment)
        bottom_navigation.setupWithNavController(navController)
    }

}