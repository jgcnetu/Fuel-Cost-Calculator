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

        // Encontrar e obter a referência do botão voltar (findViewById):
        val toolbarConsumo = findViewById<MaterialToolbar>(R.id.toolbar_consumo)

        // Definir a ação ao clicar no botão voltar da toolbar:
        setSupportActionBar(toolbarConsumo)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Receber dados da tela anterior (getExtra):
        val edtPrecoLitro = intent.getDoubleExtra("PREÇO LITRO", 0.0)

        // Encontrar e obter a referência do campo a ser digitado pelo usuario (findViewById):
        val edtConsumo = findViewById<EditText>(R.id.edtConsumo)

        // Encontrar e obter a referência do botão "próximo" (findViewById):
        val btnConsumoProximo = findViewById<Button>(R.id.btnConsumoProximo)

        // Definir a ação ao clicar no botão "próximo":
        btnConsumoProximo.setOnClickListener {

            // Receber dados digitados pelo usuario (como String):
            val consumoDigitado = edtConsumo.text.toString()

            // CORREÇÃO: Verificar se o campo está vazio (ANTES de converter para Double)
            if (consumoDigitado.isEmpty()) {
                // Mostrar mensagem de erro
                edtConsumo.error = "Por favor, digite o consumo"
                return@setOnClickListener // Para a execução aqui e não avança
            }

            // VALIDAÇÃO EXTRA: Verificar se é um número válido
            try {
                val edtConsumoValor = consumoDigitado.toDouble()

                // VALIDAÇÃO: Verificar se o valor é maior que zero
                if (edtConsumoValor <= 0) {
                    edtConsumo.error = "O consumo deve ser maior que zero"
                    return@setOnClickListener
                }

                // Limpar qualquer erro anterior
                edtConsumo.error = null

                // Intenção de navegar para a proxima tela executando a proxima Activity (intent):
                val intent = Intent(this, DistanciaActivity::class.java)

                // Passar valores armazenados para a proxima tela (putExtra):
                intent.putExtra("PREÇO LITRO", edtPrecoLitro)
                intent.putExtra("CONSUMO", edtConsumoValor)

                startActivity(intent)
            } catch (e: NumberFormatException) {
                // Se não conseguir converter para número, mostrar erro
                edtConsumo.error = "Por favor, digite um valor numérico válido"
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