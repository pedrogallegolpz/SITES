package com.example.sites

import android.graphics.Point

object Monumentos {

    val sanMiguelBajo= Point(37.180595, -3.596713)
    val catedral= Point(37.176682, -3.599089)
    val bañuelo=Point(37.178359, -3.592887)

    val arraySitios = arrayOf( sanMiguelBajo, catedral, bañuelo)
    val arrayNombres = arrayOf( "Iglesia de San Miguel Bajo" , "Catedral", "El Bañuelo")

    val image = arrayOf( R.drawable.sanmiguelbajo, R.drawable.catedral, R.drawable.banuelo)


    val zona = arrayOf( "Albaicín", "Centro", "Albaicín")

    val descripcion = arrayOf( "La iglesia es de estilo mudéjar", "Catedral de Granada", "Baños árabes del bañuelo")

    val info = arrayOf( "La Iglesia de San Miguel Bajo se erigió como parroquia en 1501 sobre una antigua mezquita. Se levantó durante el siglo XVI, en dos etapas. " +
            "En el primer periodo, comprendido entre 1528 y 1539, se construyó la Capilla Mayor y un tramo de la nave.  Los maestros encargados de las obras fueron, " +
            "Antonio Fernández, albañil y Gil Martín, carpintero. Entre 1551 y 1557 se ejecutó el resto de la nave, siendo Alonso de Villanueva, albañil, y Gabriel Martínez, " +
            "carpintero, los maestros encargados de las obras.",

        "La Santa Apostólica Iglesia Catedral Metropolitana de la Encarnación de Granada es un templo católico de la ciudad española de Granada, sede de la archidiócesis de la " +
            "ciudad. El templo es una de las obras cumbres del Renacimiento español. Durante el renacimiento, el Reino de Granada, al igual que Galicia, conformó un centro artístico " +
            "independiente del estilo predominante en el resto de la península, el herrerianismo. Con el reinado de Carlos I de España se llevarán a cabo numerosas construcciones en la " +
            "ciudad de Granada, dada la intención del monarca en convertir a la urbe en el modelo de ciudad del siglo XVI. Así la construcción de la catedral de Granada será coetánea " +
            "a las del palacio cristiano de la Alhambra, la Universidad y la chancillería.",

        "El Bañuelo es un edificio, declarado Bien de interés cultural, situado en la Carrera del Darro nº. 31, en Granada, comunidad autónoma de Andalucía, España, que contiene un ḥammām o " +
                "baño árabe, de época zirí, siglo XI. En la Granada musulmana, este edificio era el ḥammān del barrio de «Rabad Haxarris» (o de los Axares), conocido como ḥammān " +
                "al-Ŷawza o baño del Nogal.En otras épocas fue también conocido como Baño de Palacios y Baño de la Puerta de Guadix."

        )

    fun getArray(): Array<Point> {
        return arraySitios;
    }
    fun getPosition(posicion:Int): Point {
        return arraySitios[posicion];
    }
    fun getName(posicion:Int): String {
        return arrayNombres[posicion];
    }

    fun getIndex(name:String): Int{
        return arrayNombres.indexOf(name)
    }

    fun getCount(): Int {
        return arrayNombres.size
    }

    class Point(var lat: Double, var lon: Double)

}