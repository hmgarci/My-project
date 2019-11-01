package com.example.myapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_albums.view.*
import kotlinx.android.synthetic.main.fragment_songs.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AlbumsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AlbumsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AlbumsFragment : Fragment() {

    lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        mView=inflater.inflate(R.layout.fragment_albums, container, false)
        mView.btn_crear_restaurante.setOnClickListener{
            this.createRestaurante()
        }
        return mView
    }
    companion object {
        fun newInstance(): AlbumsFragment = AlbumsFragment()
    }

    fun createRestaurante(){
        val intent = Intent(activity, RestaurantActivity::class.java)
        startActivity(intent)

    }
}
