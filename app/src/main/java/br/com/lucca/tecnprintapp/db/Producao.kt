package br.com.lucca.tecnprintapp.db

import java.io.Serializable

// Garanta que sua classe seja um data class (caso ainda não seja)
// e adicione os valores default para todos os parâmetros do construtor.
data class Producao(
    var funcionario: String = "", // Adicione valores default
    var maquina: String = "",
    var produto: String = ""
) : Serializable {
    // Firestore precisa de um construtor sem argumentos
    constructor() : this("", "", "")
}
