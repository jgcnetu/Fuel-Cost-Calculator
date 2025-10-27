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

        // 01 - Encontrar e obter as referências dos view's com FindViewById;
        // 02 - Receber dados da tela anterior com getExtra;
        // 03 - Mostrar na tela o resumo dos valores que foram armazenados;
        // 04 - Reiniciar o ciclo para a tela inicial;
        // 05 - Retornar para tela anterior.

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

        // Mostrar na tela o resumo dos valores que foram armazenados (toString):
        tvPrecoLitro.text = precoLitro.toString()
        tvConsumo.text = consumo.toString()
        tvDistancia.text = distancia.toString()
        tvResultado.text = custoTotal.toString()

        // Definir a ação ao clicar no botão "próximo":
        btnResultado.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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