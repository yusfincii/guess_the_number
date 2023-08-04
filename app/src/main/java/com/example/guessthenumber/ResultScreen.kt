package com.example.guessthenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.guessthenumber.databinding.ResultScreenBinding

class ResultScreen : AppCompatActivity() {
    private lateinit var resultScreen : ResultScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultScreen = ResultScreenBinding.inflate(layoutInflater)
        setContentView(resultScreen.root)

        // informations from GameScreen
        val winState = intent.getBooleanExtra("resultKey", true)
        val targetNumber = intent.getIntExtra("targetNumberKey", 0)

        // winState false case
        // when user lost the game some ResultScreen properties will be change
        // background turns the red
        // win or lose text will be change
        // win or lose face image will be change
        // target number will display in the screen
        // play again button's text color will be change
        if(winState == false){
            resultScreen.root.setBackgroundColor(getResources().getColor(R.color.loseBackground))
            resultScreen.resultText.text = getText(R.string.lose)
            resultScreen.resultImage.setImageResource(R.drawable.sad_face)
            resultScreen.buttonPlayAgain.setTextColor(getResources().getColor(R.color.loseBackground))
            val text = "${getText(R.string.targetNumber)} $targetNumber"
            resultScreen.targetNumber.setText(text)
        }

        resultScreen.buttonPlayAgain.setOnClickListener{
            val intent = Intent(this, StartScreen::class.java)
            // to block turn to ResultScreen
            finish()
            startActivity(intent)
        }
    }
}