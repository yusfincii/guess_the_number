package com.example.guessthenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.guessthenumber.databinding.GameScreenBinding
import kotlin.random.Random

class GameScreen : AppCompatActivity() {
    private lateinit var gameScreen : GameScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        gameScreen = GameScreenBinding.inflate(layoutInflater)
        setContentView(gameScreen.root)

        // variable will refer game's win or lose situation
        var winState = true

        // target number specifying
        val randomNumber = Random.nextInt(0,101)
        Log.e("yusuf", "Random Number : $randomNumber")

        // counter for remain guesses
        var remainGuess = 5
        gameScreen.remainGuess.text = remainGuess.toString()

        gameScreen.buttonSubmit.setOnClickListener{
            // taking input
            val predicted = gameScreen.guessNumber.text.toString()

            // when user not enter a number
            if(predicted.equals("")){
                // alert
                Toast.makeText(applicationContext, "Please enter a number!", Toast.LENGTH_SHORT).show()
            }
            else{
                val numberGuessed = predicted.toInt()

                // the situation of predicted number less than target number
                if(numberGuessed < randomNumber){
                    // specifying information message
                    gameScreen.incOrDec.text = getText(R.string.incrase)
                    // specifying information message colour
                    gameScreen.incOrDec.setTextColor(resources.getColor(R.color.increase))
                    // counter decrementing
                    remainGuess--
                    gameScreen.remainGuess.text = remainGuess.toString()
                }

                // case of predicted number will be greater than the target number
                else if(numberGuessed > randomNumber){
                    // specifying information message
                    gameScreen.incOrDec.text = getText(R.string.decrease)
                    // specifying information message color
                    gameScreen.incOrDec.setTextColor(getResources().getColor(R.color.decrease))
                    // counter decrementing
                    remainGuess--
                    gameScreen.remainGuess.text = remainGuess.toString()
                }

                // when user have last guess chance
                // remaining guesss text will be red
                if(remainGuess == 1){
                    gameScreen.remainGuess.setTextColor(getResources().getColor(R.color.decrease))
                }

                // when guessed number equal to target number
                // win case
                if(numberGuessed == randomNumber){
                    val intent = Intent(this, ResultScreen::class.java)
                    // to block come previous(game screen) screen
                    finish()
                    // win
                    winState = true
                    // information to be sent the ResultScreen
                    intent.putExtra("resultKey", winState)
                    startActivity(intent)
                }

                // fail case (lose)
                if(remainGuess == 0){
                    val intent = Intent(this, ResultScreen::class.java)
                    // lose
                    winState = false
                    // to block come previous(game screen) screen
                    finish()
                    // information to be sent the ResultScreen
                    intent.putExtra("targetNumberKey", randomNumber)
                    intent.putExtra("resultKey", false)
                    startActivity(intent)
                }

                // to reset the text to hold the input at each guess
                gameScreen.guessNumber.setText("")
            }
        }
    }
}