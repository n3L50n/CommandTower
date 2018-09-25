package com.nodecoyote.commandtower

import android.animation.ObjectAnimator
import android.graphics.Point
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.FlingAnimation
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.nodecoyote.commandtower.fragments.CreatePlayerFragment
import com.nodecoyote.commandtower.fragments.FormatFragment
import com.nodecoyote.commandtower.fragments.MainButtonFragment
import com.nodecoyote.commandtower.fragments.MainButtonService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainButtonService {

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
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
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

    private fun setUpTouchHandler() {

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

            override fun onSingleTapUp(e: MotionEvent?): Boolean {

                val createPlayerFragment = supportFragmentManager.findFragmentByTag(Navigation.CreatePlayer.name)
                if (!supportFragmentManager.fragments.contains(createPlayerFragment)) {
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.createPlayerContainer, CreatePlayerFragment(), Navigation.CreatePlayer.name)
                            .commit()
                    createPlayerContainer.visibility = View.VISIBLE
                }
                //           mainButtonService.handleClick(MainButtonStatus.CreateAPlayer)
                return super.onSingleTapUp(e)
            }
        }

        val gestureDetector = GestureDetector(this, gestureListener)

        main_button_container.setOnTouchListener { _, motionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
        }

        val point = Point()
        windowManager?.defaultDisplay?.getSize(point)
        mMainButtonX = point.x.toFloat()
        mMainButtonY = point.y.toFloat()

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
//    //1
//    val objectAnimator = ObjectAnimator.ofObject(
//            frameLayout,
//            "backgroundColor",
//            ArgbEvaluator(),
//            ContextCompat.getColor(this, R.color.background_from),
//            ContextCompat.getColor(this, R.color.background_to)
//    )
//
//// 2
//    objectAnimator.repeatCount = 1
//    objectAnimator.repeatMode = ValueAnimator.REVERSE
//
//// 3
//    objectAnimator.duration = BaseAnimationActivity.Companion.DEFAULT_ANIMATION_DURATION
//    objectAnimator.start()

    private fun animateMainButton() {

        supportFragmentManager
                .beginTransaction()
                .add(R.id.main_button_container, MainButtonFragment(), Navigation.MainButton.name)
                .setCustomAnimations(R.animator.create_button_slow_in, R.animator.create_button_slow_out)
                .commit()

        val point = Point()
        windowManager?.defaultDisplay?.getSize(point)
        val screenWidth = point.x.toFloat()
        val screenHeight = point.y.toFloat()

        val xAnimator = ObjectAnimator.ofFloat(
                main_button_container,
                "translationX",
                screenWidth,
                screenWidth - (screenWidth/1.5f)

        )

        val yAnimator = ObjectAnimator.ofFloat(
                main_button_container,
                "translationY",

                screenHeight,
                screenHeight - (screenHeight/3)
        )

        yAnimator.duration = 1200
        yAnimator.start()

        xAnimator.duration = 1200
        xAnimator.start()

        main_button_container.visibility = View.VISIBLE

    }

}
