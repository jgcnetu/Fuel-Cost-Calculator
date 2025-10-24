package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class DistanciaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_distancia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 01 - Recuperar dados da tela anterior;
        // 02 - Assimilar elementos com FindViewById;
        // 03 - Passar dados digitados pelo usuario para a proxima tela;
        // 04 - Avançar para a proxima tela.

        // Toolbar com função de voltar
        val toolbarDistancia = findViewById<MaterialToolbar>(R.id.toolbarDistancia)
        setSupportActionBar(toolbarDistancia)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Resgatar valores da pagina anterior:
        val edtPrecoLitro = intent.getDoubleExtra("PREÇO LITRO", 0.0)
        val edtConsumo = intent.getIntExtra("CONSUMO", 0)

        // Atribuir os elementos desta tela:
        val edtDistancia = findViewById<EditText>(R.id.edtDistancia)
        val btnDistanciaProximo = findViewById<Button>(R.id.btnDistanciaProximo)

        btnDistanciaProximo.setOnClickListener {

            // Atribuir os valores digitados pelo usuario:
            val precoLitro = edtPrecoLitro
            val consumo = edtConsumo
            val distancia = edtDistancia.text.toString().toInt()

            // 05 - Calcular os litros necessarios:
            val litrosNecessarios = distancia / consumo

            // 06 - Calcular o custo total:
            val custoTotal = litrosNecessarios * precoLitro

            val intent = Intent(this, ResultadoActivity::class.java)

            intent.putExtra("PREÇO LITRO", precoLitro)
            intent.putExtra("CONSUMO", consumo)
            intent.putExtra("DISTANCIA", distancia)
            intent.putExtra("CUSTO TOTAL", custoTotal)

            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}