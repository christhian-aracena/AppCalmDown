package com.miempresa.appcalmdown

import android.content.Context
import android.media.MediaPlayer

class SoundPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null

    fun playSound(soundResourceId: Int) {
        stopSound()

        mediaPlayer = MediaPlayer.create(context, soundResourceId)
        mediaPlayer?.start()

        mediaPlayer?.setOnCompletionListener {
            stopSound()
        }
    }

    fun stopSound() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        mediaPlayer = null
    }
}