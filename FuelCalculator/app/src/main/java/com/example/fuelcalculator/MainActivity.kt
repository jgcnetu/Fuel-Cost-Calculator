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

        // vincular o botao na tela a uma função no codigo:
        val btnCalcular = findViewById<Button>(R.id.btnMainProximo)

        // atribuir uma ação ao botão:
        btnCalcular.setOnClickListener {

            // navegar para a proxima tela com a intenção de executar a proxima Activity:
            val intent = Intent(this, PrecoLitroActivity::class.java)
            startActivity(intent)
        }
    }
}