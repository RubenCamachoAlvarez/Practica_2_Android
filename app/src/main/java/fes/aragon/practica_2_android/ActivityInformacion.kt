package fes.aragon.practica_2_android

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ActivityInformacion : AppCompatActivity() {

    private lateinit var campoSubsidioNumeroHijos : EditText

    private lateinit var campoSubsidioHijosEdadEscolar : EditText

    private lateinit var campoSubsidioTotal : EditText

    private lateinit var campoSubsidioEstadoCivil : EditText

    private lateinit var botonRegresar : Button

    private var numeroHijos : Float = 0.0f

    private var numeroHijosEdadEscolar : Float = 0.0f

    private var subsidioEstadoCivil : Boolean = false

    private fun getInformationSendByParentActivity() {

        this.numeroHijos = this.intent.getFloatExtra("Numero hijos", 0.0f)

        this.numeroHijosEdadEscolar = this.intent.getFloatExtra("Numero hijos edad escolar", 0.0f)

        this. subsidioEstadoCivil = this.intent.getBooleanExtra("Subsidio estado civil", false)

    }

    private fun retrieveGraphicElements() {

        this.campoSubsidioNumeroHijos = findViewById<EditText>(R.id.campoSubsidioNumeroHijos)

        this.campoSubsidioHijosEdadEscolar = findViewById<EditText>(R.id.campoSubdidioHijosEdadEscolar)

        this.campoSubsidioEstadoCivil = findViewById<EditText>(R.id.campoSubsidioEstadoCivil)

        this.campoSubsidioTotal = findViewById<EditText>(R.id.campoTotalSubsidio)

        this.botonRegresar = findViewById<Button>(R.id.botonRegresar)

    }

    private fun configGraphicElement() {

        this.campoSubsidioNumeroHijos.inputType = InputType.TYPE_NULL

        this.campoSubsidioHijosEdadEscolar.inputType = InputType.TYPE_NULL

        this.campoSubsidioEstadoCivil.inputType = InputType.TYPE_NULL

        this.campoSubsidioTotal.inputType = InputType.TYPE_NULL

        this.botonRegresar.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()

        }

    }

    private fun calculate() {

        var subsidioNumeroHijos = 1700.0f

        if(this.numeroHijos in 3.0f..5.0f) {

            subsidioNumeroHijos = 1900.0f

        }else if(this.numeroHijos > 5.0f) {

            subsidioNumeroHijos = 1200.0f

        }

        var montoSubsidioHijoEdadEscolar = 200.0f

        var montoSubsidioMadreViuda = 800.0f

        var subsidioNumeroHijosEdadEscolar = montoSubsidioHijoEdadEscolar * this.numeroHijosEdadEscolar

        var subsidioEstadoCivil = if (this.subsidioEstadoCivil) montoSubsidioMadreViuda else 0.0f

        var subsidioTotal = subsidioNumeroHijos + subsidioNumeroHijosEdadEscolar + subsidioEstadoCivil

        this.campoSubsidioTotal.setText("$${subsidioTotal}")

        this.campoSubsidioNumeroHijos.setText("$${subsidioNumeroHijos}")

        this.campoSubsidioHijosEdadEscolar.setText("$${subsidioNumeroHijosEdadEscolar}")

        this.campoSubsidioEstadoCivil.setText("$${subsidioEstadoCivil}")

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_informacion)

        this.getInformationSendByParentActivity()

        this.retrieveGraphicElements()

        this.configGraphicElement()

        this.calculate()

    }

    override fun onDestroy() {

        super.onDestroy()

    }

}