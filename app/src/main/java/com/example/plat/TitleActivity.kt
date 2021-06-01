package com.example.plat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule


/**
 * 타이틀화면
 * 일단 1초 지나면 메인으로 들어가도록 만들어놓음
 * 추후에 서버 구축 다 되면
 * 로딩 끝나고 난 다음에 메인으로 들어가도록 바꿀 예정임
 */

class TitleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        Timer().schedule(1000){
            goMain()
        }
    }

    //1초 후에 타이틀 화면 변환
    private fun goMain(){
        if(PlatPrefs.prefs.getValue("token","abc") == "abc"){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}