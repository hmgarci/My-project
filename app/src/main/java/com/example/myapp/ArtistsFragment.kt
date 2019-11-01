package com.example.myapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.content.ContextCompat.getSystemService
import android.app.DownloadManager
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.os.Build
import android.content.IntentFilter




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ArtistsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ArtistsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ArtistsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_artists, container, false)

    companion object {
        fun newInstance(): ArtistsFragment = ArtistsFragment()
    }


}
