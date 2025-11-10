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

class DistanciaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_distancia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 01 - Encontrar e obter as referências dos view's com FindViewById;
        // 02 - Receber dados da tela anterior com getExtra;
        // 03 - Calcular os valores necessarios para o custo total;
        // 04 - Passar dados para a tela final com putExtra;
        // 05 - Avançar para a tela de resultado;
        // 06 - Retornar para tela anterior.

        // Encontrar e obter a referência do botão voltar (findViewById):
        val toolbarDistancia = findViewById<MaterialToolbar>(R.id.toolbarDistancia)

        // Definir a ação ao clicar no botão voltar da toolbar:
        setSupportActionBar(toolbarDistancia)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Receber dados da tela anterior (getExtra):
        val edtPrecoLitro = intent.getDoubleExtra("PREÇO LITRO", 0.0)
        val edtConsumo = intent.getDoubleExtra("CONSUMO", 0.0)

        // Encontrar e obter a referência do campo a ser digitado pelo usuario (findViewById):
        val edtDistancia = findViewById<EditText>(R.id.edtDistancia)

        // Encontrar e obter a referência do botão "próximo" (findViewById):
        val btnDistanciaProximo = findViewById<Button>(R.id.btnDistanciaProximo)

        // Definir a ação ao clicar no botão "próximo":
        btnDistanciaProximo.setOnClickListener {

            // Receber dados digitados pelo usuario:
            val distanciaTexto = edtDistancia.text.toString()

            // CORREÇÃO: Verificar se o campo está vazio (ANTES de converter para Double)
            if (distanciaTexto.isEmpty()) {
                // Mostrar mensagem de erro
                edtDistancia.error = "Por favor, digite a distância"
                return@setOnClickListener // Para a execução aqui e não avança
            }

            // VALIDAÇÃO EXTRA: Verificar se é um número válido
            try {
                val edtDistanciaValor = distanciaTexto.toDouble()

                // VALIDAÇÃO: Verificar se o valor é maior que zero
                if (edtDistanciaValor <= 0) {
                    edtDistancia.error = "A distância deve ser maior que zero"
                    return@setOnClickListener
                }

                // Limpar qualquer erro anterior
                edtDistancia.error = null

                // Atribuir todos valores armazenados anteriormente:
                val precoLitro = edtPrecoLitro
                val consumo = edtConsumo

                // Calcular os litros necessarios:
                val litrosNecessarios = edtDistanciaValor / consumo

                // Calcular o custo total:
                val custoTotal = litrosNecessarios * precoLitro

                // Intenção de navegar para a proxima tela executando a proxima Activity (intent):
                val intent = Intent(this, ResultadoActivity::class.java)

                // Passar valores armazenados para a proxima tela (putExtra):
                intent.putExtra("PREÇO LITRO", precoLitro)
                intent.putExtra("CONSUMO", consumo)
                intent.putExtra("DISTANCIA", edtDistanciaValor) // CORREÇÃO: usar edtDistanciaValor em vez de distancia
                intent.putExtra("CUSTO TOTAL", custoTotal)

                startActivity(intent)

            } catch (e: NumberFormatException) {
                // Se não conseguir converter para número, mostrar erro
                edtDistancia.error = "Por favor, digite um valor numérico válido"
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