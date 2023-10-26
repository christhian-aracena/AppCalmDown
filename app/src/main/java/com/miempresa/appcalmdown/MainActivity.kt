package com.miempresa.appcalmdown

import android.content.Intent
import android.content.pm.ActivityInfo
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
    private val handler = Handler(Looper.getMainLooper())
    private val delay = 7000L // 8 segundos
    private var runnable: Runnable? = null

    private val messages = listOf(

        //lista inmutable para almacenar las frases.
        "Respira hondo. La calma es tu aliada, no importa cuán complicado parezca todo.",
        "Enfrenta un problema a la vez. No dejes que el caos te abrume.",
        "Recuerda que el estrés no arregla nada. Actúa con calma y piensa con claridad.",
        "Las cosas rara vez son tan malas como parecen. No dejes que la ansiedad tome el control.",
        "En lugar de preocuparte por el futuro, céntrate en el siguiente paso que puedes dar.",
        "A veces, el mejor plan es simplemente respirar, aceptar y seguir adelante.",
        "Las soluciones no suelen llegar cuando estás nervioso. Date un momento, relájate y luego toma decisiones.",
        "La paciencia es una habilidad. Desarrolla la tuya y verás cómo las cosas se acomodan.",
        "Si algo está fuera de tu control, déjalo ir. La calma viene cuando aceptas lo inevitable.",
        "Mantén la perspectiva. Lo que parece un gran problema hoy, puede ser insignificante mañana.",
        "La serenidad es la clave para superar cualquier desafío. Respira y mantén la calma.",
        "Cada problema tiene una solución. Enfócate en encontrarla paso a paso.",
        "La tranquilidad de la mente es tu mejor aliada en tiempos difíciles.",
        "No dejes que la tormenta te hunda. Después de la lluvia, siempre llega la calma.",
        "En lugar de preocuparte por lo que podría salir mal, concéntrate en lo que puede salir bien.",
        "Aprende a disfrutar del proceso, incluso cuando las cosas no salen como planeas.",
        "La sabiduría está en saber cuándo tomar un respiro. La calma precede a la claridad.",
        "La paciencia es como un músculo; cuanto más la ejercitas, más fuerte se vuelve.",
        "Deja de luchar contra lo inevitable. A veces, la calma surge cuando dejas ir.",
        "Cada montaña se puede escalar paso a paso. Mantén la vista en la cima.",
        "No permitas que los problemas de hoy te roben la paz del mañana.",
        "La vida es como un río. A veces fluye suavemente, a veces hay rápidos, pero siempre sigue adelante.",
        "Ante la adversidad, mantén la calma. En la calma, encontrarás la fuerza.",
        "La paz interior es el tesoro más valioso. Cultívala con cada respiración.",
        "En lugar de buscar respuestas inmediatas, busca comprender y luego actuar.",
        "La verdadera fortaleza radica en la capacidad de mantener la calma en medio de la tormenta.",
        "El tiempo que pasas preocupándote es tiempo que pierdes para ser feliz.",
        "La calma es la brújula que te guía a través de los momentos difíciles.",
        "Aprende a bailar bajo la lluvia en lugar de esperar a que pase la tormenta.",
        "Las pequeñas pausas son la melodía que suaviza la sinfonía de la vida."
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        soundPlayer = SoundPlayer(this)
        switchRandomMessages = findViewById(R.id.switchRandomMessages)
        randomMessageTextView = findViewById(R.id.randomMessageTextView)


        switchRandomMessages.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                randomMessageTextView.text = messages.random()
                startRandomMessageUpdates()
            } else {
                randomMessageTextView.setText("")
                stopRandomMessageUpdates()
            }
        }

        findViewById<View>(R.id.btnRain).setOnClickListener { soundPlayer.playSound(R.raw.lluvia) }
        findViewById<View>(R.id.btnWaves).setOnClickListener { soundPlayer.playSound(R.raw.olas_mar) }
        findViewById<View>(R.id.btnBirds).setOnClickListener { soundPlayer.playSound(R.raw.pajaros) }
        findViewById<View>(R.id.btnStop).setOnClickListener { soundPlayer.stopSound() }

    }

    private fun startRandomMessageUpdates() {
        stopRandomMessageUpdates() // Detener el anterior antes de iniciar uno nuevo

        runnable = object : Runnable {
            override fun run() {
                if (switchRandomMessages.isChecked) {
                    val randomMessage = messages.random()
                    randomMessageTextView.text = randomMessage
                    handler.postDelayed(this, delay)
                }
            }
        }

        handler.postDelayed(runnable!!, delay)
    }

    private fun stopRandomMessageUpdates() {

        //por medio de la propiedad removeCallbacks se eliminan las instancias generadas por el Handler.
        //evitando que se acumulen multiples instancias.
        //it, es el Runnable actual que maneja el hilo del handler.
        runnable?.let {
            handler.removeCallbacks(it)
        }
    }



    fun goHome(view: View) {
        soundPlayer.stopSound()
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }
}