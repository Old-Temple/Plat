package com.example.plat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.schedule

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
            var personalID = id.text.toString()
            val personalPW = pw.text.toString()


            val apolloCient = PlatApollo().apolloCient

            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val response: Response<SeeProfileQuery.Data> =
                    apolloCient.query(SeeProfileQuery("zoody")).await()

                async {
                    val userID = response.data?.seeProfile?.id
                    personalID = userID.toString()
                    PlatPrefs.prefs.setValue("idKey", userID.toString())
                }.await()
                finishAcitivy()
            }
        }

        createAC.setOnClickListener{ view ->
            val createAccount = DialogMakeAccount()
            createAccount.show(supportFragmentManager, createAccount.tag)
        }
    }
    fun finishAcitivy(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}