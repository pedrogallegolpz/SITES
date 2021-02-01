package com.example.sites

import android.graphics.Point

object Miradores {

    val sanNicolas= Point(37.181104,-3.592653)
    val torreVelaAlhambra = Point(37.177008,-3.592265)
    val sanMiguelAlto = Point(37.184734,-3.587869)
    val sillaDelMoro = Point(37.178313,-3.583707)
    val torreonDeCartuja = Point(37.194950,-3.593758)
    val barrancoDelAbogado = Point(37.170033,-3.584908)
    val monasterioChillOut = Point(37.178866,-3.603495)
    val arraySitios = arrayOf( sanNicolas, torreVelaAlhambra, sanMiguelAlto, sillaDelMoro,
        torreonDeCartuja, barrancoDelAbogado, monasterioChillOut)
    val arrayNombres = arrayOf( "Mirador de San Nicolás", "Mirador Torre de la Vela de la Alhambra",
        "Mirador San Miguel Alto", "Mirador Silla del Moro", "Mirador Torreón de Cartuja", "Mirador Barranco del Abogado",
        "Mirador Monasterio Chill-Out")

    val image = arrayOf( R.drawable.miradorsannicolas,R.drawable.torredelavela,R.drawable.sanmiguel,R.drawable.sillamoro,
        R.drawable.cartuja,R.drawable.barranco,R.drawable.monasterio)


    val zona = arrayOf( "Albaicín", "Alhambra",  "Albaicín", "Alhambra", "Cartuja", "Realejo",
        "Centro")

    val descripcion = arrayOf( "Mirador donde se ve la alhambra", "Mirador donde se ve el Albaicín",
        "Mirador donde se ve toda Granada", "Mirador en la dehesa de la Alhambra", "Mirador donde vemos Granada ciudad", "Mirador donde vemos todo el Realejo",
        "Mirador céntrico para copas")

    fun getArray(): Array<Point> {
        return arraySitios;
    }
    fun getPosition(posicion:Int): Point {
        return arraySitios[posicion];
    }
    fun getName(posicion:Int): String {
        return arrayNombres[posicion];
    }

    fun getCount(): Int {
        return arrayNombres.size
    }

    class Point(var lat: Double, var lon: Double)

}