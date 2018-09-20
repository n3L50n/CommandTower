package com.nodecoyote.commandtower

import android.graphics.Point
import android.graphics.PointF
import android.os.Build
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.FlingAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_button.*

class MainActivity : AppCompatActivity() {

    private val _tag = "MainActivity"

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

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        animateMainButton()

        setUpTouchHandler()
    }

    private fun setUpTouchHandler(){

        val gestureListener = object : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent?): Boolean {
                return true
            }

            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {

                flingAnimationX.setStartVelocity(velocityX)
                flingAnimationY.setStartVelocity(velocityY)

                flingAnimationX.start()
                flingAnimationY.start()

                return true
            }
        }

        val gestureDetector = GestureDetector(this, gestureListener)

        main_button_container.setOnTouchListener { _, motionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
        }

        val point = Point()
        windowManager?.defaultDisplay?.getSize(point)
        mMainButtonX = point.x.toFloat()
        mMainButtonY = point.x.toFloat()

        main_button_container.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                flingAnimationX.setMinValue(0f).setMaxValue((mMainButtonX))
                flingAnimationY.setMinValue(0f).setMaxValue((mMainButtonY))
                main_button_container.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }

    val flingAnimationX: FlingAnimation by lazy(LazyThreadSafetyMode.NONE) {
        FlingAnimation(main_button_container, DynamicAnimation.TRANSLATION_X).setFriction(1.1f)
    }

    val flingAnimationY: FlingAnimation by lazy(LazyThreadSafetyMode.NONE) {
        FlingAnimation(main_button_container, DynamicAnimation.TRANSLATION_Y).setFriction(1.1f)
    }

    private fun animateMainButton(){



        supportFragmentManager
                .beginTransaction()
                .add(R.id.main_button_container, MainButtonFragment(), Navigation.MainButton.name)
                .commit()

        main_button_container.visibility = View.VISIBLE

    }

}
