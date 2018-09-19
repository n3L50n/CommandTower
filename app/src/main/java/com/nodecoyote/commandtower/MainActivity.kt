package com.nodecoyote.commandtower

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = FormatFragment()
                supportFragmentManager.beginTransaction().replace(R.id.formatContainer, fragment, Navigation.Formats.name).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.fragments.forEach {
                    if (it.tag == Navigation.Formats.name) {
                        supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentByTag(Navigation.Formats.name)).commit()
                    }
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                supportFragmentManager.fragments.forEach {
                    if (it.tag == Navigation.Formats.name) {
                        supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentByTag(Navigation.Formats.name)).commit()
                    }
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
