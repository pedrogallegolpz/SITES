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
    "Zona Zaid??n", "Zona Estadio de la Juventud", "Zona Carretera de la Sierra", "Zona Norte", "Zona Cartuja", "Zona Albaicin", "Zona Alhambra", "Zona Realejo", "Zona Generalife",
    "Zona Sacromonte")

    val descripcion = arrayOf("Zona c??ntrica con comercios", "Zona conBares al rededor de la Plaza de toros", "Zona Residencial con bares", "Zona Residencial", "Zona Residencial",
        "Zona Residencial", "Zona Residencial con bares", "Carretera direcci??n la sierra", "Zona Residencial", "Universidades", "Barrio ??rabe Granada", "Palacio ??rabe", "Zona con buenos bares", "Palacio veraniego Alhambra",
        "Zona tradicional Granadina")

    val info = arrayOf("En esta zona se encuentran lugares emblem??ticos de la ciudad como la Catedral, la Capilla Real, la Alcaicer??a, el Zacat??n, el Ayuntamiento, Puerta Real y " +
            "la plaza Bib-Rambla. Tambi??n es la zona comercial m??s importante de la ciudad, concentrada en torno a las calles Recogidas, Mesones, Alh??ndiga, Puentezuelas, Gran V??a de Col??n, " +
            "Reyes Cat??licos y Acera del Darro.",
        "Es el barrio en el que se encuentra situada la plaza de toros y la mayor??a de hospitales de la ciudad. Se caracteriza por sus bares de tapas y cafeter??as.",

        "Es una de las zona m??s nuevas de la ciudad. Adem??s es muy popular por sus innumerables bares de tapas.",

        "Barrio colindante a la Chana y que sirve de nexo de uni??n con el cercano municipio de Maracena. En ??l se encuentra la Escuela de Inform??tica y Telecomunicaciones.",

        "Zona m??s al oeste de Granada, donde se encuentra la Circunvalaci??n y donde la ciudad no ha llegado a extenderse en edificaci??n. Entre colegios e institutos, sigue habiendo huertos y zonas de cultivo.",

        "Es el barrio comprendido entre los r??os Genil y Monachil, situado al sur de la ciudad. Es un barrio de origen humilde y destaca por el Palacio de Congresos, el Palacio de Deportes y el Estadio Nuevo Los C??rmenes.",

        "Zona que abarca los alrededores del Estadio de la Juventud. Y que destaca por sus bares de tapas y su cercan??a con la Facultad de Ciencias.",

        "Zona localizada a la orilla del r??o Genil, bajo la Alhambra, y que fluye en direcci??n a Sierra Nevada. A esta zona pertenecen el barrio del Serrallo y Bola de Oro.",

        "Est?? compuesta los barrios de Almanj??yar, Caser??a de Montijo, Parque Nueva Granada y La Paz. Es la zona m??s humilde de la ciudad y donde se encuentra el Recinto Ferial.",

        "Barrio situado al norte de la ciudad y formado en torno al Monasterio de la Cartuja. Destaca por las vistas de la ciudad y sus facultades.",

        "Es el barrio m??s antiguo de Granada, fue el germen de la actual ciudad y conserva a??n toda la magia de su pasado ??rabe. Es famoso por sus calles " +
            "estrechas, sus iglesias y sus aljibes de la ??poca musulmana. Pero sobre todo destacan sus miradores desde donde se puede contemplar la Alhambra con Sierra Nevada de fondo.",

        "Es una ciudad palatina andalus?? consistente en un conjunto de antiguos palacios, jardines y fortaleza inicialmente concebida para alojar al emir y la corte del Reino nazar??, y " +
                "m??s tarde como residencia real castellana y de sus representantes. Su singularidad art??stica radica en los interiores de los palacios nazar??es, cuya decoraci??n est?? entre las cumbres " +
                "del arte andalus??, as?? como en su localizaci??n y adaptaci??n, generando un paisaje nuevo pero totalmente integrado con la naturaleza preexistente.",

        "Situado en el casco antiguo de la ciudad, a los pies de la Alhambra. Arrabal jud??o de la ciudad musulmana  y que cuando los cristianos tomaron la ciudad, lo renombraron como Realejo. " +
                "En ??l se encuentra una de las lugares m??s famosos de Granada por sus bares, el Campo del Pr??ncipe.",

        "Extenso parque que contin??a a la espalda del Generalife con caminos para pasear, mesas de p??cnic, ruinas y vistas a la ciudad y a la Sierra. Destaca como lugar muy conocido el Llano de la Perdiz.",

        "Se encuentra en el valle de Valpara??so, frente a la Alhambra. Famoso por sus cuevas y por su tradici??n flamenca."
    )

    val image = arrayOf(R.drawable.centro, R.drawable.plazadetoros, R.drawable.chana, R.drawable.etsiit,
        R.drawable.vega, R.drawable.zaidin, R.drawable.juventud, R.drawable.carr_sierra,R.drawable.norte,R.drawable.cartuja2,R.drawable.albaicin,
        R.drawable.alhambra2,R.drawable.realejo,R.drawable.generalife,R.drawable.sacromonte)

    // Comprueba si el punto q est?? en el segmento pr
    fun onSegment(p: Point, q: Point, r: Point): Boolean {
        return if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(
                p.y,
                r.y
            ) && q.y >= Math.min(p.y, r.y)
        ) {
            true
        } else false
    }

    // Devuelve el sentido de un tri??ngulo orientado formado por p, q y r o si son colineales
    // 0 --> colineales
    // 1 --> horario
    // 2 --> antihorario
    fun orientation(p: Point, q: Point, r: Point): Int {
        val `val` = ((q.y - p.y) * (r.x - q.x)
                - (q.x - p.x) * (r.y - q.y))
        if (`val` == 0.0) {
            return 0 // colineal
        }
        return if (`val` > 0) 1 else 2 // horario o antihorario
    }

    fun getIndex(name:String): Int{
        return arrayNombres.indexOf(name)
    }

    // Comprueba si los segmentos p1q1 y p2q2 intersecan
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