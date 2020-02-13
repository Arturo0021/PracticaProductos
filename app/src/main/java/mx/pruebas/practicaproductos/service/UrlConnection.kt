package mx.pruebas.practicaproductos.service

class UrlConnection {

    fun UrlProduction(flag : String = "") : String {
        val url = when(flag) {
                "liverpool" -> "https://www.liverpool.com.mx/tienda?s="
                "url" -> "https://shoppapp.liverpool.com.mx/appclienteservices/services/v3/plp?force-plp=true&search-string="
                    else -> "&d3106047a194921c01969dfdec083925=json"
            }
        return url
    }

}