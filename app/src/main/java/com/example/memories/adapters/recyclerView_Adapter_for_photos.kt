package com.example.memories.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memories.R
import com.example.memories.images.images
import kotlinx.android.synthetic.main.item_view_of_photos_in_fragment_on_add_note.view.*


// 
//recycler View Adapter for photos on add note  
// Created by Ehab Azab on 10/30/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//

class recyclerView_Adapter_for_photos(var photosArray: List<String>) :
    RecyclerView.Adapter<recyclerView_Adapter_for_photos.photos_ViewHolder>() {
    val imagesObject = images()

    class photos_ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var image_in_item_view = itemView.image_in_item_view
        var dleteImage = itemView.close_image_in_item_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): photos_ViewHolder {
        return photos_ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_of_photos_in_fragment_on_add_note, parent, false)
        )
    }

    override fun getItemCount(): Int {
        if (photosArray == null) {
            return 0
        } else {
            return photosArray.size
        }

    }

    override fun onBindViewHolder(holder: photos_ViewHolder, position: Int) {
        holder.image_in_item_view.setImageBitmap(imagesObject.PathToBitmab(photosArray.get(position)))
        if (OnCloseClick != null) {
            holder.dleteImage.setOnClickListener {
                OnCloseClick!!.OnClick(position)
            }
        }

    }

    interface OnImageClickListner {
        fun OnClick(position: Int)

    }

    var OnCloseClick: OnImageClickListner? = null
}