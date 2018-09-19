package com.nodecoyote.commandtower

import android.graphics.Point
import android.graphics.PointF
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_player.*

class MainActivity : AppCompatActivity() {

    private val _tag = "MainActivity"

    private var mMainButtonPosition = 850.57f
    private var mMainButtonX: Float = 0f
    private var mMainButtonY: Float = 0f

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

        val point = Point()
        windowManager?.defaultDisplay?.getSize(point)
        mMainButtonX = point.x.toFloat()
        mMainButtonY = point.x.toFloat()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        animateMainButton()
        setUpTouchHandler()
    }

    private fun setUpTouchHandler(){
        add_player_button_layout.setOnTouchListener { view, motionEvent ->

            val f = PointF()
            f.x = motionEvent.x
            f.y = motionEvent.y
            mMainButtonPosition = f.length()
            mMainButtonX = motionEvent.rawX
            mMainButtonY = motionEvent.rawY

            when(motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    true
                }
                MotionEvent.ACTION_UP -> {
                    Log.v(_tag, "Main position: $mMainButtonPosition")
                    animateMainButton()
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun animateMainButton(){

        val spring = SpringForce(mMainButtonPosition)
                .setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_LOW)

        Log.v(_tag," SYSTEM: ::: $mMainButtonX $mMainButtonY ")

        val xAnim = SpringAnimation(add_player_button_layout, DynamicAnimation.X)
                .setStartValue(mMainButtonX)
                .setSpring(spring)

        val yAnim = SpringAnimation(add_player_button_layout, DynamicAnimation.Y)
                .setStartValue(mMainButtonY)
                .setSpring(spring)

        xAnim.start()
        yAnim.start()

        add_player_button_layout.visibility = View.VISIBLE

    }

}
