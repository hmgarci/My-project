package com.example.myapp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_login.setOnClickListener{ this.validateLogin()}
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
                Toast.makeText(this@MainActivity, "ups! Tu información no es correcta", Toast.LENGTH_SHORT).show()
        }
    }
}
