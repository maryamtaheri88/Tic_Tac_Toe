package com.example.tictactoe

import android.app.Activity
import android.app.AlertDialog
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class Turn {
        NOUGHT,
        CROSS
    }

    private var firstTure = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private var crossesScore = 0
    private var noughtsScore = 0


    private var boardList = mutableListOf<Button>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    fun boardTapped(view: View) {

        if (view !is Button)
            return
        addToBoard(view)

        if (checkForVictiry(NOUGHT)){
            result("Noughts wins")
        }
        if (checkForVictiry(CROSS)){
            result("Cross wins")
        }

        if (fullBoard()) {
            result("Draw")
        }
    }

    private fun checkForVictiry(s: String): Boolean {


            if (match(binding.a1,s) && match(binding.a2,s) && match(binding.a3,s))
                return true




    }

    private fun match(button: Button,symbol:String):Boolean = button.text ==symbol

    private fun result(title: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setPositiveButton("Reset")
            { _, _ ->
                restBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun restBoard() {
        for (button in boardList) {
            button.text = ""
        }

        if (firstTure == Turn.NOUGHT)
            firstTure = Turn.CROSS
        else if (firstTure == Turn.CROSS)
            firstTure = Turn.NOUGHT

        currentTurn = firstTure
        setTurnLable()
    }

    private fun fullBoard(): Boolean {
        for (button in boardList) {
            if (button.text == "")
                return false
        }
        return true
    }

    private fun addToBoard(button: Button) {
        if (button.text != "")
            return

        if (currentTurn == Turn.NOUGHT) {
            button.text = NOUGHT
            currentTurn = Turn.CROSS
        }

        else if (currentTurn == Turn.CROSS) {
            button.text = CROSS
            currentTurn = Turn.NOUGHT
        }
        setTurnLable()
    }
    private fun setTurnLable() {
        var turnText= ""
        if(currentTurn == Turn.CROSS)
            turnText = "Turn $CROSS"
        if(currentTurn == Turn.NOUGHT)
            turnText = "Turn $NOUGHT"

        binding.turnTV.text =turnText
    }
    companion object{
        const val NOUGHT = "O"
        const val CROSS = "x"
    }
}
