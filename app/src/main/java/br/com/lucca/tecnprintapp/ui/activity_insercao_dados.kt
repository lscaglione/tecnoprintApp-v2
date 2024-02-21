package br.com.lucca.tecnprintapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.lucca.tecnprintapp.R
import br.com.lucca.tecnprintapp.db.Funcionarios
import br.com.lucca.tecnprintapp.db.Maquinas
import br.com.lucca.tecnprintapp.db.Producao
import br.com.lucca.tecnprintapp.db.Produtos
import com.google.firebase.firestore.FirebaseFirestore

class activity_insercao_dados : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insercao_dados)

        // Configura os botões e busca as informações necessárias ao iniciar
        botaoVoltar()
        buscarFuncionarios()
        buscarMaquinas()
        buscarProdutos()
        botaoConfirmar()
    }

    //Troca a activity
    private fun botaoVoltar() {
        val botaoVolta: Button = findViewById(R.id.botaoDeTeste)
        botaoVolta.setOnClickListener {
            val intent = Intent(this, activity_visualizacao_dados::class.java)
            startActivity(intent)
        }
    }





    //Atualiza os dados de produção atual no firestore para serem visualizados em outra activity
    private fun botaoConfirmar() {
        val botaoConfirmar: Button = findViewById(R.id.botaoConfirmaDados)
        botaoConfirmar.setOnClickListener {
            val funcionario = findViewById<Spinner>(R.id.spinnerFuncionario).selectedItem.toString()
            val maquina = findViewById<Spinner>(R.id.spinnerMaquina).selectedItem.toString()
            val produto = findViewById<Spinner>(R.id.spinnerProduto).selectedItem.toString()

            if (funcionario != "Selecione o funcionário" && maquina != "Selecione a máquina" && produto != "Selecione o produto") {
                val producao = Producao(funcionario, maquina, produto)
                salvarProducaoNoFirestore(producao)
                Toast.makeText(this, "Produção salva com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, selecione todas as opções.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    //Salva os dados no firestore para serem atualizados na activity de visualização
    private fun salvarProducaoNoFirestore(producao: Producao) {
        val db = FirebaseFirestore.getInstance()
        db.collection("Producoes").document(producao.maquina)
            .set(producao)
    }



    //Busca os nomes de funcionários e aciona o callback para atualizar os spinners
    private fun buscarFuncionarios() {
        Funcionarios().apply {
            buscarNomesFuncionarios()
            setOnDataLoadedCallback { nomes ->
                atualizarSpinnerFuncionarios(nomes)
            }

        }
    }

    //Busca as máquina e aciona o callback para atualizar os spinners
    private fun buscarMaquinas() {
        Maquinas().apply {
            buscarNomesMaquinas()
            setOnDataLoadedCallback { nomes ->
                atualizarSpinnerMaquinas(nomes)
            }
        }
    }


    //Busca os produtos e aciona o callback para atualizar os spinners
    private fun buscarProdutos() {

        Produtos().apply {
            buscarNomesProdutos()
            setOnDataLoadedCallback { nomes ->
                atualizarSpinnerProdutos(nomes)
            }
        }
    }


    //Spinner que recebe a lista de funcionários e uma "hint", layout do adapter personalizado
    private fun atualizarSpinnerFuncionarios(nomes: List<String>) {
        val opcoesComHint = mutableListOf("Selecione o funcionário").apply {
            addAll(nomes)
        }
        val adapter = ArrayAdapter(this, R.layout.spinner, opcoesComHint).also { it.setDropDownViewResource(R.layout.spinner_dropdown) }
        findViewById<Spinner>(R.id.spinnerFuncionario).adapter = adapter
    }

    //Spinner que recebe a lista de maquinas e uma "hint", layout do adapter personalizado
    private fun atualizarSpinnerMaquinas(nomes: List<String>) {
        val opcoesComHint = mutableListOf("Selecione a máquina").apply {
            addAll(nomes)
        }
        val adapter = ArrayAdapter(this, R.layout.spinner, opcoesComHint).also { it.setDropDownViewResource(R.layout.spinner_dropdown) }
        findViewById<Spinner>(R.id.spinnerMaquina).adapter = adapter
    }

    //Spinner que recebe a lista de produtos e uma "hint", layout do adapter personalizado
    private fun atualizarSpinnerProdutos(nomes: List<String>) {
        val opcoesComHint = mutableListOf("Selecione o produto").apply {
            addAll(nomes)
        }
        val adapter = ArrayAdapter(this, R.layout.spinner, opcoesComHint).also { it.setDropDownViewResource(R.layout.spinner_dropdown) }
        findViewById<Spinner>(R.id.spinnerProduto).adapter = adapter
    }
}
