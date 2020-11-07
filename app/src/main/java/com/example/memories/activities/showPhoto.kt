package com.example.memories.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.memories.R
import com.example.memories.adapters.ViewAdapter_for_Images_in_show_image_activity
import kotlinx.android.synthetic.main.activity_show_photo.*
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator


class showPhoto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_photo)
        var list_of_images_pathes: ArrayList<String>
        val intent2: Intent = getIntent()
        var imagedpathesintes = intent2.getStringArrayListExtra("imagespathes")
        list_of_images_pathes = imagedpathesintes as ArrayList<String>
        Log.e("lista", list_of_images_pathes.toString())
        val adapter =
            ViewAdapter_for_Images_in_show_image_activity(
                list_of_images_pathes
            )

        showPhotos_recycler.adapter = adapter
        showPhotos_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(showPhotos_recycler)

        val recyclerIndicator: ScrollingPagerIndicator = findViewById(R.id.indicator)
        recyclerIndicator.attachToRecyclerView(showPhotos_recycler)
        /*val indecator = findViewById<CircleIndicator3>(R.id.indicator_3)
        indeca*/
    }
}