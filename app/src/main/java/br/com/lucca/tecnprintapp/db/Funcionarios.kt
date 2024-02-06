package br.com.lucca.tecnprintapp.db

import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log

class Funcionarios {

    private val db = FirebaseFirestore.getInstance()
    private var onDataLoaded: ((List<String>) -> Unit)? = null

    fun setOnDataLoadedCallback(callback: (List<String>) -> Unit) {
        onDataLoaded = callback
    }

    fun buscarNomesFuncionarios() {
        db.collection("Funcionários")
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
