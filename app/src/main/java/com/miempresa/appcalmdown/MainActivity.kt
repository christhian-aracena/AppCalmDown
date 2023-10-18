package com.miempresa.appcalmdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var soundPlayer: SoundPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundPlayer = SoundPlayer(this)

        findViewById<View>(R.id.btnRain).setOnClickListener { soundPlayer.playSound(R.raw.lluvia) }
        findViewById<View>(R.id.btnWaves).setOnClickListener { soundPlayer.playSound(R.raw.olas_mar) }
        findViewById<View>(R.id.btnBirds).setOnClickListener { soundPlayer.playSound(R.raw.pajaros) }
        findViewById<View>(R.id.btnStop).setOnClickListener { soundPlayer.stopSound() }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPlayer.stopSound()
    }
}
