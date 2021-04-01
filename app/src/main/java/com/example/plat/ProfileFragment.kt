package com.example.plat

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

/**
 * 프로필 화면
 * 리스트 대충 구현함
 * 상세 모양은 나중에 다듬음
 */
class ProfileFragment : Fragment() {
    //임시로 만든 arraylist
    val myList = arrayListOf<ProfileListItem>(
        ProfileListItem("리스트1"), ProfileListItem("리스트2"), ProfileListItem("리스트3"),
        ProfileListItem("리스트4"), ProfileListItem("리스트5"), ProfileListItem("리스트6"),
        ProfileListItem("리스트7"), ProfileListItem("리스트8"), ProfileListItem("리스트9"),
        ProfileListItem("리스트10"), ProfileListItem("리스트11"), ProfileListItem("리스트12"),
        ProfileListItem("리스트13"), ProfileListItem("리스트14"), ProfileListItem("리스트15")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile, null)
        val profileListView = view.findViewById<ListView>(R.id.profile_listveiw)
        val profileAdapter = ProfileListAdapter(activity!!, myList)

        profileListView.adapter = profileAdapter

        return view
    }

}

//임시 클래스(이름 바꿀 예정)
class ProfileListItem(val text: String)

//테스트용 listadapter
class ProfileListAdapter (val context: Context, val profileListItem: ArrayList<ProfileListItem>) : BaseAdapter() {
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
        
        //각 리스트의 형태 저장
        val view: View = LayoutInflater.from(context).inflate(R.layout.profile_list_item, null)
        
        //각 리스트의 내용
        val textItem = view.findViewById<TextView>(R.id.profileContents)
        val item = profileListItem[position]
        textItem.text = item.text

        return view
    }
    //오버라이드 함수
    //리스트 크기 반환함
    override fun getCount(): Int {
        return profileListItem.size
    }
    //오버라이드 함수
    //해당 인덱스의 아이템 반환함
    override fun getItem(position: Int): Any {
        return profileListItem[position]
    }
    //오버라이드 함수
    //해당 인덱스의 아이디 반환함
    //버튼 안 만들어서 현재 쓸 일 없음
    override fun getItemId(position: Int): Long {
        return 0
    }

}