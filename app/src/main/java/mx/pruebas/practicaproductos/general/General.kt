package mx.pruebas.practicaproductos.general

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.snackbar.Snackbar
import mx.pruebas.practicaproductos.R

class General {

    @SuppressLint("ServiceCast")
    fun CheckNetwork(context: Context) : Boolean {
        var state = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.getActiveNetworkInfo()

        if(networkInfo != null && networkInfo.isConnected){
            state = true
        }
        return state
    }

    fun CustomSnackbar(view : View, message : String) : Snackbar {
        val snackbar : Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val snackView : View = snackbar.view
        snackView.setBackgroundColor(view.resources.getColor(R.color.negro, null))
        return snackbar
    }

    fun imageListDinamically(url : String, comentario : String) : ArrayList<SlideModel>{
        val imageList = ArrayList<SlideModel>()
        if(comentario.isEmpty()){
            imageList.add(SlideModel(url))
        } else {
            imageList.add(SlideModel(url, comentario))
        }
        return imageList
    }

    fun recyclerViewCreator(context: Context): RecyclerView {
        val recycler = RecyclerView(context)
//        recycler.isNestedScrollingEnabled = true
//        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL!!, false)
        return recycler
    }

}