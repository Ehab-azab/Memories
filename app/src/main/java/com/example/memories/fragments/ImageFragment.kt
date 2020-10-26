package com.example.memories.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.viewpager2.widget.ViewPager2
import com.example.memories.R
import kotlinx.android.synthetic.main.activity_mains.*
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.android.synthetic.main.fragment_image.view.*
import me.relex.circleindicator.CircleIndicator3

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [imageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class imageFragment : Fragment() {






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }
    lateinit var text_title : String
    lateinit var text_Detalis :String
    lateinit var image_list : MutableList<Int>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        view_pager5.adapter = ViewAdapter(text_title,text_Detalis,image_list)
       // view_pager5.orientation = view_pager5.ORIENTATION_HORIZONTAL




        val indecator = view.findViewById<CircleIndicator3>(R.id.indicator_3)
        indecator.setViewPager(view_pager5)


    }





}