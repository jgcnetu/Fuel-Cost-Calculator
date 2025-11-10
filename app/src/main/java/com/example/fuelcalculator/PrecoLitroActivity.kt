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

            // Receber dados digitados pelo usuario:
            val precoDigitado = edtPrecoLitro.text.toString()

            // VALIDAÇÃO: Verificar se o campo está vazio
            if (precoDigitado.isEmpty()) {
                // Mostrar mensagem de erro
                edtPrecoLitro.error = "Por favor, digite o preço do litro"
                return@setOnClickListener // Para a execução aqui e não avança
            }

            // VALIDAÇÃO EXTRA: Verificar se é um número válido
            try {
                val edtPrecoLitroValor = precoDigitado.toDouble()

                // VALIDAÇÃO: Verificar se o valor é maior que zero
                if (edtPrecoLitroValor <= 0) {
                    edtPrecoLitro.error = "O preço deve ser maior que zero"
                    return@setOnClickListener
                }

                // Limpar qualquer erro anterior
                edtPrecoLitro.error = null

                // Intenção de navegar para a proxima tela executando a proxima Activity (intent):
                val intent = Intent(this, ConsumoActivity::class.java)

                // Passar valores armazenados para a proxima tela (putExtra):
                intent.putExtra("PREÇO LITRO", edtPrecoLitroValor)

                startActivity(intent)

            } catch (e: NumberFormatException) {
                // Se não conseguir converter para número, mostrar erro
                edtPrecoLitro.error = "Por favor, digite um valor numérico válido"
            }
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