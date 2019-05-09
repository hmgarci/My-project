package com.example.myapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var auth=FirebaseAuth.getInstance()

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
                            goToHome()
                        }else{
                            Toast.makeText(this@MainActivity, "¡ups! Los datos son incorrectos, revisalos e ingresalos de nuevo por favor", Toast.LENGTH_SHORT).show()
                        }
                }


        }else{
            Toast.makeText(this@MainActivity, "¡Hey! Completa los datos por favor", Toast.LENGTH_SHORT).show()
        }
    }
    private fun goToHome(){
        var intent= Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}

