package com.example.memories.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.memories.R
import com.example.memories.activities.location
import com.example.memories.database.Note
import com.example.memories.database.NotesDatabase
import kotlinx.android.synthetic.main.fragment_image.*
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
    lateinit var notedata: Note
    var bundle = arguments
    val id = bundle?.getInt("id")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val note = NotesDatabase.getInstance(activity!!.baseContext).notesDao().searchbyid(id!!)


        view_pager5.adapter = ViewAdapter(note)
        // view_pager5.orientation = view_pager5.ORIENTATION_HORIZONTAL


        location_ic.setOnClickListener {
            val intet = Intent(activity, location::class.java)
            intet.putExtra("lat", note.lat)
            intet.putExtra("longe", note.longe)
            startActivity(intet)

        }
        val indecator = view.findViewById<CircleIndicator3>(R.id.indicator_3)
        indecator.setViewPager(view_pager5)


    }





}