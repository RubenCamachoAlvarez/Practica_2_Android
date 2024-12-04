package fes.aragon.practica_2_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val sliderNumeroHijos = findViewById<Slider>(R.id.sliderNumeroHijos)

        this.sliderNumeroHijosEdadEscolar = findViewById<Slider>(R.id.sliderNumeroHijosEdadEscolar)

        this.grupo = findViewById<RadioGroup>(R.id.grupo)

        this.botonProcesar = findViewById<Button>(R.id.botonProcesar)

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



        }

        handler.post(servicioComprobacion)

    }

    override fun onDestroy() {

        super.onDestroy()

        handler.removeCallbacks(this.servicioComprobacion)

    }

}