package br.com.lucca.tecnprintapp.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import br.com.lucca.tecnprintapp.R
import br.com.lucca.tecnprintapp.db.Funcionarios
import br.com.lucca.tecnprintapp.db.Maquinas
import java.util.Calendar

class ActivitySalvarProducao : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salvar_producao)

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
        val opcoesComHint = mutableListOf("Selecione o funcionário").apply {
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
        val opcoesComHint = mutableListOf("Selecione a máquina").apply {
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
}
