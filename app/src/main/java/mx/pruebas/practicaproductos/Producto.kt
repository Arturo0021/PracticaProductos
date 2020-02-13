package mx.pruebas.practicaproductos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import mx.pruebas.practicaproductos.entities.EntidadesProductos

class Producto : AppCompatActivity() {

    private var entidadesProductos = EntidadesProductos()
    private lateinit var image_productos : ImageView
    private lateinit var producto : TextView
    private lateinit var precio : TextView
    private lateinit var sku : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)
        castItems()
        entidadesProductos = intent.getSerializableExtra("producto") as EntidadesProductos

        Glide.with(this@Producto).load(entidadesProductos.Image).into(image_productos)
        producto.text = "${entidadesProductos.Titulo}"
        precio.text   = "Precio: \n$ ${entidadesProductos.Precio}"
        sku.text      = "Sku: \n${entidadesProductos.Sku}"

    }

    fun castItems() {
        image_productos = findViewById(R.id.image_productos)
        producto = findViewById(R.id.producto)
        precio = findViewById(R.id.precio)
        sku = findViewById(R.id.sku)
    }
}
