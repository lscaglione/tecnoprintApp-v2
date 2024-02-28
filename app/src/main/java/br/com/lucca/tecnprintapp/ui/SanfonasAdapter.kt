package br.com.lucca.tecnprintapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import br.com.lucca.tecnprintapp.R

class SanfonasAdapter(private val context: Context, private val sanfonas: List<String>) :
    RecyclerView.Adapter<SanfonasAdapter.SanfonasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SanfonasViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false)
        return SanfonasViewHolder(view)
    }

    override fun onBindViewHolder(holder: SanfonasViewHolder, position: Int) {
        val sanfonas = sanfonas[position]
        holder.bind(sanfonas)
    }
    override fun getItemCount(): Int {
        return sanfonas.size
    }

    class SanfonasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.produtoButton)

        fun bind(sanfonas: String) {
            button.text = sanfonas
            // Adicione lógica de clique se necessário
        }
    }
}