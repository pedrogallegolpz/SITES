package com.example.sites

import android.graphics.Point

internal object Miradores {

    val sanNicolas= Point(37.181104,-3.592653);
    val torreVelaAlhambra = Point(37.177008,-3.592265)
    val sanMiguelAlto = Point(37.184734,-3.587869)
    val sillaDelMoro = Point(37.178313,-3.583707)
    val torreonDeCartuja = Point(37.194950,-3.593758)
    val barrancoDelAbogado = Point(37.170033,-3.584908)
    val monasterioChillOut = Point(37.178866,-3.603495)
    val arraySitios = arrayOf( sanNicolas, torreVelaAlhambra, sanMiguelAlto, sillaDelMoro,
        torreonDeCartuja, barrancoDelAbogado, monasterioChillOut)
    val arrayNombres = arrayOf("Mirador de San Nicolás", "Mirador Torre de la Vela de la Alhambra",
        "Mirador San Miguel Alto", "Mirador Silla del Moro", "Mirador Torreón de Cartuja", "Mirador Barranco del Abogado",
        "Mirador Monasterio Chill-Out")

    fun getArray(): Array<Point> {
        return arraySitios;
    }
    fun getPosition(posicion:Int): Point {
        return arraySitios[posicion];
    }
    fun getName(posicion:Int): String {
        return arrayNombres[posicion];
    }

    class Point(var lat: Double, var lon: Double)

}