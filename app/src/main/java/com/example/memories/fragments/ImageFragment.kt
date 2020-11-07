package com.example.memories.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.memories.R
import com.example.memories.activities.location
import com.example.memories.activities.showPhoto
import com.example.memories.database.Note
import com.example.memories.database.NotesDatabase
import com.example.memories.images.images
import kotlinx.android.synthetic.main.fragment_image.*

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



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundle = arguments
        val id = bundle?.getInt("id")
        val imagesclass = images()
        var listofimages: List<String>? = null
        val note = NotesDatabase.getInstance(activity!!.baseContext).notesDao().searchbyid(id!!)
        listofimages = note.pathImage as List<String>
        image_fragment.setImageBitmap(imagesclass.PathToBitmab(note.pathImage?.get(0).toString()))
        image_fragment.setOnClickListener {
            val intent = Intent(context, showPhoto::class.java)
            //intent.putExtra("imagespathes",note.pathImage as ArrayList<String>)
            intent.putStringArrayListExtra("imagespathes", ArrayList(note.pathImage))
            Log.e("pa", ArrayList(note.pathImage).toString())
            startActivity(intent)
        }

        var arayofstrings = null



        Log.e("photos", listofimages.toString())
        Log.e("photosarray", "" + arayofstrings)
        text_title.setText(note.title)
        text_words.setText(note.description)
        item_pager_date.setText(note.date)

        // view_pager5.orientation = view_pager5.ORIENTATION_HORIZONTAL


        location_ic.setOnClickListener {
            val intet = Intent(activity, location::class.java)
            intet.putExtra("lat", note.lat)
            intet.putExtra("longe", note.longe)
            startActivity(intet)

        }


    }


}