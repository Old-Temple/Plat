package com.example.plat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val error = findViewById<TextView>(R.id.loginErrorMessage)
        val createAC:TextView = findViewById(R.id.goCreateAccount)
        val email:EditText = findViewById(R.id.editTextEmail)
        val pw:EditText = findViewById(R.id.editTextPW)

        val apolloCient = apolloClient(context = applicationContext)

        var personalEmail = String()

        pw.visibility = View.INVISIBLE
        btn.visibility = View.INVISIBLE

        btnSubmit.setOnClickListener { view ->
            //todo : 아이디랑 패스워드 보내고, True값 오면 프리퍼런스에 저장하고 메인으로
            //일단 저장이랑 액티비티 바꾸는 것만 구현
            personalEmail = email.text.toString()



            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val response: Response<RequestSecretMutation.Data> =
                    apolloCient
                        .mutate(RequestSecretMutation(email = personalEmail))
                        .await()

                if(response.data?.requestSecret?.ok == true){
                    Log.d("AAA1", personalEmail)
                }
                else{
                    error.text = "이메일이 올바르지 않습니다"
                }
            }
            if(error.text.toString() == "") {
                pw.visibility = View.VISIBLE
                btn.visibility = View.VISIBLE
                email.visibility = View.INVISIBLE
                btnSubmit.visibility = View.INVISIBLE
            }
        }
        btn.setOnClickListener { view ->
            var personalPW = pw.text.toString()

            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val response: Response<ConfirmSecretMutation.Data> =
                    apolloCient
                        .mutate(ConfirmSecretMutation(
                            email = personalEmail,
                            secret = personalPW
                        )).await()

                if(response.data?.confirmSecret?.ok == true){
                    Log.d("AAA1", response.data?.confirmSecret?.token.toString())
                    PlatPrefs.prefs.setValue("token", response.data?.confirmSecret?.token.toString())
                    finishAcitivy()
                }
                else{
                    error.text = response.data?.confirmSecret?.error
                }
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