package br.com.lucca.tecnprintapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import br.com.lucca.tecnprintapp.R

class RolosAdapter(private val context: Context, private val rolos: List<String>) :
    RecyclerView.Adapter<RolosAdapter.RolosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RolosViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false)
        return RolosViewHolder(view)
    }

    override fun onBindViewHolder(holder: RolosViewHolder, position: Int) {
        val rolos = rolos[position]
        holder.bind(rolos)
    }
    override fun getItemCount(): Int {
        return rolos.size
    }

    class RolosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.produtoButton)

        fun bind(rolos: String) {
            button.text = rolos
            // Adicione lógica de clique se necessário
        }
    }
}