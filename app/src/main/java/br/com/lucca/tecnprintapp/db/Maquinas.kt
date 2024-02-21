package br.com.lucca.tecnprintapp.db

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class Maquinas {

    private val db = FirebaseFirestore.getInstance()
    private var onDataLoaded: ((List<String>) -> Unit)? = null

    //Callback
    fun setOnDataLoadedCallback(callback: (List<String>) -> Unit) {
        onDataLoaded = callback
    }

    //Busca os nomes dos funcionários e adiciona a lista nomes
    fun buscarNomesMaquinas() {
        db.collection("Máquinas")
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