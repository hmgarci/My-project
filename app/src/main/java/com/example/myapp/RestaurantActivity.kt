package com.example.myapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

class RestaurantActivity : AppCompatActivity() {

    private lateinit var txtRestaurantName: EditText
    private lateinit var txtRestaurantAdress: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtRestaurantNit: EditText
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        initVariable()
    }

    private  fun initVariable(){
        txtRestaurantName=findViewById(R.id.input_restaurant_name)
        txtRestaurantAdress=findViewById(R.id.input_restaurant_address)
        txtEmail=findViewById(R.id.input_restautant_email)
        txtRestaurantNit=findViewById(R.id.input_restaurant_nit)
        firestore= FirebaseFirestore.getInstance()

    }
     fun registerRestaurant(view: View){
        putValues()
     }
    private fun putValues(){
        var name=txtRestaurantName.text.toString()
        var Adress=txtRestaurantAdress.text.toString()
        var Email=txtEmail.text.toString()
        var nit=txtRestaurantNit.text.toString()
        saveClientInfo(name,Adress,Email,nit)
    }
    private fun saveClientInfo(name:String,adress:String,email:String,nit:String){
        val userDocument = HashMap<String,String>()
        userDocument.put("Nombre_restaurante", name)
        userDocument.put("Direccion", adress)
        userDocument.put("ID_restaurante", nit)
        userDocument.put("ID_cliente",email)
        firestore.collection("restaurant")
            .add(userDocument as Map<String, Any>).addOnSuccessListener {
                Toast.makeText(this, "Se guardo correctamente", Toast.LENGTH_LONG).show()
            }.addOnFailureListener(){
                Toast.makeText(this, "No Se guardo correctamente", Toast.LENGTH_LONG).show()
            }
    }

}
