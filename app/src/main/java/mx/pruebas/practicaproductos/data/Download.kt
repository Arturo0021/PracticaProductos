package mx.pruebas.practicaproductos.data

import android.content.Context
import android.util.Log
import androidx.core.text.HtmlCompat
import mx.pruebas.practicaproductos.entities.ArrayListProductos
import mx.pruebas.practicaproductos.entities.EntidadesProductos
import mx.pruebas.practicaproductos.service.ServiceConnection
import org.json.JSONArray
import org.json.JSONObject

class Download {

    fun DownloadProductos(context: Context, criterio : String) : ArrayListProductos {
        val service = ServiceConnection()
        val collection = ArrayListProductos()
        val response = service.HttpCustomGetRequest(criterio)
        val json = response.get("plpResults") as JSONObject
        val items = json.get("records") as JSONArray

            if(items.length() != 0) {
                for (i in 0..(items.length()) - 1) {
                    val item = items.getJSONObject(i)
                    val entities = EntidadesProductos()
                        entities.Id  = item.getInt("productId")
                        entities.Sku = item.getInt("skuRepositoryId")
                        entities.Titulo = item.getString("productDisplayName")
                        entities.Precio = item.getDouble("listPrice")
                        entities.Image = item.getString("lgImage")
                    collection.add(entities)
                }
            }

        return collection
    }
}