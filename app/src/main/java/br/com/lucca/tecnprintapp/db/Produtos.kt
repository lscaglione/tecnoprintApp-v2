package br.com.lucca.tecnprintapp.db

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class Produtos {

    private val db = FirebaseFirestore.getInstance()
    private var onDataLoaded: ((List<String>) -> Unit)? = null

    fun setOnDataLoadedCallback(callback: (List<String>) -> Unit) {
        onDataLoaded = callback
    }

    fun buscarNomesProdutos() {
        db.collection("Produtos")
            .get()
            .addOnSuccessListener { resultado ->
                val nomes = mutableListOf<String>()
                for (documento in resultado) {
                    documento.getString("nome")?.let { nome ->
                        nomes.add(nome)
                    }
                }
                onDataLoaded?.invoke(nomes)
            }
            .addOnFailureListener { exception ->
                Log.d("Funcionarios", "Erro ao buscar documentos: ", exception)
            }
    }
}