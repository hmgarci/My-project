package com.example.myapp

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.IOException

class UploadImageActivity : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var imageview: ImageView
    private val GALLERY = 1
    private val CAMERA = 2
    private val storage = FirebaseStorage.getInstance()
    private var storageRef = storage.reference
    private lateinit var imagesRef: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_image)

        btn = findViewById<View>(R.id.btn) as Button
        imageview = findViewById<View>(R.id.iv) as ImageView
        btn!!.setOnClickListener { showPictureDialog() }

    }
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        /* if (resultCode == this.RESULT_CANCELED)
         {
         return
         }*/
        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data?.data
                try
                {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val filePath=storageRef?.child("images").child(contentURI.lastPathSegment)
                    imageview!!.setImageBitmap(bitmap)
                    val uploadTask =filePath?.putFile(contentURI)
                    //val uploadTask = imagesRef?.putBytes(converBitmap(bitmap))
                    uploadTask?.addOnSuccessListener{
                        Toast.makeText(this@UploadImageActivity, "Se subio correctamente!", Toast.LENGTH_SHORT).show()
                    }?.addOnFailureListener(){
                      uploadTask.exception?.printStackTrace()
                        Toast.makeText(this@UploadImageActivity, "No se subio correctamente!", Toast.LENGTH_SHORT).show()
                    }

                }
                catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this@UploadImageActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }

            }

        }
        else if (requestCode == CAMERA)
        {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            imageview!!.setImageBitmap(thumbnail)
            val stream=FileInputStream(thumbnail.toString())
            val putStream = imagesRef?.putBytes(converBitmap(thumbnail))
        }
    }

    private fun converBitmap(bitmap:Bitmap): ByteArray {
        val byteArray=ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray)
        val toByteArray = byteArray.toByteArray()
        return toByteArray
    }
}
