package com.example.plat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 * 알람창 화면
 */
class AlarmFragment : Fragment() {
    //임시로 만든 arraylist
    private val myList = arrayListOf<AlarmContentsListItem>(
        AlarmContentsListItem("Image1", "User1", "Like"),
        AlarmContentsListItem("Image2", "User2", "Share"),
        AlarmContentsListItem("Image3", "User3", "Like"),
        AlarmContentsListItem("Image4", "User4", "Like"),
        AlarmContentsListItem("Image5", "User5", "Share"),
        AlarmContentsListItem("Image6", "User6", "Like"),
        AlarmContentsListItem("Image7", "User7", "Share"),
        AlarmContentsListItem("Image8", "User8", "Share"),
        AlarmContentsListItem("Image9", "User9", "Like"),
        AlarmContentsListItem("Image0", "User0", "Like"),
        AlarmContentsListItem("Image1", "User1", "Share"),
        AlarmContentsListItem("Image2", "User2", "Share"),
        AlarmContentsListItem("Image3", "User3", "Like"),
        AlarmContentsListItem("Image4", "User4", "Like"),
        AlarmContentsListItem("Image5", "User5", "Share"),
        AlarmContentsListItem("Image6", "User6", "Like")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_alarm, null)
        val alarmListView = view.findViewById<ListView>(R.id.alarmListView)
        val alarmListAdapter = AlarmListAdapter(activity!!, myList)

        alarmListView.adapter = alarmListAdapter

        return view
    }

}

//이미지의 경우 임시로 String타입 줬음
class AlarmContentsListItem(val image: String, val user: String, val action: String)

//listAdapter
class AlarmListAdapter(val context: Context, val alarmContentsListItem: ArrayList<AlarmContentsListItem>) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //각 리스트 형태 저장
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_alarm, null)

        //각 리스트 내용
        val image = view.findViewById<TextView>(R.id.alarmIcon)
        val text = view.findViewById<TextView>(R.id.alarmText)
        val item = alarmContentsListItem[position]

        image.text = item.image

        //액션 값에 따라 출력 다름
        if (item.action == "Share") {text.text = item.user+"님이 공유하였습니다"}
        else if (item.action == "Like") {text.text = item.user + "님이 좋아요를 눌렀습니다"}

        return view
    }

    override fun getCount(): Int {
        return alarmContentsListItem.size
    }

    override fun getItem(position: Int): Any {
        return alarmContentsListItem[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}