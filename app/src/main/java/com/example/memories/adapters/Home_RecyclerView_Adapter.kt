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
import kotlinx.android.synthetic.main.home_text_item_inrecyclerview.view.*
import java.io.File
import java.util.*


// 
//home recyclerView Adapter to adapt viewing data in recyclerView at Home page 
// Created by Ehab Azab on 10/29/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
class Home_RecyclerView_Adapter(var list: ArrayList<Note>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class homerecyclerView_ViewHolder_0(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var title_txt_tv: TextView = itemview.title
        var date_txt_tv: TextView = itemview.date_tv
        var description_txt_tv: TextView = itemview.description


    }

    class homerecyclerView_ViewHolder_1(itemview: View) : RecyclerView.ViewHolder(itemview) {
        //image View
        var image_note_image_tv: ImageView = itemview.note_image
        var title_image_tv: TextView = itemview.title_image
        var date_image_tv: TextView = itemview.title_image
        var description_image_tv: TextView = itemview.description_iamge

    }

    override fun getItemViewType(position: Int): Int {
        if (list.get(position).pathImage?.size == 0) {
            return 0
        } else {
            return 1
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            return@onCreateViewHolder homerecyclerView_ViewHolder_0(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.home_text_item_inrecyclerview,
                    parent, false
                )
            )
        } else {
            return@onCreateViewHolder homerecyclerView_ViewHolder_1(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.home_image_item_inrecyclerview,
                    parent, false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        if (list != null) {
            return list.size
        } else {
            return 0
        }

    }

    interface OnItemClickListener {
        fun OnItemClicked(position: Int, noteId: Int?)
    }

    var onitemclicked: OnItemClickListener? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 1) {
            val viewHolder1: homerecyclerView_ViewHolder_1 = holder as homerecyclerView_ViewHolder_1
            if (onitemclicked != null) {
                viewHolder1.itemView.setOnClickListener {
                    onitemclicked!!.OnItemClicked(position, noteId = list[position].id)
                }
                viewHolder1.date_image_tv.setText(list[position].date)
                viewHolder1.title_image_tv.setText(list[position].title)
                viewHolder1.description_image_tv.setText(list[position].description)


                //viewHolder1.image_note_image_tv.setImageBitmap(PathToBitmab(list[position].pathImage!!.get(0)))


            } else {
                val viewHolder0: homerecyclerView_ViewHolder_0 =
                    holder as homerecyclerView_ViewHolder_0
                viewHolder0.date_txt_tv.setText(list[position].date)
                viewHolder0.title_txt_tv.setText(list[position].title)
                viewHolder0.description_txt_tv.setText(list[position].description)
                if (onitemclicked != null) {
                    viewHolder0.itemView.setOnClickListener {
                        onitemclicked!!.OnItemClicked(position, noteId = list[position].id)
                    }

                }
            }

        }


    }

    fun PathToBitmab(imagepath: String): Bitmap {
        var imagefile = File(imagepath)
        var imagebitmab: Bitmap = BitmapFactory.decodeFile(imagefile.absolutePath)
        return@PathToBitmab imagebitmab
    }
}
