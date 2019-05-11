package com.example.myapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.Exception
import java.util.*



class RegisterActivity : AppCompatActivity() {

    private lateinit var txtName: EditText
    private lateinit var txtAdress: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtReEnterPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        link_login.setOnClickListener{ super.onBackPressed()}
        initVariable()
    }

   private  fun initVariable(){
        txtName=findViewById(R.id.input_name)
        txtEmail=findViewById(R.id.input_email)
        txtAdress=findViewById(R.id.input_address)
        txtUser=findViewById(R.id.input_user)
        txtPassword=findViewById(R.id.input_password)
        txtReEnterPassword=findViewById(R.id.input_reEnterPassword)
        progressBar= findViewById(R.id.progressBar)
        database= FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()
        dbReference=database.reference.child("client")
        firestore= FirebaseFirestore.getInstance()
    }
    fun register(view:View){
        putValues()
        actionGoToLogin()
    }
    private fun putValues(){
        val name:String=txtName.text.toString()
        val email:String=txtEmail.text.toString()
        val adress:String=txtAdress.text.toString()
        val user:String=txtUser.text.toString()
        val password:String=txtPassword.text.toString()
        val reEnterPassword:String=txtReEnterPassword.text.toString()
        createAuthentication(email,password,name,adress,user)
        saveClientInfo(name,adress,user)

    }

    private fun createAuthentication(email:String, password:String,
                                     name:String,adress:String,user:String ){
        try {
            if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                progressBar.visibility=View.VISIBLE
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){
                            task ->
                        if (task.isComplete){
                            val firebaseUser:FirebaseUser?=auth.currentUser
                            this.sendConfirmationEmail(firebaseUser)


                        }else{
                            task.exception?.printStackTrace()
                        }
                    }

            }
        }
        catch (e: Exception) {
           e.printStackTrace()
        }

    }

    private fun saveClientInfo(name:String,adress:String,user:String){
        val userDocument = HashMap<String,String>()
        userDocument.put("Name", name)
        userDocument.put("Adress", adress)
        userDocument.put("User", user)
        firestore.collection("client")
            .add(userDocument as Map<String, Any>).addOnSuccessListener {
                Toast.makeText(this, "Se guardo correctamente", Toast.LENGTH_LONG).show()
            }.addOnFailureListener(){
                Toast.makeText(this, "No Se guardo correctamente", Toast.LENGTH_LONG).show()
            }
      /*  val userDB=dbReference.child(fireUser?.uid.toString())
        userDB.child("Name").setValue(name)
        userDB.child("Adress").setValue(adress)
        userDB.child("User").setValue(user)*/
    }

    private fun sendConfirmationEmail(user:FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                task ->
                    if (task.isComplete){
                        Toast.makeText(this, "Correo enviado", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "Se generó un error enviando el correo", Toast.LENGTH_LONG).show()
                    }
            }
    }

    private fun actionGoToLogin(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}
