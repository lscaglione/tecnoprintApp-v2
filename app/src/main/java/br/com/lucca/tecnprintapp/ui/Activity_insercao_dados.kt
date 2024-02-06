package br.com.lucca.tecnprintapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import br.com.lucca.tecnprintapp.R
import br.com.lucca.tecnprintapp.db.Funcionarios
import br.com.lucca.tecnprintapp.db.Maquinas
import br.com.lucca.tecnprintapp.db.Produtos

class activity_insercao_dados : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insercao_dados)

        botaoVoltar()
        buscarFuncionarios()
        buscarMaquinas()
        buscarProdutos()

    }


    fun atualizarSpinnerFuncionarios(nomes: List<String>) {
        runOnUiThread {
            val opcoesComHint = mutableListOf("Selecione o funcionário")
            opcoesComHint.addAll(nomes)

            val adapter = ArrayAdapter(this, R.layout.spinner, opcoesComHint).also { adapter ->
                adapter.setDropDownViewResource(R.layout.spinner_dropdown)
            }
            val spinnerFuncionario: Spinner = findViewById(R.id.spinnerFuncionario)
            spinnerFuncionario.adapter = adapter
        }
    }


    fun atualizarSpinnerMaquinas(nomes: List<String>) {
        runOnUiThread {
            val opcoesComHint = mutableListOf("Selecione a máquina")
            opcoesComHint.addAll(nomes)

            val adapter = ArrayAdapter(this, R.layout.spinner,  opcoesComHint).also { adapter ->
                adapter.setDropDownViewResource(R.layout.spinner_dropdown)
            }
            val spinnerMaquina: Spinner = findViewById(R.id.spinnerMaquina)
            spinnerMaquina.adapter = adapter
        }
    }


    fun atualizarSpinnerProdutos(nomes: List<String>) {
        runOnUiThread {
            val opcoesComHint = mutableListOf("Selecione o produto")
            opcoesComHint.addAll(nomes)

            val adapter = ArrayAdapter(this, R.layout.spinner, opcoesComHint).also { adapter ->
                adapter.setDropDownViewResource(R.layout.spinner_dropdown)
            }
            val spinnerProduto: Spinner = findViewById(R.id.spinnerProduto)
            spinnerProduto.adapter = adapter
        }
    }




    private fun buscarFuncionarios() {
        Funcionarios().apply {
            setOnDataLoadedCallback { nomes ->
                atualizarSpinnerFuncionarios(nomes)
            }
            buscarNomesFuncionarios()
        }
    }

    private fun buscarMaquinas() {
        Maquinas().apply {
            setOnDataLoadedCallback { nomes ->
                atualizarSpinnerMaquinas(nomes)
            }
            buscarNomesMaquinas()
        }
    }

    private fun buscarProdutos() {
        Produtos().apply {
            setOnDataLoadedCallback { nomes ->
                atualizarSpinnerProdutos(nomes)
            }
            buscarNomesProdutos()
        }
    }


    private fun botaoVoltar() {
        val botaoVolta: Button = findViewById(R.id.botaoDeTeste)
        botaoVolta.setOnClickListener {
            val intent = Intent(this, activity_visualizacao_dados::class.java)
            startActivity(intent)
        }
    }
    private fun botaoConfirmar(){


    }
}
