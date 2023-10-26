package com.miempresa.appcalmdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

private lateinit var soundPlayer: SoundPlayer

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)


    }

    fun startMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun goToMeditation(view: View) {
        val intent = Intent(this, Meditation::class.java)
        startActivity(intent)
    }

}