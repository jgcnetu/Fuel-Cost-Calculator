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

        // 01 - Encontrar e obter as referências dos view's com FindViewById;
        // 02 - Passar dados para a proxima tela com putExtra;
        // 03 - Avançar para a proxima tela (Consumo);
        // 04 - Retornar para tela inicial (Main).

        // Encontrar e obter a referência do botão voltar (findViewById):
        val toolbarPrecoLitro = findViewById<MaterialToolbar>(R.id.toolbarPrecoLitro)

        // Definir a ação ao clicar no botão voltar da toolbar:
        setSupportActionBar(toolbarPrecoLitro)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Encontrar e obter a referência do campo a ser digitado pelo usuario (findViewById):
        val edtPrecoLitro = findViewById<EditText>(R.id.edtPrecoLitro)

        // Encontrar e obter a referência do botão "próximo" (findViewById):
        val btnPrecoLitroProximo = findViewById<Button>(R.id.btnPrecoLitroProximo)

        // Definir a ação ao clicar no botão "próximo":
        btnPrecoLitroProximo.setOnClickListener {

            // Receber e converter dados digitados pelo usuario:
            val edtPrecoLitroValor = edtPrecoLitro.text.toString().toDouble()

            // Intenção de navegar para a proxima tela executando a proxima Activity (intent):
            val intent = Intent(this, ConsumoActivity::class.java)

            // Passar valores armazenados para a proxima tela (putExtra):
            intent.putExtra("PREÇO LITRO", edtPrecoLitroValor)

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