package com.example.memories.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.memories.R


// 
//mains adapter 
// Created by Ehab Azab on 10/21/2020.
// Copyright (c) 2020 Memories. All rights reserved.
//
class mains_adapter(private var image: List<Int>, private var txt: List<String>) :
    RecyclerView.Adapter<mains_adapter.viewHolder>() {


    class viewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var goal_image: ImageView
        var goal_txt: TextView

        init {
            goal_image = itemview.findViewById(R.id.goal_img)
            goal_txt = itemview.findViewById(R.id.goal_txt)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.goals_shape, parent, false)
        )
    }

    //this for call back
    interface ONItemChangedListener {
        fun OndataChange(position: Int, datasize: Int)
    }

    var OnPositionChange: ONItemChangedListener? = null


    override fun getItemCount(): Int {
        if (txt == null) {
            return 0
        }
        return txt.size
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.goal_image.setImageResource(image.get(position))
        holder.goal_txt.text = txt.get(position)
        if (OnPositionChange != null) {
            OnPositionChange?.OndataChange(position, txt.size)
        }
    }
}