package br.com.lucca.tecnprintapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.lucca.tecnprintapp.R

class activity_visualizacao_dados : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizacao_dados)


        val botao = findViewById<Button>(R.id.botaoInserirDados)
        botao.setOnClickListener {
            val intent = Intent(this, activity_insercao_dados::class.java)
            startActivity(intent)
        }
    }
}