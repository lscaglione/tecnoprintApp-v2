package br.com.lucca.tecnprintapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucca.tecnprintapp.R
import br.com.lucca.tecnprintapp.db.Funcionarios
import br.com.lucca.tecnprintapp.db.Maquinas
import br.com.lucca.tecnprintapp.db.Produtos

class activity_info_produto : AppCompatActivity() {

    private val funcionarios = Funcionarios()
    private val produtos = Produtos()
    private val maquinas = Maquinas()
    private lateinit var recyclerView: RecyclerView
    private lateinit var funcionarioAdapter: FuncionarioAdapter
    private lateinit var produtoAdapter: ProdutoAdapter
    private lateinit var maquinaAdapter: MaquinaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_produto)

        val mainButton: Button = findViewById(R.id.mainButton)
        val mainButton2: Button = findViewById(R.id.mainButton2)
        val mainButton3: Button = findViewById(R.id.mainButton3)
        recyclerView = findViewById(R.id.optionsRecyclerView)

        val optionsContainer: View = findViewById(R.id.optionsRecyclerView)

        mainButton.setOnClickListener {
            // Alternar a visibilidade do contêiner de opções
            if (optionsContainer.visibility == View.VISIBLE) {
                optionsContainer.visibility = View.GONE
            } else {
                optionsContainer.visibility = View.VISIBLE

                // Limpar opções anteriores
                funcionarios.setOnDataLoadedCallback { nomes ->
                    funcionarioAdapter = FuncionarioAdapter(this, nomes)
                    recyclerView.adapter = funcionarioAdapter

                    // Configurar o layout para exibir os botões lado a lado (GridLayoutManager)
                    val numberOfColumns = 2 // Ou o número desejado de colunas
                    recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
                }

                funcionarios.buscarNomesFuncionarios()
            }
        }

        mainButton2.setOnClickListener {
            // Alternar a visibilidade do contêiner de opções
            if (optionsContainer.visibility == View.VISIBLE) {
                optionsContainer.visibility = View.GONE
            } else {
                optionsContainer.visibility = View.VISIBLE

                // Limpar opções anteriores
                produtos.setOnDataLoadedCallback { nomes ->
                    produtoAdapter = ProdutoAdapter(this, nomes)
                    recyclerView.adapter = produtoAdapter

                    // Configurar o layout para exibir os botões lado a lado (GridLayoutManager)
                    val numberOfColumns = 2 // Ou o número desejado de colunas
                    recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
                }

                produtos.buscarNomesProdutos()
            }
        }

        mainButton3.setOnClickListener {
            // Alternar a visibilidade do contêiner de opções
            if (optionsContainer.visibility == View.VISIBLE) {
                optionsContainer.visibility = View.GONE
            } else {
                optionsContainer.visibility = View.VISIBLE

                // Limpar opções anteriores
                maquinas.setOnDataLoadedCallback { nomes ->
                    maquinaAdapter = MaquinaAdapter(this, nomes)
                    recyclerView.adapter = maquinaAdapter

                    // Configurar o layout para exibir os botões lado a lado (GridLayoutManager)
                    val numberOfColumns = 2 // Ou o número desejado de colunas
                    recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
                }

                maquinas.buscarNomesMaquinas()
            }
        }
    }
}
