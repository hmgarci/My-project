package com.example.myapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.graphics.Palette
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var imageGalleryAdapter: ImageGalleryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //this.showName()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView = findViewById(R.id.rv_images)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        imageGalleryAdapter = ImageGalleryAdapter(this, SetPhoto.getSunsetPhotos())
    }
    override fun onStart() {
        super.onStart()
        recyclerView.adapter = imageGalleryAdapter
    }
    private inner class ImageGalleryAdapter(val context: Context, val sunsetPhotos: Array<SetPhoto>)
        : RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageGalleryAdapter.MyViewHolder {
            val context = parent.context
            val inflater = LayoutInflater.from(context)
            val photoView = inflater.inflate(R.layout.activity_image_detail, parent, false)
            return MyViewHolder(photoView)
        }
        override fun onBindViewHolder(holder: ImageGalleryAdapter.MyViewHolder, position: Int) {
            val sunsetPhoto = sunsetPhotos[position]
            val imageView = holder.photoImageView
            Picasso.get()
                .load(sunsetPhoto.url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .into(imageView)

        }

        override fun getItemCount(): Int {
            return sunsetPhotos.size
        }
        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

            var photoImageView: ImageView = itemView.findViewById(R.id.iv_photo)

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(view: View) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val sunsetPhoto = sunsetPhotos[position]
                    val intent = Intent(context, HomeActivity::class.java).apply {
                        putExtra(ImageDetail.EXTRA_SUNSET_PHOTO, sunsetPhoto)
                    }
                    startActivity(intent)
                }
            }
        }
    }
}
