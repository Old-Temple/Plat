package com.example.plat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import org.w3c.dom.Text

/**
 * 프로필 화면
 * 리스트 대충 구현함
 * 상세 모양은 나중에 다듬음
 */
class ProfileFragment : Fragment() {
    //임시로 만든 arraylist
    val myList = arrayListOf<ProfileContentsListItem>(
        ProfileContentsListItem("Imgae1", "Title1", "Text1"),
        ProfileContentsListItem("Imgae2", "Title2", "Text2"),
        ProfileContentsListItem("Imgae3", "Title3", "Text3"),
        ProfileContentsListItem("Imgae4", "Title4", "Text4"),
        ProfileContentsListItem("Imgae5", "Title5", "Text5"),
        ProfileContentsListItem("Imgae6", "Title6", "Text6"),
        ProfileContentsListItem("Imgae7", "Title7", "Text7"),
        ProfileContentsListItem("Imgae8", "Title8", "Text8"),
        ProfileContentsListItem("Imgae9", "Title9", "Text9"),
        ProfileContentsListItem("Imgae10", "Title10", "Text10"),
        ProfileContentsListItem("Imgae11", "Title11", "Text11"),
        ProfileContentsListItem("Imgae12", "Title12", "Text12"),
        ProfileContentsListItem("Imgae13", "Title13", "Text13"),
        ProfileContentsListItem("Imgae14", "Title14", "Text14"),
        ProfileContentsListItem("Imgae15", "Title15", "Text15")
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

//이미지의 경우 임시로 String타입 줬음
class ProfileContentsListItem(val image: String, val title: String, val text: String)

//테스트용 listadapter
class ProfileListAdapter (val context: Context, val profileContentsListItem: ArrayList<ProfileContentsListItem>) : BaseAdapter() {
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
        
        //각 리스트의 형태 저장
        val view: View = LayoutInflater.from(context).inflate(R.layout.profile_list_item, null)
        
        //각 리스트의 내용
        val image = view.findViewById<TextView>(R.id.contentsImage)
        val title = view.findViewById<TextView>(R.id.contentsTitle)
        val text = view.findViewById<TextView>(R.id.contentsText)
        val item = profileContentsListItem[position]
        image.text = item.image
        title.text = item.title
        text.text = item.text

        return view
    }
    //오버라이드 함수
    //리스트 크기 반환함
    override fun getCount(): Int {
        return profileContentsListItem.size
    }
    //오버라이드 함수
    //해당 인덱스의 아이템 반환함
    override fun getItem(position: Int): Any {
        return profileContentsListItem[position]
    }
    //오버라이드 함수
    //해당 인덱스의 아이디 반환함
    //버튼 안 만들어서 현재 쓸 일 없음
    override fun getItemId(position: Int): Long {
        return 0
    }

}