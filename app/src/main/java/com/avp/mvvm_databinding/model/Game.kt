package com.avp.mvvm_databinding.model

import androidx.lifecycle.MutableLiveData

class Game(
    playerOne: String,
    playerTwo: String) {

    companion object {
        private val TAG = Game::class.java.simpleName
        private const val BOARD_SIZE = 3
    }

    var player1: Player?
    var player2: Player?
    var currentPlayer: Player?
    var cells: Array<Array<Cell?>>
    var winner: MutableLiveData<Player> = MutableLiveData()

    init {
        cells = Array(BOARD_SIZE) { arrayOfNulls<Cell>(BOARD_SIZE) }
        player1 = Player(name = playerOne, value = "x")
        player2 = Player(name = playerTwo, value = "o")
        currentPlayer = player1
    }

    fun switchPlayer() {
        currentPlayer = if (currentPlayer == player1) player2 else player1
    }

    fun hasGameEnded(): Boolean {
        if (hasThreeSameDiagonalCells() || hasThreeSameHorizontalCells() || hasThreeSameVerticalCells()) {
            winner.value = currentPlayer
            return true
        }

        if (isBoardFull()) {
            winner.value = null
            return true
        }
        return false
    }

    private fun hasThreeSameHorizontalCells(): Boolean {
        for (i in 0 until BOARD_SIZE) {
            if (areEquals(cells[i][0], cells[i][1], cells[i][2]))
                return true
        }
        return false
    }

    private fun hasThreeSameVerticalCells(): Boolean {
        for (i in 0 until BOARD_SIZE) {
            if (areEquals(cells[0][i], cells[1][i], cells[2][i]))
                return true
        }
        return false
    }

    private fun hasThreeSameDiagonalCells(): Boolean {
        return areEquals(cells[0][0], cells[1][1], cells[2][2]) || areEquals(cells[0][2], cells[1][1], cells[2][0])
    }

    private fun isBoardFull(): Boolean {
        for (row in cells) {
            for (cell in row) {
                if (cell == null || cell.player.value.isEmpty())
                    return false
            }
        }
        return true
    }


    private fun areEquals(vararg cells: Cell?): Boolean {
        if (cells.isEmpty()) {
            return false
        }

        cells.forEach { cell ->
            if (cell == null || cell.player.value.isEmpty()) {
                return false
            }
        }

        val comparisonBase = cells[0]
        cells.forEach { cell ->
            if (!comparisonBase?.player?.value.equals(cell?.player?.value))
                return false
        }

        return true
    }

    fun reset(){
        player1 = null
        player2 = null
        currentPlayer = null
        cells = emptyArray()
    }
}