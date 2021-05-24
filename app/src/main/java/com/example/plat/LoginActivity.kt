package com.example.plat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn:Button = findViewById(R.id.btnLogin)
        val createAC:TextView = findViewById(R.id.goCreateAccount)
        val id:EditText = findViewById(R.id.editTextID)
        val pw:EditText = findViewById(R.id.editTextPW)

        btn.setOnClickListener { view ->
            //todo : 아이디랑 패스워드 보내고, True값 오면 프리퍼런스에 저장하고 메인으로
            //일단 저장이랑 액티비티 바꾸는 것만 구현
            val personalID = id.text.toString()
            val personalPW = pw.text.toString()

            PlatPrefs.prefs.setValue("id", personalID)  //앱에 저장

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        createAC.setOnClickListener{ view ->
            val createAccount = DialogMakeAccount()
            createAccount.show(supportFragmentManager, createAccount.tag)
        }
    }
}