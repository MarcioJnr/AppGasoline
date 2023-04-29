package com.example.alcoolougasolina

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var editAlcool: EditText
    private lateinit var editGasolina: EditText
    private lateinit var switchPorcentagem: Switch
    private lateinit var textResultado: TextView
    private lateinit var labelGasolina: TextView
    private lateinit var labelAlcool: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editAlcool = findViewById(R.id.edit_alcool)
        editGasolina = findViewById(R.id.edit_gasolina)
        switchPorcentagem = findViewById(R.id.switch_porcentagem)
        textResultado = findViewById(R.id.text_resultado)
        labelGasolina = findViewById(R.id.label_gasolina)
        labelAlcool = findViewById(R.id.label_alcool)

        val buttonCalcular: Button = findViewById(R.id.button_calcular)
        buttonCalcular.setOnClickListener {
            calcularMelhorPreco()
        }

        switchPorcentagem.setOnCheckedChangeListener { _, isChecked ->
            calcularMelhorPreco()
        }

    }

    override fun onPause() {
        super.onPause()
        val porcentagem = if (switchPorcentagem.isChecked) 70 else 75
        getPreferences(MODE_PRIVATE).edit()
            .putInt("porcentagem", porcentagem)
            .apply()
    }

    override fun onResume() {
        super.onResume()
        val porcentagem = getPreferences(MODE_PRIVATE).getInt("porcentagem", 75)
        switchPorcentagem.isChecked = porcentagem == 70
        // Inicializa o textResultado com um valor padrão
        textResultado.text = "Digite os valores e clique em Calcular"
    }

    private fun calcularMelhorPreco() {

        val valorAlcool = editAlcool.text.toString().toDoubleOrNull() ?: 0.0
        val valorGasolina = editGasolina.text.toString().toDoubleOrNull() ?: 0.0

        Log.d("MainActivity", "valorAlcool: $valorAlcool, valorGasolina: $valorGasolina")

        val porcentagem = if (switchPorcentagem.isChecked) 70 else 75

        Log.d("MainActivity", "precoAlcool: $valorAlcool, precoGasolina: $valorGasolina, porcentagem: $porcentagem")

        val resultado = valorAlcool / valorGasolina

        Log.d("MainActivity", "resultado: $resultado")

        if (resultado <= porcentagem / 100.0) {
            textResultado.text = "Álcool é a melhor opção"
        } else {
            textResultado.text = "Gasolina é a melhor opção"
        }
    }

}
