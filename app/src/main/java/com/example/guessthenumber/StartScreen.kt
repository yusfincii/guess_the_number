package com.example.guessthenumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.guessthenumber.databinding.StartScreenBinding

class StartScreen : AppCompatActivity() {
    private lateinit var startScreen : StartScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startScreen = StartScreenBinding.inflate(layoutInflater)
        setContentView(startScreen.root)

        startScreen.buttonStart.setOnClickListener{
            val intent = Intent(this, GameScreen::class.java)
            startActivity(intent)
        }
    }
}