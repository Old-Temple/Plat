package com.example.plat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.*

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

        val apolloClient = apolloClient(applicationContext)

        var personalEmail = String()

        pw.visibility = View.INVISIBLE
        btn.visibility = View.INVISIBLE

        btnSubmit.setOnClickListener { view ->
            personalEmail = email.text.toString()



            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val response: Response<RequestSecretMutation.Data> =
                    apolloClient
                        .mutate(RequestSecretMutation(email = personalEmail))
                        .await()

                if(response.data?.requestSecret?.ok != true){
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
            val personalPW = pw.text.toString()

            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val response: Response<ConfirmSecretMutation.Data> =
                    apolloClient
                        .mutate(ConfirmSecretMutation(
                            email = personalEmail,
                            secret = personalPW
                        )).await()


                if(response.data?.confirmSecret?.ok == true){
                    PlatPrefs.prefs.setValue("token", response.data?.confirmSecret?.token.toString())

                    val me : Response<MeQuery.Data> =
                        apolloClient.query(MeQuery()).await()
                    PlatPrefs.prefs.setValue("id", me.data?.me?.id.toString())
                    PlatPrefs.prefs.setValue("userName", me.data?.me?.userName.toString())

                    finishAcitivy()
                }
                else{
                    error.text = response.data?.confirmSecret?.error
                }
            }
        }
        createAC.setOnClickListener{ view ->
            val createAccount = DialogMakeAccount(this)
            createAccount.show(supportFragmentManager, createAccount.tag)
        }
    }
    fun finishAcitivy(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}