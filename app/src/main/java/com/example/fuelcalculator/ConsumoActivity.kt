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

class ConsumoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_consumo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 01 - Recuperar dados da tela anterior;
        // 02 - Assimilar elementos com FindViewById;
        // 03 - Passar dados digitados pelo usuario para a proxima tela;
        // 04 - Avançar para a proxima tela.

        val toolbarConsumo = findViewById<MaterialToolbar>(R.id.toolbar_consumo)
        setSupportActionBar(toolbarConsumo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val edtPrecoLitro = intent.getDoubleExtra("PREÇO LITRO", 0.0)

        val edtConsumo = findViewById<EditText>(R.id.edtConsumo)
        val btnConsumoProximo = findViewById<Button>(R.id.btnConsumoProximo)

        btnConsumoProximo.setOnClickListener {

            val edtConsumoValor = edtConsumo.text.toString().toInt()

            val intent = Intent(this, DistanciaActivity::class.java)

            intent.putExtra("PREÇO LITRO", edtPrecoLitro)
            intent.putExtra("CONSUMO", edtConsumoValor)

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