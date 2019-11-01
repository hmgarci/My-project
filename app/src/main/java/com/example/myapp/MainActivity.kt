package com.example.myapp

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.nfc.Tag
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var auth=FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //btn_login.setOnClickListener{ this.validateLogin()}
        link_signup.setOnClickListener{processLinkSingup()}

    }

    private fun processLinkSingup() {
        var intent= Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun validateLogin(){
        var user= input_email.text.toString()
        var pass= input_password.text.toString()
        var intent= Intent(this,HomeActivity::class.java)
        if (user.equals("hmgarci") && pass.equals("hola123")){
            intent.putExtra("name",user)
            startActivity(intent)
        }else{
            Toast.makeText(this@MainActivity, "ups! your info is not correct", Toast.LENGTH_SHORT).show()
        }
    }

    fun login(wiew:View){
        var user= input_email.text.toString()
        var pass= input_password.text.toString()

        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)){
            auth.signInWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this){
                    task ->
                        if (task.isSuccessful){
                            this.getData(user)
                        }else{
                            Toast.makeText(this@MainActivity, "¡ups! Los datos son incorrectos, revisalos e ingresalos de nuevo por favor", Toast.LENGTH_SHORT).show()
                        }
                }


        }else{
            Toast.makeText(this@MainActivity, "¡Hey! Completa los datos por favor", Toast.LENGTH_SHORT).show()
        }
    }
    private fun goToHome(){
        //var intent= Intent(this,UploadImageActivity::class.java)
        var intent= Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

    private fun getData(correo:String) {
        var rol:Any?=null
        db.collection("client")
            .whereEqualTo("ID_cliente", correo)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    rol = document.data.getValue("rol")
                }
                this.validateRol(rol)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this@MainActivity, "Error recuperando los datos", Toast.LENGTH_SHORT).show()
            }
    }
    private fun validateRol(rol:Any?){
        if (rol!!.equals("Administrador")){
            var intent= Intent(this,MenuAdminActivity::class.java)
            startActivity(intent)
        }else if(rol!!.equals("Cliente")) {
            var intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}

