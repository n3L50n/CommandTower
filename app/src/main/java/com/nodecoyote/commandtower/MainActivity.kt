package com.nodecoyote.commandtower

import android.graphics.Point
import android.graphics.PointF
import android.os.Build
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewAnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_button.*

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

    private fun createNewPlayer(){

        val itemView = main_button_container
        val factor = 2

        val finalRadius = Math.hypot(itemView.width.toDouble(), itemView.height.toDouble()).toInt()

        if (Build.VERSION.SDK_INT >= 21) {

            val animator = ViewAnimationUtils.createCircularReveal (
                    itemView,
                    itemView.width / factor,
                    itemView.height / factor,
                    0f,
                    finalRadius.toFloat()
            )
            animator.start()
        }

        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment.tag == Navigation.CreatePlayer.name) {
                return@forEach
            }
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.createPlayerContainer, CreatePlayerFragment(), Navigation.CreatePlayer.name)
                    .commit()
        }

        createPlayerContainer.visibility = View.VISIBLE

    }

    private fun setUpTouchHandler(){
        main_button_container.setOnTouchListener { view, motionEvent ->

            val f = PointF()

            view.x
            view.y

            f.x = motionEvent.x
            f.y = motionEvent.y
            mMainButtonPosition = f.length()
            mMainButtonX = motionEvent.rawX
            mMainButtonY = motionEvent.rawY

            Log.v(_tag, "::::::: ${view.x}${view.y}")

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
                    createNewPlayer()
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

        Log.v(_tag,"X:Y::: $mMainButtonX $mMainButtonY ")

        val xAnim = SpringAnimation(main_button_container, DynamicAnimation.X)
                .setStartValue(mMainButtonX)
                .setSpring(spring)

        val yAnim = SpringAnimation(main_button_container, DynamicAnimation.Y)
                .setStartValue(mMainButtonY)
                .setSpring(spring)

        xAnim.start()
        yAnim.start()


        supportFragmentManager
                .beginTransaction()
                .add(R.id.main_button_container, MainButtonFragment(), Navigation.MainButton.name)
                .setCustomAnimations(R.anim.create_button_in, R.anim.create_button_out)
                .commit()

        main_button_container.visibility = View.VISIBLE

    }

}
