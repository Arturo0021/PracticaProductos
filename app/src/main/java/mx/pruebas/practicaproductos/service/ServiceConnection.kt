package mx.pruebas.practicaproductos.service

import org.json.JSONObject
import org.json.JSONStringer
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class ServiceConnection {
    private val TIMEOUT = 10 * 1000
    val url : String = UrlConnection().UrlProduction("url")

    fun HttpCustomPostRequest(METHOD : String, criterio : String) : JSONObject {
        var jsonObject = JSONObject()
        val urlRequest = URL(url + METHOD)
        val connection = urlRequest.openConnection() as HttpURLConnection
            connection.readTimeout = TIMEOUT
            connection.connectTimeout = TIMEOUT
            connection.requestMethod = "POST"
            connection.instanceFollowRedirects = false
            connection.doOutput                = true
            connection.doInput                 = true
            connection.useCaches               = false
            connection.setRequestProperty("charset", "utf-8")
            connection.setRequestProperty("Content-Type", "application/json")
            connection.connect()

        val os = connection.getOutputStream()
        val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
        writer.write(criterio)
        writer.flush()
        writer.close()
        os.close()
        jsonObject =    if(connection.responseCode == HttpURLConnection.HTTP_OK) {
            val stream = BufferedInputStream(connection.inputStream)
            val data: String = readStream(inputStream = stream)
            JSONObject(data)
        } else {
            throw Exception ("${connection.responseCode}")
        }
        return jsonObject
    }

    @Throws(Exception::class)
    fun HttpCustomGetRequest(criterio: String) : JSONObject {

        var jsonobject = JSONObject()
//        var jsonobject : String = ""
        val url_request = URL(url + criterio)
        val connection = url_request.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        connection.setRequestProperty("charset", "utf-8")
        connection.setRequestProperty("Content-Type", "application/json")

        jsonobject = if(connection.responseCode == HttpURLConnection.HTTP_OK) {
            val stream = BufferedInputStream(connection.inputStream)
            val data: String = readStream(inputStream = stream)
            JSONObject(data)
//            data.toString()
        } else {
            throw Exception ("${connection.responseCode}")
        }

        return jsonobject
    }

    fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }
}