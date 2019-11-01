package com.example.myapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_songs.*
import kotlinx.android.synthetic.main.fragment_songs.view.*

class SongsFragment : Fragment() {
    lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        mView=inflater.inflate(R.layout.fragment_songs, container, false)
        mView.btn_crear_cliente.setOnClickListener{
            this.createCliente()
        }
        return mView
    }
    companion object {
        fun newInstance(): SongsFragment = SongsFragment()
    }

    fun createCliente(){
        val intent = Intent(activity, RegisterActivity::class.java)
        startActivity(intent)

    }
}
