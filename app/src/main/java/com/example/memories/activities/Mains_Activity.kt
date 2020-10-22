package com.example.memories.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.memories.R
import com.example.memories.adapters.mains_adapter
import kotlinx.android.synthetic.main.activity_mains.*
import me.relex.circleindicator.CircleIndicator3

class Mains_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //this data for intro
        var description = listOf(
            "" + getString(R.string.first_goal), "" + getString(
                R.string.secound_goal
            ), "" + getString(R.string.third_goal)
        )
        var images = listOf(
            R.drawable.memories1,
            R.drawable.memories2,
            R.drawable.memories3
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mains)

        //this for adapter
        var adapter = mains_adapter(images, description)
        view_pager2.adapter = adapter
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        adapter.OnPositionChange = object : mains_adapter.ONItemChangedListener {
            override fun OndataChange(position: Int, datasize: Int) {
                if (position == datasize - 1) {
                    Log.e("position", "" + position)
                    Log.e("positiondatasize", "" + datasize)
                    nextbtn.visibility = (View.VISIBLE)
                    nextbtn.text = "Start"

                    nextbtn.setOnClickListener {
                        startActivity(Intent(this@Mains_Activity, Home::class.java))

                    }
                } else {
                    Log.e("elseposition", "" + position)
                    nextbtn.visibility = (View.INVISIBLE)
                }
            }

        }

        //thi for indicator
        val indecator: CircleIndicator3 = findViewById(R.id.indecator)
        indecator.setViewPager(view_pager2)

    }


}



