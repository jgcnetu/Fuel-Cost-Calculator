package com.example.fuelcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // 01 - Encontrar e obter as referências dos view's com FindViewById;
        // 02 - Avançar para a proxima tela (PrecoLitro).

        // Encontrar e obter a referência do botão "próximo" (findViewById):
        val btnCalcular = findViewById<Button>(R.id.btnMainProximo)

        // Definir a ação ao clicar no botão "próximo":
        btnCalcular.setOnClickListener {

            // Intenção de navegar para a proxima tela executando a proxima Activity (intent):
            val intent = Intent(this, PrecoLitroActivity::class.java)
            startActivity(intent)
        }
    }
}