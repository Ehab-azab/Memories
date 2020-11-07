package com.example.memories.adapters

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
import kotlinx.android.synthetic.main.home_image_item_inrecyclerview.view.*
import java.io.File
import java.util.*


// 
//home recyclerView Adapter to adapt viewing data in recyclerView at Home page 
// Created by Ehab Azab on 10/29/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
class Home_RecyclerView_Adapter(var list: ArrayList<Note>) :
    RecyclerView.Adapter<Home_RecyclerView_Adapter.homerecyclerView_ViewHolder_1>() {


    class homerecyclerView_ViewHolder_1(itemview: View) : RecyclerView.ViewHolder(itemview) {
        //image View
        var image_note_image_tv: ImageView = itemview.note_image
        var title_image_tv: TextView = itemview.title_image
        var date_image_tv: TextView = itemview.date_tv_image
        var description_image_tv: TextView = itemview.description_iamge

    }

    fun PathToBitmab(imagepath: String): Bitmap {
        var imagefile = File(imagepath)
        var imagebitmab: Bitmap = BitmapFactory.decodeFile(imagefile.absolutePath)
        return imagebitmab
    }


    override fun getItemViewType(position: Int): Int {
        if (list.get(position).pathImage?.size!! > 0) {
            return 1
        } else {
            return 0
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): homerecyclerView_ViewHolder_1 {
        return Home_RecyclerView_Adapter.homerecyclerView_ViewHolder_1(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_image_item_inrecyclerview,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        if (list != null) {
            return list.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: homerecyclerView_ViewHolder_1, position: Int) {
        when (holder.itemViewType) {
            1 -> {
                holder.date_image_tv.setText(list[position].date)
                holder.title_image_tv.setText(list[position].title)
                holder.description_image_tv.setText(list[position].description)
                holder.image_note_image_tv.setImageBitmap(PathToBitmab(list[position].pathImage!![0]))
            }
            0 -> {
                holder.date_image_tv.setText(list[position].date)
                holder.title_image_tv.setText(list[position].title)
                holder.description_image_tv.setText(list[position].description)
            }
        }

        if (onitemclicked != null) {
            holder.itemView.setOnClickListener {
                onitemclicked!!.OnItemClicked(position, noteId = list[position].id)
            }

        }
    }

    interface OnItemClickListener {
        fun OnItemClicked(position: Int, noteId: Int?)
    }

    var onitemclicked: OnItemClickListener? = null
    fun changedata(Lista: ArrayList<Note>) {
        this.list = Lista
    }

}














