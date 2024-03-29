package br.com.lucca.tecnprintapp.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucca.tecnprintapp.R
import br.com.lucca.tecnprintapp.db.Funcionarios
import br.com.lucca.tecnprintapp.db.Maquinas
import br.com.lucca.tecnprintapp.db.Produtos
import java.util.Calendar

class ActivitySalvarProducao : AppCompatActivity() {

    private val produtos = Produtos()
    private lateinit var produtosAdapter: ProdutosAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextPesquisaProdutos: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salvar_producao)



        pesquisaProdutos()
        buscarMaquinas()
        buscarFuncionarios()
        selecionarData()

    }

    private fun buscarFuncionarios() {
        Funcionarios().apply {
            buscarNomesFuncionarios()
            setOnDataLoadedCallback { nomes ->
                atualizarSpinnerFuncionarios(nomes)
            }

        }
    }

    private fun atualizarSpinnerFuncionarios(nomes: List<String>) {
        val opcoesComHint = mutableListOf("Funcionário").apply {
            addAll(nomes)
        }
        val adapter = ArrayAdapter(this, R.layout.spinner, opcoesComHint).also { it.setDropDownViewResource(R.layout.spinner_dropdown) }
        findViewById<Spinner>(R.id.spinnerFuncionarioProducao).adapter = adapter
    }

    private fun buscarMaquinas() {
        Maquinas().apply {
            buscarNomesMaquinas()
            setOnDataLoadedCallback { nomes ->
                atualizarSpinnerMaquinas(nomes)
            }
        }
    }

    private fun atualizarSpinnerMaquinas(nomes: List<String>) {
        val opcoesComHint = mutableListOf("Máquina").apply {
            addAll(nomes)
        }
        val adapter = ArrayAdapter(this, R.layout.spinner, opcoesComHint).also { it.setDropDownViewResource(R.layout.spinner_dropdown) }
        findViewById<Spinner>(R.id.spinnerMaquinaProducao).adapter = adapter
    }

    private fun selecionarData() {
        val editTextDataProducao = findViewById<EditText>(R.id.data)

        // Obtém a data atual
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Configuração do DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                editTextDataProducao.setText(selectedDate)
            },
            year,
            month,
            day
        )

        // Configuração do OnClickListener para o EditText
        editTextDataProducao.setOnClickListener {
            datePickerDialog.show()
        }

        // Configuração do OnFocusChangeListener para evitar a abertura do teclado
        editTextDataProducao.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                datePickerDialog.show()
            }
        }
    }

    fun pesquisaProdutos() {
        editTextPesquisaProdutos = findViewById(R.id.editTextPesquisaProdutos)
        recyclerView = findViewById(R.id.producaoRecyclerView)


        editTextPesquisaProdutos.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                produtos.setOnDataLoadedCallback { nomes ->
                    produtosAdapter = ProdutosAdapter(this, nomes)
                    recyclerView.adapter = produtosAdapter
                    recyclerView.layoutManager = GridLayoutManager(this, 2)
                }
                produtos.buscarNomesProdutos()

            }
        }

    }


}
