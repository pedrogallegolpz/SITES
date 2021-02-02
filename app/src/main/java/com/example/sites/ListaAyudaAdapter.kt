package com.example.sites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ListaAyudaAdapter (private val context: Context, private val names: Array<String>, private val images: Array<Int>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //1
    override fun getCount(): Int {
        return names.size
    }

    //2
    override fun getItem(position: Int): Any {
        return names[position]
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

// 2
        titleTextView.text = nombre
        subtitleTextView.text = ""
        detailTextView.text = ""

// 3    image
        Picasso.get().load(images[position] ).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView) //"@drawable/miradorSanNicolas"
        //thumbnailImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.miradorsannicolas));

        return rowView
    }

}
