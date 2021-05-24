package com.example.plat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 메인화면
 * 버튼 이벤트는 추후 작성함
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //네비게이션 버튼 클릭 리스너
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.actionMain -> {
                    replaceFragment(MainFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionProfile -> {
                    replaceFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionStore -> {
                    replaceFragment(StoreFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionSearch -> {
                    replaceFragment(SearchFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.actionAlarm -> {
                    replaceFragment(AlarmFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
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
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransactionListener: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransactionListener.replace(R.id.frameLayout, fragment)
        fragmentTransactionListener.commit()
    }

}
