package br.com.lucca.tecnprintapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import br.com.lucca.tecnprintapp.R

class MaquinaAdapter(private val context: Context, private val maquinas: List<String>) :
    RecyclerView.Adapter<MaquinaAdapter.MaquinaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaquinaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_maquina, parent, false)
        return MaquinaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MaquinaViewHolder, position: Int) {
        val maquina = maquinas[position]
        holder.bind(maquina)
    }

    override fun getItemCount(): Int {
        return maquinas.size
    }

    class MaquinaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.maquinaButton)

        fun bind(maquina: String) {
            button.text = maquina
            // Adicione lógica de clique se necessário
        }
    }
}
