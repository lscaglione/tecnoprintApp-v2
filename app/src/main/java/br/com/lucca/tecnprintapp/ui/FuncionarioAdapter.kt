package br.com.lucca.tecnprintapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.lucca.tecnprintapp.R

class FuncionarioAdapter(private val context: Context, private val funcionarios: List<String>) :
    RecyclerView.Adapter<FuncionarioAdapter.FuncionarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncionarioViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_funcionario, parent, false)
        return FuncionarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: FuncionarioViewHolder, position: Int) {
        val funcionario = funcionarios[position]
        holder.bind(funcionario)
    }

    override fun getItemCount(): Int {
        return funcionarios.size
    }

    class FuncionarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.funcionarioButton)

        fun bind(funcionario: String) {
            button.text = funcionario

            button.setOnClickListener {
                // Exibir um Toast com o nome do funcionário
                Toast.makeText(itemView.context, "Clicou em: $funcionario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}