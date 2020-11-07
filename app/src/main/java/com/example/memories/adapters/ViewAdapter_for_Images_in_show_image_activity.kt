package com.example.memories.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.memories.R
import kotlinx.android.synthetic.main.item_pager.view.*
import java.io.File

class ViewAdapter_for_Images_in_show_image_activity(var imagespathes: ArrayList<String>) :
    RecyclerView.Adapter<ViewAdapter_for_Images_in_show_image_activity.pagerHolder>() {

    inner class pagerHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val itemImage: ImageView = itemview.image_fragment

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pager, parent, false)
        return pagerHolder(view)
    }

    override fun getItemCount(): Int {
        return imagespathes.size
    }

    override fun onBindViewHolder(holder: pagerHolder, position: Int) {
        if (imagespathes != null) {
            holder.itemImage.setImageBitmap(PathToBitmab(imagespathes.get(position)))
        }

    }

    fun PathToBitmab(imagepath: String): Bitmap {
        var imagefile = File(imagepath)
        var imagebitmab: Bitmap = BitmapFactory.decodeFile(imagefile.absolutePath)
        return@PathToBitmab imagebitmab
    }

}