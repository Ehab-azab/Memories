package com.example.memories.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.memories.R
import com.example.memories.database.Note
import kotlinx.android.synthetic.main.fragment_text.view.*
import kotlinx.android.synthetic.main.item_pager.view.*
import java.io.File

class ViewAdapter(var note: Note) : RecyclerView.Adapter<ViewAdapter.pagerHolder>() {

    inner class pagerHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val title: TextView = itemview.title
        val notedetils: TextView = itemview.details
        val date: TextView = itemview.date
        val itemImage: ImageView = itemview.image_fragment

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAdapter.pagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pager, parent, false)
        return pagerHolder(view)
    }

    override fun getItemCount(): Int {
        return note.pathImage!!.size
    }

    override fun onBindViewHolder(holder: ViewAdapter.pagerHolder, position: Int) {
        holder.itemImage.setImageBitmap(PathToBitmab(note.pathImage!!.get(position)))
        holder.date.setText(note.date)
        holder.title.setText(note.title)
        holder.notedetils.setText(note.description)


    }

    fun PathToBitmab(imagepath: String): Bitmap {
        var imagefile = File(imagepath)
        var imagebitmab: Bitmap = BitmapFactory.decodeFile(imagefile.absolutePath)
        return@PathToBitmab imagebitmab
    }

}