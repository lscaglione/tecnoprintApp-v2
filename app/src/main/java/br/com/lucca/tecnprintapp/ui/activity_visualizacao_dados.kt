package br.com.lucca.tecnprintapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import br.com.lucca.tecnprintapp.R
import br.com.lucca.tecnprintapp.db.Producao
import com.google.firebase.firestore.FirebaseFirestore

class activity_visualizacao_dados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizacao_dados)

        botaoInserirDados()
        carregarProducoesDoFirestore()
    }

    private fun botaoInserirDados() {
        val botao = findViewById<Button>(R.id.botaoInserirDados)
        botao.setOnClickListener {
            startActivity(Intent(this, activity_insercao_dados::class.java))
        }
    }

    private fun carregarProducoesDoFirestore() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Producoes")
            .get()
            .addOnSuccessListener { documentos ->
                documentos.forEach { documento ->
                    val producao = documento.toObject(Producao::class.java)
                    atualizarCardViewComBaseNaMaquina(producao)
                }
            }
            .addOnFailureListener {
                // Tratamento de erro
            }
    }

    private fun atualizarCardViewComBaseNaMaquina(producao: Producao) {
        when (producao.maquina) {
            "Máquina 1" -> atualizarCardView(
                R.id.cardview1,
                producao,
                R.id.textview1,
                R.id.textview2,
                R.id.textview3
            )

            "Máquina 2" -> atualizarCardView(
                R.id.cardview2,
                producao,
                R.id.textview4,
                R.id.textview5,
                R.id.textview6
            )

            "Máquina 3" -> atualizarCardView(
                R.id.cardview3,
                producao,
                R.id.textview7,
                R.id.textview8,
                R.id.textview9
            )

            "Máquina 4" -> atualizarCardView(
                R.id.cardview4,
                producao,
                R.id.textview10,
                R.id.textview11,
                R.id.textview12
            )

            "Máquina 5" -> atualizarCardView(
                R.id.cardview5,
                producao,
                R.id.textview13,
                R.id.textview14,
                R.id.textview15
            )

            "Máquina 6" -> atualizarCardView(
                R.id.cardview6,
                producao,
                R.id.textview16,
                R.id.textview17,
                R.id.textview18
            )

            "Máquina 7" -> atualizarCardView(
                R.id.cardview7,
                producao,
                R.id.textview19,
                R.id.textview20,
                R.id.textview21
            )

            "Máquina 8" -> atualizarCardView(
                R.id.cardview8,
                producao,
                R.id.textview22,
                R.id.textview23,
                R.id.textview24
            )

            "Máquina 9" -> atualizarCardView(
                R.id.cardview9,
                producao,
                R.id.textview25,
                R.id.textview26,
                R.id.textview27
            )

            "Máquina 10" -> atualizarCardView(
                R.id.cardview10,
                producao,
                R.id.textview28,
                R.id.textview29,
                R.id.textview30
            )
        }
    }

    private fun atualizarCardView(cardViewId: Int, producao: Producao, textviewFuncionarioId: Int, textviewMaquinaId: Int, textviewProdutoId: Int) {
        val cardView = findViewById<CardView>(cardViewId)
        val textviewFuncionario = cardView.findViewById<TextView>(textviewFuncionarioId)
        val textviewMaquina = cardView.findViewById<TextView>(textviewMaquinaId)
        val textviewProduto = cardView.findViewById<TextView>(textviewProdutoId)

        textviewFuncionario.text = producao.funcionario
        textviewMaquina.text = producao.maquina
        textviewProduto.text = producao.produto
    }
}

