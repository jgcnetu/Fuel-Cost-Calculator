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

class PrecoLitroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preco_litro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 01 - Assimilar elementos com FindViewById;
        // 02 - Passar dados digitados pelo usuario para a proxima tela;
        // 03 - Avançar para a proxima tela.

        // Botão de toolbar com a função de voltar a tela inicial:
        val toolbarPrecoLitro = findViewById<MaterialToolbar>(R.id.toolbarPrecoLitro)
         setSupportActionBar(toolbarPrecoLitro)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        val edtPrecoLitro = findViewById<EditText>(R.id.edtPrecoLitro)
        val btnPrecoLitroProximo = findViewById<Button>(R.id.btnPrecoLitroProximo)

        btnPrecoLitroProximo.setOnClickListener {

            val edtPrecoLitroValor = edtPrecoLitro.text.toString().toDouble()
            val intent = Intent(this, ConsumoActivity::class.java)

            intent.putExtra("PREÇO LITRO", edtPrecoLitroValor)

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