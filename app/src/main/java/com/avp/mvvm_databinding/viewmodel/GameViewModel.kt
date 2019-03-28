package com.avp.mvvm_databinding.viewmodel

import android.databinding.ObservableArrayMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.avp.mvvm_databinding.model.Cell
import com.avp.mvvm_databinding.model.Game
import com.avp.mvvm_databinding.model.Player

class GameViewModel : ViewModel() {
    private lateinit var cells: ObservableArrayMap<String, String>
    private lateinit var game: Game

    fun init(player1: String, player2: String) {
        game = Game(player1, player2)
        cells = ObservableArrayMap()
    }

    fun onClickedCellAt(row: Int, column: Int){
        if (game.cells[row][column] == null){
            game.cells[row][column] = Cell(game.currentPlayer)
            cells
        }
    }

    fun getWinner() : LiveData<Player>{
        return game.winner
    }
}