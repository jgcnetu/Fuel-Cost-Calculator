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

        val tollbarResultado = findViewById<MaterialToolbar>(R.id.tollbarResultado)
        setSupportActionBar(tollbarResultado)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        val precoLitro = intent.getDoubleExtra("PREÇO LITRO", 0.0)
        val consumo = intent.getIntExtra("CONSUMO", 0)
        val distancia = intent.getIntExtra("DISTANCIA", 0)
        val custoTotal = intent.getDoubleExtra("CUSTO TOTAL", 0.0)

        val tvPrecoLitro = findViewById<TextView>(R.id.tvPrecoLitro)
        val tvConsumo = findViewById<TextView>(R.id.tvConsumo)
        val tvDistancia = findViewById<TextView>(R.id.tvDistancia)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val btnResultado = findViewById<Button>(R.id.btnResultado)

        tvPrecoLitro.text = precoLitro.toString()
        tvConsumo.text = consumo.toString()
        tvDistancia.text = distancia.toString()
        tvResultado.text = custoTotal.toString()

        btnResultado.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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