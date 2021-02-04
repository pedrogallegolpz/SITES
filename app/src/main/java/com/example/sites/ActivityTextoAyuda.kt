package com.example.sites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class ActivityTextoAyuda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_texto_ayuda)

        var nombreElegido = intent.getStringExtra("NOMBRE").toString()
        var position = intent.getStringExtra("POS").toString()

        var text : TextView = findViewById(R.id.textView)
        var titulo : TextView = findViewById(R.id.titulo)

        titulo.text = "   AYUDA:  "+ nombreElegido

        if (position.toInt()==0){
            text.text="Pulsando en SitesBot tendrá a su disposición un bot que le dará información y le resolverá preguntas acerca de los miradores y de las zonas de Granada, " +
                    "además si usted no sabe donde ir le recomendará un sitio. También podrá jugar con él cuando usted se encuentre en un mirador, el " +
                    "bot intentará adivinar donde se encuentra."
        }else if(position.toInt()==1){
            text.text="En Visión usted accederá a la visión de la cámara trasera de su dispositivo. Y podrá realizar las siguientes acciones:\n" +
                    "\t - Cuando usted se encuentre en un mirador, al apuntar a las vistas con su cámara, aparecerán en una lista abajo las zonas que está observando " +
                    "y la distancia a la que se encuentran. Pulsando en una zona de la lista podrá obtener información de ella.\n" +
                    "\t - Cuando usted no se encuentre en un mirador, en la cámara aparecerá la zona en la que se encuentra y los miradores que estén en la dirección apuntada por la cámara. " +
                    "Pulsando en un mirador aparecerá su información. Y pulsando en el botón de la flecha, se le dirigirá hacia él mediante un indicador en el modo visión. Además le mostrará " +
                    "la distancia al destino en todo momento.\n" +
                    "\t - Si en su trayecto dentro del modo Visión se encuentra cercano a un monumento, le aparecerá un mensaje con la distancia a él, y tocando en el mensaje, le mostrará información de este"

        }else if(position.toInt()==2){
            text.text="En Miradores podrá acceder a la lista de miradores disponible en la aplicación, y pulsando en uno de ellos podrá obtener más información de él y tendrá la opción de dirigirse hacia él en " +
                    "el modo Visión pulsando en la flecha."
        }else if(position.toInt()==3){
            text.text="Accediendo al modo Mapa aparecerán varias chinchetas donde la roja marca su ubicación y las azules la localización de los miradores. " +
                    "Al pulsar en cada chincheta de mirador se mostrará una imagen del mirador y pulsando en la imagen se le mostrará más información y se le permitirá ir a él en el modo Visión. " +
                    "Se actualizará su ubicación cada cierto tiempo y al pasar la mano por encima de la pantalla. Al pulsar en las chinchetas de mirador, también aparecerán abajo a la derecha dos botones. Uno le llevará " +
                    "a la localización de la chincheta en Google Maps y el otro le llevara a Google Maps mostrándole la ruta desde su ubicación al mirador."
        }
    }
}