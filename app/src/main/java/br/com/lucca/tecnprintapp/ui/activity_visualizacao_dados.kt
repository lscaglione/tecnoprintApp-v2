package br.com.lucca.tecnprintapp.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import br.com.lucca.tecnprintapp.R
import br.com.lucca.tecnprintapp.db.Producao
import com.google.firebase.firestore.FirebaseFirestore

class activity_visualizacao_dados : AppCompatActivity() {
    private lateinit var cardview1: CardView
    private lateinit var cardview2: CardView
    private lateinit var cardview3: CardView
    private lateinit var cardview4: CardView
    private lateinit var cardview5: CardView
    private lateinit var cardview6: CardView
    private lateinit var cardview7: CardView
    private lateinit var cardview8: CardView
    private lateinit var cardview9: CardView
    private lateinit var cardview10: CardView

    private lateinit var botaoCard1: Button
    private lateinit var botaoCard2 : Button
    private lateinit var botaoCard3: Button
    private lateinit var botaoCard4 : Button
    private lateinit var botaoCard5: Button
    private lateinit var botaoCard6 : Button
    private lateinit var botaoCard7: Button
    private lateinit var botaoCard8 : Button
    private lateinit var botaoCard9: Button
    private lateinit var botaoCard10 : Button
    private var cardZoomedIn: CardView? = null

    override fun onResume() {
        super.onResume()
        carregarProducoesDoFirestore()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizacao_dados)

        botaoInserirDados()
        carregarProducoesDoFirestore()
        botaoRelatorio()


        botaoCard1 = findViewById(R.id.botaoCardView1)
        botaoCard2 = findViewById(R.id.botaoCardView2)
        botaoCard3 = findViewById(R.id.botaoCardView3)
        botaoCard4 = findViewById(R.id.botaoCardView4)
        botaoCard5 = findViewById(R.id.botaoCardView5)
        botaoCard6 = findViewById(R.id.botaoCardView6)
        botaoCard7 = findViewById(R.id.botaoCardView7)
        botaoCard8 = findViewById(R.id.botaoCardView8)
        botaoCard9 = findViewById(R.id.botaoCardView9)
        botaoCard10 = findViewById(R.id.botaoCardView10)


        botaoCard1.visibility = View.GONE
        botaoCard2.visibility = View.GONE
        botaoCard3.visibility = View.GONE
        botaoCard4.visibility = View.GONE
        botaoCard5.visibility = View.GONE
        botaoCard6.visibility = View.GONE
        botaoCard7.visibility = View.GONE
        botaoCard8.visibility = View.GONE
        botaoCard9.visibility = View.GONE
        botaoCard10.visibility = View.GONE

        cardViews()


    }

    //Troca a activity
    private fun botaoInserirDados() {
        val botao = findViewById<Button>(R.id.botaoInserirDados)
        botao.setOnClickListener {
            startActivity(Intent(this, activity_insercao_dados::class.java))
        }
    }

    //Troca a activity
    private fun botaoRelatorio(){

        val botaoRelatorio: Button = findViewById(R.id.botaoRelatorio)
        botaoRelatorio.setOnClickListener {
            startActivity(Intent(this, activity_info_produto::class.java))
        }
    }

    //VOLTAR A ESTUDAR DAQUI
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

    private fun aplicarZoom(view: View) {
        val animacao = ScaleAnimation(
            1.0f, 1.0f,  // Escala de 1.0 para 1.2 no eixo X
            1.0f, 1.0f,  // Escala de 1.0 para 1.2 no eixo Y
            Animation.RELATIVE_TO_SELF, 0.5f,  // Ponto central no eixo X
            Animation.RELATIVE_TO_SELF, 0.5f   // Ponto central no eixo Y
        )

        animacao.duration = 300 // Duração da animação em milissegundos
        animacao.fillAfter = true // Mantém a view no tamanho aumentado após a animação

        view.startAnimation(animacao)
    }

    private fun resetarZoom(view: View) {
        val animacao = ScaleAnimation(
            1.0f, 1.0f,  // Escala de 1.2 para 1.0 no eixo X
            1.0f, 1.0f,  // Escala de 1.2 para 1.0 no eixo Y
            Animation.RELATIVE_TO_SELF, 0.5f,  // Ponto central no eixo X
            Animation.RELATIVE_TO_SELF, 0.5f   // Ponto central no eixo Y
        )

        animacao.duration = 300 // Duração da animação em milissegundos
        animacao.fillAfter = false // Garante que a view retorna ao tamanho original após a animação


        view.startAnimation(animacao)
    }

    private fun cardViews(){

        cardview1 = findViewById(R.id.cardview1)
        cardview2 = findViewById(R.id.cardview2)
        cardview3 = findViewById(R.id.cardview3)
        cardview4 = findViewById(R.id.cardview4)
        cardview5 = findViewById(R.id.cardview5)
        cardview6 = findViewById(R.id.cardview6)
        cardview7 = findViewById(R.id.cardview7)
        cardview8 = findViewById(R.id.cardview8)
        cardview9 = findViewById(R.id.cardview9)
        cardview10 = findViewById(R.id.cardview10)

        cardview1.setOnClickListener {
            if (cardZoomedIn == null) {
                // Se nenhum CardView estiver ampliado, aplica o zoom
                aplicarZoom(cardview1)
                cardZoomedIn = cardview1
                botaoCard1.visibility = View.VISIBLE
            } else if (cardZoomedIn == cardview1) {
                // Se o CardView clicado já estiver ampliado, redefina o zoom
                resetarZoom(cardview1)
                cardZoomedIn = null
                botaoCard1.visibility = View.GONE
            } else {
                // Se um CardView diferente já estiver ampliado, redefina o zoom para aquele
                resetarZoom(cardZoomedIn!!)
                aplicarZoom(cardview1)
                cardZoomedIn = cardview1
                botaoCard1.visibility = View.VISIBLE
            }

        }

        cardview2.setOnClickListener {
            if (cardZoomedIn == null) {
                // Se nenhum CardView estiver ampliado, aplica o zoom
                aplicarZoom(cardview2)
                cardZoomedIn = cardview2
                botaoCard2.visibility = View.VISIBLE
            } else if (cardZoomedIn == cardview2) {
                // Se o CardView clicado já estiver ampliado, redefina o zoom
                resetarZoom(cardview2)
                cardZoomedIn = null
                botaoCard2.visibility = View.GONE
            } else {
                // Se um CardView diferente já estiver ampliado, redefina o zoom para aquele
                resetarZoom(cardZoomedIn!!)
                aplicarZoom(cardview2)
                cardZoomedIn = cardview2
                botaoCard2.visibility = View.VISIBLE
            }

        }

        cardview3.setOnClickListener {
            if (cardZoomedIn == null) {
                // Se nenhum CardView estiver ampliado, aplica o zoom
                aplicarZoom(cardview3)
                cardZoomedIn = cardview3
                botaoCard3.visibility = View.VISIBLE
            } else if (cardZoomedIn == cardview3) {
                // Se o CardView clicado já estiver ampliado, redefina o zoom
                resetarZoom(cardview3)
                cardZoomedIn = null
                botaoCard3.visibility = View.GONE
            } else {
                // Se um CardView diferente já estiver ampliado, redefina o zoom para aquele
                resetarZoom(cardZoomedIn!!)
                aplicarZoom(cardview3)
                cardZoomedIn = cardview3
                botaoCard3.visibility = View.VISIBLE
            }

        }

        cardview4.setOnClickListener {
            if (cardZoomedIn == null) {
                // Se nenhum CardView estiver ampliado, aplica o zoom
                aplicarZoom(cardview4)
                cardZoomedIn = cardview4
                botaoCard4.visibility = View.VISIBLE
            } else if (cardZoomedIn == cardview4) {
                // Se o CardView clicado já estiver ampliado, redefina o zoom
                resetarZoom(cardview4)
                cardZoomedIn = null
                botaoCard4.visibility = View.GONE
            } else {
                // Se um CardView diferente já estiver ampliado, redefina o zoom para aquele
                resetarZoom(cardZoomedIn!!)
                aplicarZoom(cardview4)
                cardZoomedIn = cardview4
                botaoCard4.visibility = View.VISIBLE
            }

        }

        cardview5.setOnClickListener {
            if (cardZoomedIn == null) {
                // Se nenhum CardView estiver ampliado, aplica o zoom
                aplicarZoom(cardview5)
                cardZoomedIn = cardview5
                botaoCard5.visibility = View.VISIBLE
            } else if (cardZoomedIn == cardview5) {
                // Se o CardView clicado já estiver ampliado, redefina o zoom
                resetarZoom(cardview5)
                cardZoomedIn = null
                botaoCard5.visibility = View.GONE
            } else {
                // Se um CardView diferente já estiver ampliado, redefina o zoom para aquele
                resetarZoom(cardZoomedIn!!)
                aplicarZoom(cardview5)
                cardZoomedIn = cardview5
                botaoCard5.visibility = View.VISIBLE
            }

        }

        cardview6.setOnClickListener {
            if (cardZoomedIn == null) {
                // Se nenhum CardView estiver ampliado, aplica o zoom
                aplicarZoom(cardview6)
                cardZoomedIn = cardview6
                botaoCard6.visibility = View.VISIBLE
            } else if (cardZoomedIn == cardview6) {
                // Se o CardView clicado já estiver ampliado, redefina o zoom
                resetarZoom(cardview6)
                cardZoomedIn = null
                botaoCard6.visibility = View.GONE
            } else {
                // Se um CardView diferente já estiver ampliado, redefina o zoom para aquele
                resetarZoom(cardZoomedIn!!)
                aplicarZoom(cardview6)
                cardZoomedIn = cardview6
                botaoCard6.visibility = View.VISIBLE
            }

        }

        cardview7.setOnClickListener {
            if (cardZoomedIn == null) {
                // Se nenhum CardView estiver ampliado, aplica o zoom
                aplicarZoom(cardview7)
                cardZoomedIn = cardview7
                botaoCard7.visibility = View.VISIBLE
            } else if (cardZoomedIn == cardview7) {
                // Se o CardView clicado já estiver ampliado, redefina o zoom
                resetarZoom(cardview7)
                cardZoomedIn = null
                botaoCard7.visibility = View.GONE
            } else {
                // Se um CardView diferente já estiver ampliado, redefina o zoom para aquele
                resetarZoom(cardZoomedIn!!)
                aplicarZoom(cardview7)
                cardZoomedIn = cardview7
                botaoCard7.visibility = View.VISIBLE
            }

        }

        cardview8.setOnClickListener {
            if (cardZoomedIn == null) {
                // Se nenhum CardView estiver ampliado, aplica o zoom
                aplicarZoom(cardview8)
                cardZoomedIn = cardview8
                botaoCard8.visibility = View.VISIBLE
            } else if (cardZoomedIn == cardview8) {
                // Se o CardView clicado já estiver ampliado, redefina o zoom
                resetarZoom(cardview8)
                cardZoomedIn = null
                botaoCard8.visibility = View.GONE
            } else {
                // Se um CardView diferente já estiver ampliado, redefina o zoom para aquele
                resetarZoom(cardZoomedIn!!)
                aplicarZoom(cardview8)
                cardZoomedIn = cardview8
                botaoCard8.visibility = View.VISIBLE
            }

        }

        cardview9.setOnClickListener {
            if (cardZoomedIn == null) {
                // Se nenhum CardView estiver ampliado, aplica o zoom
                aplicarZoom(cardview9)
                cardZoomedIn = cardview9
                botaoCard9.visibility = View.VISIBLE
            } else if (cardZoomedIn == cardview9) {
                // Se o CardView clicado já estiver ampliado, redefina o zoom
                resetarZoom(cardview9)
                cardZoomedIn = null
                botaoCard9.visibility = View.GONE
            } else {
                // Se um CardView diferente já estiver ampliado, redefina o zoom para aquele
                resetarZoom(cardZoomedIn!!)
                aplicarZoom(cardview9)
                cardZoomedIn = cardview9
                botaoCard9.visibility = View.VISIBLE
            }

        }

        cardview10.setOnClickListener {
            if (cardZoomedIn == null) {
                // Se nenhum CardView estiver ampliado, aplica o zoom
                aplicarZoom(cardview10)
                cardZoomedIn = cardview10
                botaoCard10.visibility = View.VISIBLE
            } else if (cardZoomedIn == cardview10) {
                // Se o CardView clicado já estiver ampliado, redefina o zoom
                resetarZoom(cardview10)
                cardZoomedIn = null
                botaoCard10.visibility = View.GONE
            } else {
                // Se um CardView diferente já estiver ampliado, redefina o zoom para aquele
                resetarZoom(cardZoomedIn!!)
                aplicarZoom(cardview10)
                cardZoomedIn = cardview10
                botaoCard10.visibility = View.VISIBLE
            }

        }


    }

}

