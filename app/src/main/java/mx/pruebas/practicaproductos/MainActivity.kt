package mx.pruebas.practicaproductos

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import mx.pruebas.practicaproductos.adapter.CustomRecyclerViewAdapter
import mx.pruebas.practicaproductos.data.Download
import mx.pruebas.practicaproductos.entities.ArrayListProductos
import mx.pruebas.practicaproductos.entities.EntidadesProductos
import mx.pruebas.practicaproductos.general.General
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var relativeMainActivity : ScrollView
    private lateinit var linearProductos : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        castItems()
        searchdata.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                downloadInService(this@MainActivity, relativeMainActivity, this@MainActivity, query!!).execute()
                val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(relativeMainActivity.windowToken, 0)
                return true
            }
        })
    }

    class downloadInService(val activity : MainActivity, val view: View, val context: Context, val criterio : String) : AsyncTask<Void, Void, ArrayListProductos>() {

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog, null)
        var alertDialog = AlertDialog.Builder(context)
            .setView(mDialogView)
            .setCancelable(false)
            .create()
        val texto_descarga = mDialogView.findViewById(R.id.texto_descarga) as TextView
        var collecion = ArrayListProductos()

        override fun onPreExecute() {
            texto_descarga.setText("Descargando...")
            alertDialog.show()
        }

        override fun doInBackground(vararg data: Void?): ArrayListProductos {
            try{
                collecion = Download().DownloadProductos(context, criterio)
            } catch (e: Exception){
                Log.e("#ERROR", e.message!!)
            }
            return collecion
        }

        override fun onPostExecute(result: ArrayListProductos?) {
            super.onPostExecute(result)

            if(result!!.size > 0){
                activity.adapter(result)
            }
            General().CustomSnackbar(view, "Listo!!").show()
            alertDialog.dismiss()
        }
    }

    fun adapter(lista : List<EntidadesProductos>) {
        linearProductos.removeAllViews()
        val recycler = General().recyclerViewCreator(this@MainActivity)
            recycler.adapter = CustomRecyclerViewAdapter(this@MainActivity,lista)
        linearProductos.addView(recycler)
    }

    fun castItems() {
        relativeMainActivity = findViewById(R.id.relativeMainActivity)
        linearProductos = findViewById(R.id.linearProductos)
    }

}
