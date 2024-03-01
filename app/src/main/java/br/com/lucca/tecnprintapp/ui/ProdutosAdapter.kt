package br.com.lucca.tecnprintapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import br.com.lucca.tecnprintapp.R

class ProdutosAdapter (private val context: Context, private val produtos: List<String>) :
    RecyclerView.Adapter<ProdutosAdapter.ProdutosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_maquina, parent, false)
        return ProdutosViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutosViewHolder, position: Int) {
        val produtos = produtos[position]
        holder.bind(produtos)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    class ProdutosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.maquinaButton)

        fun bind(produtos: String) {
            button.text = produtos
            // Adicione lógica de clique se necessário
        }


    }
}
