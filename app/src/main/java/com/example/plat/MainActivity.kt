package com.example.plat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.plat_funiture.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

/**
 * 메인화면
 * 버튼 이벤트는 추후 작성함
 */
object flag {
    var mainFlag = 0
}

//todo 네비게이션 좀 고쳐야 함
class MainActivity : AppCompatActivity() {

    val userName = PlatPrefs.prefs.getValue("userName","")
    var clickedName : String = ""
    var apolloClient : ApolloClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apolloClient = apolloClient(applicationContext)
        val scope = CoroutineScope(Dispatchers.IO)
            //네비게이션 버튼 클릭 리스너
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.actionMain -> {
                    replaceFragment(MainFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionProfile -> {
                    replaceFragment(ProfileFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionStore -> {
                    replaceFragment(StoreFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionSearch -> {
                    replaceFragment(SearchFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionAlarm -> {
                    replaceFragment(AlarmFragment(this))
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }

        scope.launch {
            while (true){
                if (flag.mainFlag != 0){
                    flag.mainFlag = 0
                    replaceFragment(MainFragment(this@MainActivity))
                }
            }
        }

        //초기 설정
        bottomNavigation.selectedItemId = R.id.actionMain

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)
            val warehouse = findViewById<Button>(R.id.btnGoWarehouse)
            warehouse.setOnClickListener { view ->
                scope.launch {
                    val response : Response<SeeUserItemsQuery.Data> =
                        apolloClient?.query(SeeUserItemsQuery(userName))!!.await()

                    val list = response.data?.seeProfile?.items
                    val furnitures = mutableListOf<SeeItemQuery.SeeItem>()
                    val themas = mutableListOf<SeeItemQuery.SeeItem>()
                    val heads = mutableListOf<SeeItemQuery.SeeItem>()
                    val bodys = mutableListOf<SeeItemQuery.SeeItem>()
                    val legs = mutableListOf<SeeItemQuery.SeeItem>()

                    if (list != null) {
                        for (i in list){

                            val temp: Response<SeeItemQuery.Data> =
                                apolloClient?.query(SeeItemQuery(i.id))!!.await()
                            val tempValue = temp.data?.seeItem?.itemInfo?.typeId

                            if (tempValue == "furniture") {
                                furnitures.add(temp.data?.seeItem!!)
                            } else if (tempValue == "theme") {
                                themas.add(temp.data?.seeItem!!)
                            } else if (tempValue == "head") {
                                heads.add(temp.data?.seeItem!!)
                            } else if (tempValue == "body") {
                                bodys.add(temp.data?.seeItem!!)
                            } else if (tempValue == "leg") {
                                legs.add(temp.data?.seeItem!!)
                            }

                        }
                    }
                    val wareHouse = DialogWarehouse(furnitures, themas, heads, bodys, legs)
                    wareHouse.show(supportFragmentManager, wareHouse.tag)
                }
            }
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransactionListener: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransactionListener.replace(R.id.frameLayout, fragment)
        fragmentTransactionListener.commit()
    }
}

