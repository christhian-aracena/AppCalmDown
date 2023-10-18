package com.miempresa.appcalmdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var soundPlayer: SoundPlayer
    private lateinit var switchRandomMessages: Switch
    private lateinit var randomMessageTextView: TextView
    private val messages = listOf(
        "Respira hondo. La calma es tu aliada, no importa cuán complicado parezca todo.",

        "Enfrenta un problema a la vez. No dejes que el caos te abrume.",

        "Recuerda que el estrés no arregla nada. Actúa con calma y piensa con claridad.",

        "Las cosas rara vez son tan malas como parecen. No dejes que la ansiedad tome el control.",

        "En lugar de preocuparte por el futuro, céntrate en el siguiente paso que puedes dar.",

        "A veces, el mejor plan es simplemente respirar, aceptar y seguir adelante.",

        "Las soluciones no suelen llegar cuando estás nervioso. Date un momento, relájate y luego toma decisiones.",

        "La paciencia es una habilidad. Desarrolla la tuya y verás cómo las cosas se acomodan.",

        "Si algo está fuera de tu control, déjalo ir. La calma viene cuando aceptas lo inevitable.",

        "Mantén la perspectiva. Lo que parece un gran problema hoy, puede ser insignificante mañana."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundPlayer = SoundPlayer(this)
        switchRandomMessages = findViewById(R.id.switchRandomMessages)
        randomMessageTextView = findViewById(R.id.randomMessageTextView)

        // Configura el evento de cambio de estado del Switch
        switchRandomMessages.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                randomMessageTextView.setText(messages.random())
                startRandomMessageUpdates()
            } else {
                stopRandomMessageUpdates()

                randomMessageTextView.setText("")
            }
        }

        findViewById<View>(R.id.btnRain).setOnClickListener { soundPlayer.playSound(R.raw.lluvia) }
        findViewById<View>(R.id.btnWaves).setOnClickListener { soundPlayer.playSound(R.raw.olas_mar) }
        findViewById<View>(R.id.btnBirds).setOnClickListener { soundPlayer.playSound(R.raw.pajaros) }
        findViewById<View>(R.id.btnStop).setOnClickListener { soundPlayer.stopSound() }
    }

    private fun startRandomMessageUpdates() {
        val handler = Handler(Looper.getMainLooper())
        val delay = 8000L // 10 segundos

        val runnable = object : Runnable {
            override fun run() {
                if (switchRandomMessages.isChecked) {
                    val randomMessage = messages.random()
                    randomMessageTextView.text = randomMessage
                    handler.postDelayed(this, delay)
                }
            }
        }

        handler.postDelayed(runnable, delay)
    }

    private fun stopRandomMessageUpdates() {
        randomMessageTextView.setText("")
    }
}