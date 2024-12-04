package fes.aragon.practica_2_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    private val servicioComprobacion = object : Runnable {

        override fun run() {

            botonProcesar.isEnabled = sliderNumeroHijosEdadEscolar.isEnabled && grupo.checkedRadioButtonId != -1

            handler.postDelayed(this, 500)

        }

    }

    private lateinit var sliderNumeroHijosEdadEscolar : Slider

    private lateinit var grupo : RadioGroup

    private lateinit var botonProcesar : Button

    private lateinit var botonViuda : RadioButton

    private lateinit var botonOtro : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val sliderNumeroHijos = findViewById<Slider>(R.id.sliderNumeroHijos)

        this.sliderNumeroHijosEdadEscolar = findViewById<Slider>(R.id.sliderNumeroHijosEdadEscolar)

        this.grupo = findViewById<RadioGroup>(R.id.grupo)

        this.botonProcesar = findViewById<Button>(R.id.botonProcesar)

        this.botonViuda = findViewById<RadioButton>(R.id.botonViuda)

        this.botonOtro = findViewById<RadioButton>(R.id.botonOtro)

        sliderNumeroHijos.addOnChangeListener {_, value, _ ->

            if(sliderNumeroHijosEdadEscolar.value > value)

                sliderNumeroHijosEdadEscolar.value = value

            if(value == 0.0f) {

                sliderNumeroHijosEdadEscolar.isEnabled = false

            }else{

                sliderNumeroHijosEdadEscolar.valueTo = value

                sliderNumeroHijosEdadEscolar.isEnabled = true

            }


        }

        botonProcesar.setOnClickListener {

            val nuevaVista = Intent(this, ActivityInformacion::class.java)

            nuevaVista.putExtra("Numero hijos", sliderNumeroHijos.value)

            nuevaVista.putExtra("Numero hijos edad escolar", sliderNumeroHijosEdadEscolar.value)

            nuevaVista.putExtra("Subsidio estado civil", this.botonViuda.isChecked)

            startActivity(nuevaVista)

            finish()

        }

        handler.post(servicioComprobacion)

    }

    override fun onDestroy() {

        super.onDestroy()

        handler.removeCallbacks(this.servicioComprobacion)

        Log.i("DESTRUYENDO", "Destruyendo main activity")

    }

}