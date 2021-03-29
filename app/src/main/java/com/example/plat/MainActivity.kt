package com.example.plat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.action_main -> {
                    replaceFragment(MainFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_profile -> {
                    replaceFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_store -> {
                    replaceFragment(StoreFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_search -> {
                    replaceFragment(SearchFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_alarm -> {
                    replaceFragment(AlarmFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }

        //초기 설정
        bottom_navigation.selectedItemId = R.id.action_main
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransactionListener: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransactionListener.replace(R.id.frameLayout, fragment)
        fragmentTransactionListener.commit()
    }
}
