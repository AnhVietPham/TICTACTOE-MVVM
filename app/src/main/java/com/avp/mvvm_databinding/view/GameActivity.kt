package com.avp.mvvm_databinding.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.avp.mvvm_databinding.R
import com.avp.mvvm_databinding.databinding.ActivityGameBinding
import com.avp.mvvm_databinding.model.Player
import com.avp.mvvm_databinding.utils.StringUtility.Companion.isNullOrEmpty
import com.avp.mvvm_databinding.viewmodel.GameViewModel


class GameActivity : AppCompatActivity() {

    private val GAME_BEGIN_DIALOG_TAG = "game_dialog_tag"
    private val GAME_END_DIALOG_TAG = "game_end_dialog_tag"
    private val NO_WINNER = "No one"
    private lateinit var gameViewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        promptForPlayers()
    }

    fun promptForPlayers() {
        val beginDialog = GameBeginDialog.newInstance(this)
        beginDialog.isCancelable = false
        beginDialog.show(supportFragmentManager, GAME_BEGIN_DIALOG_TAG)
    }

    fun onPlayersSet(player1: String, player2: String) {
        initDataBinding(player1, player2)
    }

    private fun initDataBinding(player1: String, player2: String) {
        val activityGameBinding = DataBindingUtil.setContentView<ActivityGameBinding>(this, R.layout.activity_game)
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        gameViewModel.init(player1, player2)
        activityGameBinding.gameViewModel = gameViewModel
        setUpOnGameEndListener()
    }

    private fun setUpOnGameEndListener() {
        gameViewModel.getWinner().observe(this, Observer { player ->
            if (player != null) {
                onGameWinnerChanged(player)
            } else {
                showDialogEndGame("No Winner")
            }
        })
    }

    private fun onGameWinnerChanged(winner: Player) {
        val winnerName = if (isNullOrEmpty(winner.name)) NO_WINNER else winner.name
        showDialogEndGame(winnerName)
    }

    private fun showDialogEndGame(nameWinner: String) {
        val dialog = GameEndDialog.newInstance(this, nameWinner)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_END_DIALOG_TAG)
    }
}