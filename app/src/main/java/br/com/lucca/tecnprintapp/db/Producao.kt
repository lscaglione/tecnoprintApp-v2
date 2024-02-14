package br.com.lucca.tecnprintapp.db

import java.io.Serializable

data class Producao(
    var funcionario: String = "",
    var maquina: String = "",
    var produto: String = ""
) : Serializable {
    constructor() : this("", "", "")
}
