package mx.pruebas.practicaproductos.entities

import java.io.Serializable

class EntidadesProductos : Serializable {
    var Id : Int?            = 0
    var Sku : Int?           = 0
    var Titulo : String?     = ""
    var Precio : Double?     = 0.0
    var Ubicacion : String ? = ""
    var Image : String?      = ""
}