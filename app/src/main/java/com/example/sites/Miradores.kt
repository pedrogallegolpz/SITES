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

    val image_dest = arrayOf( R.drawable.miradorsannicolas_dest,R.drawable.torredelavela_dest,R.drawable.sanmiguel_dest,R.drawable.sillamoro_dest,
        R.drawable.cartuja_dest,R.drawable.barranco_dest,R.drawable.monasterio_dest)


    val zona = arrayOf( "Albaicín", "Alhambra",  "Albaicín", "Alhambra", "Cartuja", "Realejo",
        "Centro")

    val descripcion = arrayOf( "Se ve la alhambra", "Se ve el Albaicín",
        "Se ve toda Granada", "En la dehesa de la Alhambra", "Se ve Granada ciudad", "Se ve todo el Realejo",
        "Lugar céntrico para copas")

    val info = arrayOf( "El Mirador de San Nicolás se sitúa en el Albaicín, en la parte más alta de la Alcazaba Qadima de los Ziries, junto a la Iglesia de San Nicolás, de donde toma su nombre. " +
            "Es una plaza de forma cuadrada, con árboles alrededor, bancos de piedra, una cruz de piedra en medio y el típico empedrado granadino. Se puede observar el Generalife, " +
            "la Alhambra y Sierra Nevada, y a sus pies el rio Darro y el Paseo de los Tristes. Además, se puede ver la zona Centro con la Catedral.",

        "La Torre de la Vela forma parte del conjunto monumental de la Alhambra, siendo la torre más grande de la Alcazaba. Desde lo alto de la Torre de La Vela se disfruta de una de las mejores " +
         "vistas que se pueden tener de Granada, su Vega y Sierra Nevada. Además de ser un sitio privilegiado por poder contemplarse desde allí el interior de la Alhambra, el Albaicín, el Sacromonte, " +
         "el Realejo y la zona Centro donde destaca la Catedral.",

        "Se sitúa en el Albaicín y es el más elevado de los miradores de Granada. Desde él se contempla una amplia panorámica de la ciudad, del Albaicín y sus murallas, de la Alhambra y Sierra Nevada. " +
         "Se accede a él después de una larga y empinada caminata. Está situado ante la Ermita de San Miguel Alto. "

        , "Este mirador se encuentra situado, a modo de balcón, en la Dehesa del Generalife. La llamada Silla del Moro, es un pequeño castillo de época nazarí construido para la vigilancia y protección " +
          "del Generalife. Y desde allí podemos observar la cuenca del río Darro en su recorrido hacia la Alhambra, mientras sus aguas se distribuían por las históricas acequias de los caminos. En las laderas, " +
           "lugares tan emblemáticos como la Abadía del Sacromonte, el cerro de San Miguel o los tan representativos barrios del Albaicín y el Sacromonte.",

        "Sobre los altos de Cartuja, tras la Facultad de Psicología encontramos este mirador. Desde el torreón se puede observar la zona más al norte de la ciudad y el valle de Granada en toda su plenitud. " +
         "Es una vista muy distinta a la que muestran los demás miradores de la ciudad.",

        "Está situado en la carretera de acceso a La Alhambra, cerca del parking público del monumento. Se puede contemplar el Paseo del Violón y el cauce del río Genil, además de las zonas Carretera de la " +
         "Sierra y Zaidín. Desde él se divisa la vista más amplia de la ciudad de Granada y su Vega.",

        "El Monasterio Chill Out es una de las mejores terrazas de la ciudad de Granada. Situada en la azotea del hotel que está frente al Monasterio de San Jerónimo, es uno de los mejores lugares donde poder " +
         "tomar algo tranquilamente. Desde aquí tenemos unas de las mejores vistas que hay en el centro de Granada, ya que desde su terraza más alta podemos ver la Alhambra, el Albaicín, la Catedral y toda la Vega." )

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