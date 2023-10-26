package com.miempresa.appcalmdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class Meditation : AppCompatActivity() {

    private lateinit var soundPlayers: SoundPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditation)
        soundPlayers = SoundPlayer(this)
        findViewById<View>(R.id.startMeditation).setOnClickListener { soundPlayers.playSound(R.raw.meditacion_guiada) }

    }
    fun goHome(view: View) {
        soundPlayers.stopSound()
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }

}