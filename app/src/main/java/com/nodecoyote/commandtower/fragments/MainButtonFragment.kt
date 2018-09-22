package com.nodecoyote.commandtower.fragments

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import com.nodecoyote.commandtower.Navigation
import com.nodecoyote.commandtower.R
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main_button.*

interface MainButtonService{
    val mainButtonService
        get() = MainButtonServiceObject.mainButtonService
}

class MainButtonServiceObject{
    companion object {
        val mainButtonService: MainButton = MainButtonServiceImplementation()
    }
}

interface MainButton {
    /**
     * Update current status of Main Button
     */
    val status: MainButtonStatus
    val rxStatus: Flowable<MainButtonStatus>
    fun handleClick(status: MainButtonStatus)
}

class MainButtonServiceImplementation: MainButton {

    private val mRxStatus = BehaviorProcessor.createDefault(MainButtonStatus.CreateAPlayer)
    override val rxStatus: Flowable<MainButtonStatus>
        get() = mRxStatus.distinctUntilChanged()
    override val status: MainButtonStatus
        get() = mRxStatus.value

    override fun handleClick(status: MainButtonStatus) {
        when(status) {
            MainButtonStatus.CreateAPlayer -> {

            }
            else -> ""
        }
    }
}

enum class MainButtonStatus{CreateAPlayer,CreateThisPlayer,StartGame,EndGame}

class MainButtonFragment: Fragment(), MainButtonService {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) {
            return null
        }
        return inflater.inflate(R.layout.fragment_main_button, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMainButton()
    }

    private fun setUpMainButton() {
        mainButtonService.rxStatus.subscribe { status ->
            when(status) {
                MainButtonStatus.CreateAPlayer -> {
                    create_player_text_view.text = context?.resources?.getString(R.string.create_player)
                }
                else -> create_player_text_view.text = "Incorrect Status"
            }
        }
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

        activity?.let { activity ->
            val createPlayerFragment = activity.supportFragmentManager.findFragmentByTag(Navigation.CreatePlayer.name)
            if (!activity.supportFragmentManager.fragments.contains(createPlayerFragment)) {
                activity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.createPlayerContainer, CreatePlayerFragment(), Navigation.CreatePlayer.name)
                        .commit()
                createPlayerContainer.visibility = View.VISIBLE
            }
        }


    }


}