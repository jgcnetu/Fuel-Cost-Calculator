package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import java.text.DecimalFormat

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Encontrar e obter a referência do botão voltar (findViewById):
        val tollbarResultado = findViewById<MaterialToolbar>(R.id.tollbarResultado)

        // Definir a ação ao clicar no botão voltar da toolbar:
        setSupportActionBar(tollbarResultado)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Receber dados da tela anterior (getExtra):
        val precoLitro = intent.getDoubleExtra("PREÇO LITRO", 0.0)
        val consumo = intent.getDoubleExtra("CONSUMO", 0.0)
        val distancia = intent.getDoubleExtra("DISTANCIA", 0.0)
        val custoTotal = intent.getDoubleExtra("CUSTO TOTAL", 0.0)

        // Encontrar e obter a referência dos valores que foram armazenados (findViewById):
        val tvPrecoLitro = findViewById<TextView>(R.id.tvPrecoLitro)
        val tvConsumo = findViewById<TextView>(R.id.tvConsumo)
        val tvDistancia = findViewById<TextView>(R.id.tvDistancia)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        // Encontrar e obter a referência do botão "Calcular Novamente" (findViewById):
        val btnResultado = findViewById<Button>(R.id.btnResultado)

        // Criar formatador para 2 casas decimais
        val decimalFormat = DecimalFormat("#,##0.00")

        // Mostrar na tela o resumo dos valores que foram armazenados com 2 casas decimais:
        tvPrecoLitro.text = "R$ ${decimalFormat.format(precoLitro)}"
        tvConsumo.text = "${decimalFormat.format(consumo)} km/L"
        tvDistancia.text = "${decimalFormat.format(distancia)} km"
        tvResultado.text = "R$ ${decimalFormat.format(custoTotal)}"

        // Definir a ação ao clicar no botão "Calcular Novamente":
        btnResultado.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Opcional: fecha esta activity para não acumular na pilha
        }
    }

    // Função de voltar na toolbar:
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