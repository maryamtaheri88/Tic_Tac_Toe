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

     enum class Turn{
         NOUGHT,CROSS
     }

    private var firstTure = Turn.CROSS
    private var currentTurn =Turn.CROSS

    private  var crossesScore = 0
    private  var noughtsScore = 0


    private var boardList= mutableListOf<Button>()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard(){
        boardList.add(binding.button1)
        boardList.add(binding.button2)
        boardList.add(binding.button3)
        boardList.add(binding.button4)
        boardList.add(binding.button5)
        boardList.add(binding.button6)
        boardList.add(binding.button7)
        boardList.add(binding.button8)
        boardList.add(binding.button9)
        //boardList.add(binding.buttonrestgame)
    }

    fun boardTapped(view: View){

        if(view !is Button)
            return
        addToBoard(view)

      if(chexkForVictory(NOUGHT)){
          noughtsScore++
         result ("Noughts Win!")
      }

        if(chexkForVictory(CROSS)){
            crossesScore ++
            result ("CROSSes Win!")
        }

      if (fullBoard()){
          result("Drow")
      }
    }

    private fun chexkForVictory(s: String): Boolean {

        //Horizantal Victori
        if (match(binding.button1,s) && match(binding.button2,s) && match(binding.button3,s))
            return true
        if (match(binding.button4,s) && match(binding.button5,s) && match(binding.button6,s))
            return true
        if (match(binding.button7,s) && match(binding.button8,s) && match(binding.button9,s))
            return true

        //Vertical Victori
        if (match(binding.button1,s) && match(binding.button4,s) && match(binding.button7,s))
            return true
        if (match(binding.button2,s) && match(binding.button5,s) && match(binding.button8,s))
            return true
        if (match(binding.button3,s) && match(binding.button6,s) && match(binding.button9,s))
            return true

        //Diaganal Victory
        if (match(binding.button1,s) && match(binding.button5,s) && match(binding.button9,s))
            return true
        if (match(binding.button3,s) && match(binding.button5,s) && match(binding.button7,s))
            return true
    }

    private fun match(button:Button , symbol : String):Boolean= button.text ==symbol

    private fun result(title: String) {
        val messsage = "\nNoughts $noughtsScore \n\nCrosses $crossesScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(messsage)
            .setPositiveButton("Reset"){
                _,_ ->
                restBoard()
            }
            setCancelsble(false)
            .show()
    }

    private fun restBoard() {
        for(button in boardList){
            button.text = ""
        }

        if(firstTure == Turn.NOUGHT)
            firstTure = Turn.CROSS
        else if (firstTure == Turn.CROSS)
            firstTure = Turn.NOUGHT
    }

    private fun fullBoard(): Boolean {
        for(button in boardList){
            if(button.text == "")
                return false
        }
        return true
    }

    private fun addToBoard(button: Button) {
        if (button.text != "")
            return

        if(currentTurn==Turn.NOUGHT){
            button.text =NOUGHT
            currentTurn = Turn.CROSS
        }
        else if (currentTurn==Turn.CROSS){
            button.text =CROSS
            currentTurn = Turn.NOUGHT
        }
        setTurnLable()

        companian object {
            val NOUGHT = "O"
            val CROSS = "X"


        }
    }

    private fun setTurnLable() {
        var turnText= ""
        if(currentTurn == Turn.CROSS)
            turnText = "Turn $CROSS"
        if(currentTurn == Turn.NOUGHT)
            turnText = "Turn $NOUGHT"

        binding.turnTv.text =turnText
    }

    fun boardTApped(view: View) {}
}