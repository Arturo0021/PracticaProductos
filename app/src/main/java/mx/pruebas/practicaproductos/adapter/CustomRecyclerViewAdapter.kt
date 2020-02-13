package mx.pruebas.practicaproductos.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_productos.view.*
import mx.pruebas.practicaproductos.Producto
import mx.pruebas.practicaproductos.R
import mx.pruebas.practicaproductos.entities.EntidadesProductos
import mx.pruebas.practicaproductos.general.General

class CustomRecyclerViewAdapter (val context: Context, val items : List<EntidadesProductos>) : RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_productos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(view: ViewHolder, position: Int) {
        view.images.setImageList(General().imageListDinamically(items.get(position).Image!!, items.get(position).Titulo!!))
        view.button.setOnClickListener {
            val intent = Intent(context, Producto::class.java)
                intent.putExtra("producto", items.get(position))
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        var images = itemsView.images
        var button = itemsView.button
    }
}