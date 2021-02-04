package com.example.sites

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.sites.Miradores
import com.example.sites.R

import com.squareup.picasso.Picasso
import java.io.File

class ListaMirandoaAdapter(private val context: Context, private val cercanos: ArrayList<Int>, private val dist: ArrayList<Int>, private val tipo: Boolean, private val dirigir: Boolean) : BaseAdapter() {
    // tipo true para miradores, false para zonas
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val m:Miradores=Miradores
    private val z:Zona=Zona
    //1
    override fun getCount(): Int {
        return cercanos.size
    }

    //2
    override fun getItem(position: Int): Any {
        if(tipo){
            return m.arrayNombres[cercanos[position] as Int]
        }else{
            return z.arrayNombres[cercanos[position] as Int]
        }
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_miradores, parent, false)

        // Get title element
        val titleTextView = rowView.findViewById(R.id.recipe_list_title) as TextView

// Get subtitle element
        val subtitleTextView = rowView.findViewById(R.id.recipe_list_subtitle) as TextView

// Get detail element
        val detailTextView = rowView.findViewById(R.id.recipe_list_detail) as TextView

// Get thumbnail element
        val thumbnailImageView = rowView.findViewById(R.id.recipe_list_thumbnail) as ImageView

        // 1
        val nombre = getItem(position) as String

        if(dirigir && position==0 && tipo){
            rowView.setBackgroundColor(Color.parseColor("#c8e6c9"))
        }

        if(dirigir && position==0 && !tipo){
            rowView.setBackgroundColor(Color.parseColor("#c8e6c9"))
            titleTextView.text = m.arrayNombres[position]
            subtitleTextView.text = m.descripcion[cercanos[position] as Int]
            detailTextView.text =dist[position].toString() + " m"
            Picasso.get().load(m.image[cercanos[position] as Int] ).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)
        }else{
            titleTextView.text = nombre
            if (tipo){
                subtitleTextView.text = m.descripcion[cercanos[position] as Int]
                detailTextView.text =dist[position].toString() + " m"
                Picasso.get().load(m.image[cercanos[position] as Int] ).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)
            }else{
                subtitleTextView.text = z.descripcion[cercanos[position] as Int]
                detailTextView.text =""
                Picasso.get().load(z.image[cercanos[position] as Int] ).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)
            }
        }

// 2


        return rowView
    }

}