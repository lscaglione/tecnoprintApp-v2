package br.com.lucca.tecnprintapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.lucca.tecnprintapp.R
import br.com.lucca.tecnprintapp.db.Funcionarios
import br.com.lucca.tecnprintapp.db.Maquinas
import br.com.lucca.tecnprintapp.db.Produtos
import br.com.lucca.tecnprintapp.db.Rolos
import br.com.lucca.tecnprintapp.db.Sanfonas

class Activity_info_produto : AppCompatActivity() {

    private val funcionarios = Funcionarios()
    private val maquinas = Maquinas()
    private val rolos = Rolos()
    private val sanfonas = Sanfonas()
    private lateinit var recyclerView: RecyclerView
    private lateinit var funcionarioAdapter: FuncionarioAdapter
    private lateinit var maquinaAdapter: MaquinaAdapter
    private lateinit var rolosAdapter: RolosAdapter
    private lateinit var sanfonasAdapter: SanfonasAdapter
    private lateinit var botaoRelatorioRolos: Button
    private lateinit var botaoRelatorioSanfonas: Button
    private lateinit var editPesquisaFuncionario : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_produto)

        botaoRelatorioRolos = findViewById(R.id.botaoRelatorioRolos)
        botaoRelatorioSanfonas = findViewById(R.id.botaoRelatorioSanfonas)
        editPesquisaFuncionario = findViewById(R.id.editTextPesquisaFuncionario)
        botaoRelatorioRolos.visibility = View.GONE
        botaoRelatorioSanfonas.visibility = View.GONE
        editPesquisaFuncionario.visibility = View.GONE


        val mainButton: Button = findViewById(R.id.mainButton)
        val mainButton2: Button = findViewById(R.id.mainButton2)
        val mainButton3: Button = findViewById(R.id.mainButton3)
        recyclerView = findViewById(R.id.optionsRecyclerView)


        val optionsContainer: View = findViewById(R.id.optionsRecyclerView)

        mainButton.setOnClickListener {
            ocultarBotao()



            if(editPesquisaFuncionario.visibility == View.VISIBLE){
                editPesquisaFuncionario.visibility = View.GONE
            }

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



            if(editPesquisaFuncionario.visibility == View.VISIBLE){
                editPesquisaFuncionario.visibility = View.GONE
            }


            // Alternar a visibilidade do contêiner de opções

            if (optionsContainer.visibility == View.VISIBLE) {
                optionsContainer.visibility = View.GONE
            } else {
                botaoRelatorioSanfonas.visibility = View.VISIBLE
                botaoRelatorioRolos.visibility = View.VISIBLE

            }

        }

        mainButton3.setOnClickListener {
            ocultarBotao()



            if(editPesquisaFuncionario.visibility == View.VISIBLE){
                editPesquisaFuncionario.visibility = View.GONE
            }


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

        botaoRelatorioRolos.setOnClickListener {
            ocultarBotao()


            editPesquisaFuncionario.visibility = View.VISIBLE



            if (optionsContainer.visibility == View.VISIBLE) {
                optionsContainer.visibility = View.GONE
            } else {
                optionsContainer.visibility = View.VISIBLE

                // Limpar opções anteriores
                rolos.setOnDataLoadedCallback { nomes ->
                    rolosAdapter = RolosAdapter(this, nomes)
                    recyclerView.adapter = rolosAdapter

                    // Configurar o layout para exibir os botões lado a lado (GridLayoutManager)
                    recyclerView.layoutManager = GridLayoutManager(this, 3)
                }

                rolos.buscarNomesRolos()
            }




        }

        botaoRelatorioSanfonas.setOnClickListener {
            ocultarBotao()

            editPesquisaFuncionario.visibility = View.VISIBLE

            if (optionsContainer.visibility == View.VISIBLE) {
                optionsContainer.visibility = View.GONE
            } else {
                optionsContainer.visibility = View.VISIBLE

                // Limpar opções anteriores
                sanfonas.setOnDataLoadedCallback { nomes ->
                    sanfonasAdapter = SanfonasAdapter(this, nomes)
                    recyclerView.adapter = sanfonasAdapter

                    // Configurar o layout para exibir os botões lado a lado (GridLayoutManager)

                    recyclerView.layoutManager = GridLayoutManager(this, 3)
                }

                sanfonas.buscarNomesSanfonas()
            }


        }


    }
    fun ocultarBotao() {

        botaoRelatorioRolos.visibility = View.GONE
        botaoRelatorioSanfonas.visibility = View.GONE

    }
}
