package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 * 알람창 화면
 */
class AlarmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

<<<<<<< Updated upstream
=======
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



>>>>>>> Stashed changes
}