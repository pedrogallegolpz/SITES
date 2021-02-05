package com.example.sites

object Zona {
    // Define Infinite (Using INT_MAX
    // caused overflow problems)
    var INF = 10000.99999

    val centro = arrayOf(
        Point(37.18471, -3.60176),
        Point(37.17767, -3.6104),
        Point(37.16372, -3.60692),
        Point(37.16944, -3.5933)
    )

    val plazaToros = arrayOf(
        Point(37.18471, -3.60176),
        Point(37.17767, -3.6104),
        Point(37.18826, -3.61482),
        Point( 37.19165, -3.60795)
    )

    val chana = arrayOf(
        Point(37.18826, -3.61482),
        Point(37.19716, -3.62062),
        Point(37.19309, -3.63445),
        Point(37.18062, -3.62164)
    )

    val cerrillo = arrayOf(
        Point(37.19309, -3.63445),
        Point(37.19716, -3.62062),
        Point(37.20478, -3.62608),
        Point(37.20099, -3.63033)
    )

    val vega = arrayOf(
        Point(37.18062, -3.62164),
        Point(37.17767, -3.6104),
        Point(37.16372, -3.60692),
        Point(37.15929, -3.61508)
    )

    val zaidin = arrayOf(
        Point(37.16944, -3.5933),
        Point(37.15929, -3.61508),
        Point(37.14271, -3.60748),
        Point(37.15726, -3.5818)
    )

    val juventud = arrayOf(
        Point(37.18826, -3.61482),
        Point(37.18062, -3.62164),
        Point(37.17935, -3.61639),
        Point(37.17767, -3.6104)
    )

    val carreteraSierra = arrayOf(
        Point(37.15726, -3.5818),
        Point(37.16246, -3.57112),
        Point(37.17445, -3.58384),
        Point(37.16944, -3.5933)
    )

    val norte = arrayOf(
        Point(37.20478, -3.62608),
        Point(37.18826, -3.61482),
        Point(37.19673, -3.59257),
        Point(37.21361, -3.6024)
    )

    val cartuja = arrayOf(
        Point(37.19165, -3.60795),
        Point(37.19673, -3.59257),
        Point(37.18944, -3.58644),
        Point(37.18471, -3.60176)
    )

    val albaicin = arrayOf(
        Point(37.18471, -3.60176),
        Point(37.18944, -3.58644),
        Point(37.17903, -3.58826),
        Point(37.17554, -3.59668)
    )

    val alhambra = arrayOf(
        Point(37.17903, -3.58826),
        Point(37.17445, -3.58384),
        Point(37.17195, -3.58828),
        Point(37.17715, -3.59321)
    )

    val realejo = arrayOf(
        Point(37.17554, -3.59668),
        Point(37.16944, -3.5933),
        Point(37.17195, -3.58828),
        Point(37.17715, -3.59321)
    )

    val generalife = arrayOf(
        Point(37.17903, -3.58826),
        Point(37.16246, -3.57112),
        Point(37.16714, -3.56034),
        Point(37.18286, -3.57521)
    )

    val sacromonte = arrayOf(
        Point(37.18944, -3.58644),
        Point(37.17903, -3.58826),
        Point(37.18286, -3.57521),
        Point(37.18889, -3.57993)
    )
    val arrayZonas = arrayOf(centro, plazaToros, chana, cerrillo, vega, zaidin, juventud, carreteraSierra, norte,
    cartuja, albaicin, alhambra, realejo, generalife, sacromonte)
    val arrayNombres = arrayOf("Zona Centro", "Zona Plaza de Toros", "Zona Chana", "Zona Cerrillo de Maracena", "Zona de la Vega",
    "Zona Zaidín", "Zona Estadio de la Juventud", "Zona Carretera de la Sierra", "Zona Norte", "Zona Cartuja", "Zona Albaicin", "Zona Alhambra", "Zona Realejo", "Zona Generalife",
    "Zona Sacromonte")

    val descripcion = arrayOf("Zona céntrica con comercios", "Zona conBares al rededor de la Plaza de toros", "Zona Residencial con bares", "Zona Residencial", "Zona Residencial",
        "Zona Residencial", "Zona Residencial con bares", "Carretera dirección la sierra", "Zona Residencial", "Universidades", "Barrio Árabe Granada", "Palacio Árabe", "Zona con buenos bares", "Palacio veraniego Alhambra",
        "Zona tradicional Granadina")

    val info = arrayOf("En esta zona se encuentran lugares emblemáticos de la ciudad como la Catedral, la Capilla Real, la Alcaicería, el Zacatín, el Ayuntamiento, Puerta Real y " +
            "la plaza Bib-Rambla. También es la zona comercial más importante de la ciudad, concentrada en torno a las calles Recogidas, Mesones, Alhóndiga, Puentezuelas, Gran Vía de Colón, " +
            "Reyes Católicos y Acera del Darro.",
        "Es el barrio en el que se encuentra situada la plaza de toros y la mayoría de hospitales de la ciudad. Se caracteriza por sus bares de tapas y cafeterías.",

        "Es una de las zona más nuevas de la ciudad. Además es muy popular por sus innumerables bares de tapas.",

        "Barrio colindante a la Chana y que sirve de nexo de unión con el cercano municipio de Maracena. En él se encuentra la Escuela de Informática y Telecomunicaciones.",

        "Zona más al oeste de Granada, donde se encuentra la Circunvalación y donde la ciudad no ha llegado a extenderse en edificación. Entre colegios e institutos, sigue habiendo huertos y zonas de cultivo.",

        "Es el barrio comprendido entre los ríos Genil y Monachil, situado al sur de la ciudad. Es un barrio de origen humilde y destaca por el Palacio de Congresos, el Palacio de Deportes y el Estadio Nuevo Los Cármenes.",

        "Zona que abarca los alrededores del Estadio de la Juventud. Y que destaca por sus bares de tapas y su cercanía con la Facultad de Ciencias.",

        "Zona localizada a la orilla del río Genil, bajo la Alhambra, y que fluye en dirección a Sierra Nevada. A esta zona pertenecen el barrio del Serrallo y Bola de Oro.",

        "Está compuesta los barrios de Almanjáyar, Casería de Montijo, Parque Nueva Granada y La Paz. Es la zona más humilde de la ciudad y donde se encuentra el Recinto Ferial.",

        "Barrio situado al norte de la ciudad y formado en torno al Monasterio de la Cartuja. Destaca por las vistas de la ciudad y sus facultades.",

        "Es el barrio más antiguo de Granada, fue el germen de la actual ciudad y conserva aún toda la magia de su pasado árabe. Es famoso por sus calles " +
            "estrechas, sus iglesias y sus aljibes de la época musulmana. Pero sobre todo destacan sus miradores desde donde se puede contemplar la Alhambra con Sierra Nevada de fondo.",

        "Es una ciudad palatina andalusí consistente en un conjunto de antiguos palacios, jardines y fortaleza inicialmente concebida para alojar al emir y la corte del Reino nazarí, y " +
                "más tarde como residencia real castellana y de sus representantes. Su singularidad artística radica en los interiores de los palacios nazaríes, cuya decoración está entre las cumbres " +
                "del arte andalusí, así como en su localización y adaptación, generando un paisaje nuevo pero totalmente integrado con la naturaleza preexistente.",

        "Situado en el casco antiguo de la ciudad, a los pies de la Alhambra. Arrabal judío de la ciudad musulmana  y que cuando los cristianos tomaron la ciudad, lo renombraron como Realejo. " +
                "En él se encuentra una de las lugares más famosos de Granada por sus bares, el Campo del Príncipe.",

        "Extenso parque que continúa a la espalda del Generalife con caminos para pasear, mesas de pícnic, ruinas y vistas a la ciudad y a la Sierra. Destaca como lugar muy conocido el Llano de la Perdiz.",

        "Se encuentra en el valle de Valparaíso, frente a la Alhambra. Famoso por sus cuevas y por su tradición flamenca."
    )

    val image = arrayOf(R.drawable.centro, R.drawable.plazadetoros, R.drawable.sillamoro, R.drawable.sillamoro,
        R.drawable.vega, R.drawable.zaidin, R.drawable.juventud, R.drawable.carr_sierra,R.drawable.norte,R.drawable.cartuja2,R.drawable.albaicin,
        R.drawable.alhambra2,R.drawable.realejo,R.drawable.generalife,R.drawable.sacromonte)

    // Given three colinear points p, q, r,
    // the function checks if point q lies
    // on line segment 'pr'
    fun onSegment(p: Point, q: Point, r: Point): Boolean {
        return if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(
                p.y,
                r.y
            ) && q.y >= Math.min(p.y, r.y)
        ) {
            true
        } else false
    }

    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    fun orientation(p: Point, q: Point, r: Point): Int {
        val `val` = ((q.y - p.y) * (r.x - q.x)
                - (q.x - p.x) * (r.y - q.y))
        if (`val` == 0.0) {
            return 0 // colinear
        }
        return if (`val` > 0) 1 else 2 // clock or counterclock wise
    }
    fun getIndex(name:String): Int{
        return arrayNombres.indexOf(name)
    }

    // The function that returns true if
    // line segment 'p1q1' and 'p2q2' intersect.
    fun doIntersect(
        p1: Point, q1: Point,
        p2: Point, q2: Point
    ): Boolean {
        // Find the four orientations needed for
        // general and special cases
        val o1 = orientation(p1, q1, p2)
        val o2 = orientation(p1, q1, q2)
        val o3 = orientation(p2, q2, p1)
        val o4 = orientation(p2, q2, q1)

        // General case
        if (o1 != o2 && o3 != o4) {
            return true
        }

        // Special Cases
        // p1, q1 and p2 are colinear and
        // p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) {
            return true
        }

        // p1, q1 and p2 are colinear and
        // q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) {
            return true
        }

        // p2, q2 and p1 are colinear and
        // p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) {
            return true
        }

        // p2, q2 and q1 are colinear and
        // q1 lies on segment p2q2
        return if (o4 == 0 && onSegment(p2, q1, q2)) {
            true
        } else false

        // Doesn't fall in any of the above cases
    }

    // Returns true if the point p lies
    // inside the polygon[] with n vertices
    fun isInside(polygon: Array<Point>, n: Int, p: Point): Boolean {
        // There must be at least 3 vertices in polygon[]
        if (n < 3) {
            return false
        }

        // Create a point for line segment from p to infinite
        val extreme = Point(INF, p.y)

        // Count intersections of the above line
        // with sides of polygon
        var count = 0
        var i = 0
        do {
            val next = (i + 1) % n

            // Check if the line segment from 'p' to
            // 'extreme' intersects with the line
            // segment from 'polygon[i]' to 'polygon[next]'
            if (doIntersect(polygon[i], polygon[next], p, extreme)) {
                // If the point 'p' is colinear with line
                // segment 'i-next', then check if it lies
                // on segment. If it lies, return true, otherwise false
                if (orientation(polygon[i], p, polygon[next]) == 0) {
                    return onSegment(
                        polygon[i], p,
                        polygon[next]
                    )
                }
                count++
            }
            i = next
        } while (i != 0)

        // Return true if count is odd, false otherwise
        return count % 2 == 1 // Same as (count%2 == 1)
    }

    // devuelve el punto del medio
    fun puntoMedio( i : Int) :Point {
        var a = (arrayZonas[i][2].y - arrayZonas[i][0].y ) / (arrayZonas[i][2].x - arrayZonas[i][0].x )
        var b = (arrayZonas[i][3].y - arrayZonas[i][1].y ) / (arrayZonas[i][3].x - arrayZonas[i][1].x )

        var x=(-arrayZonas[i][0].y+arrayZonas[i][1].y + a*arrayZonas[i][0].x - b*arrayZonas[i][1].x )/(a-b)
        var y= a * (x-arrayZonas[i][0].x) + arrayZonas[i][0].y

        return Point(x,y)
    }

    class Point(var x: Double, var y: Double)
}