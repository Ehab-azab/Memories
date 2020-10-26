package com.example.memories.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.memories.R
import com.example.memories.database.Note
import kotlinx.android.synthetic.main.item_pager.view.*

class ViewAdapter (var title : String ,var details : String,var image : MutableList<Int> )
    :RecyclerView.Adapter<ViewAdapter.pagerHolder>() {

    inner class pagerHolder(itemview:View) : RecyclerView.ViewHolder(itemview){
     val itemTitle :  TextView = itemview.text_title
        val itemDetails :  TextView = itemview.text_words
        val itemImage :  ImageView = itemview.image_fragment

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAdapter.pagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pager,parent,false)
        return pagerHolder(view)
    }

    override fun getItemCount(): Int {
        return image.size
    }

    override fun onBindViewHolder(holder: ViewAdapter.pagerHolder, position: Int) {
        holder.itemTitle.text = title
        holder.itemDetails.text = title
        holder.itemImage.setImageResource(image[position])


    }


}