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
 * 프로필 화면
 * 리스트 대충 구현함
 * 상세 모양은 나중에 다듬음
 */

class Profile(val userName:String,
              val firstName:String,
              val lastName:String,
              val profilePhoto:String,
              val followersCount:Int,
              val followingsCount:Int)
class Feeds(val image: String,
            val title:String,
            val text:String)

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val profile:Profile = loadProfile()
        val list = loadFeeds()
        val view: View = inflater.inflate(R.layout.fragment_profile, null)
        val profileListView = view.findViewById<ListView>(R.id.profileFeedsListView)
        val profileAdapter = ProfileFeedsAdapter(activity!!, list)

        profileListView.adapter = profileAdapter

        view.findViewById<TextView>(R.id.profileImage).text = profile.profilePhoto
        view.findViewById<TextView>(R.id.profileUserName).text = profile.userName
        view.findViewById<TextView>(R.id.profileFirstName).text = profile.firstName
        view.findViewById<TextView>(R.id.profileLastName).text = profile.lastName
        view.findViewById<TextView>(R.id.profileFollowerCount).text =
            "Follower : " + profile.followersCount.toString()
        view.findViewById<TextView>(R.id.profileFollowingCount).text =
            "Following : " + profile.followingsCount.toString()

        return view
    }

    fun loadProfile(): Profile {
        //todo : 서버에서 프로필 불러와야함
        val profile = Profile(
            "userName",
            "YH",
            "Lee",
            "AAA",
            100,
            100
        )

        return profile
    }

    fun loadFeeds(): ArrayList<Feeds> {
        //todo : 서버에서 프로필 피드 불러와야함
        val feeds = arrayListOf<Feeds>(
            Feeds("Imgae1", "Title1", "Text1"),
            Feeds("Imgae2", "Title2", "Text2"),
            Feeds("Imgae3", "Title3", "Text3"),
            Feeds("Imgae4", "Title4", "Text4"),
            Feeds("Imgae5", "Title5", "Text5"),
            Feeds("Imgae6", "Title6", "Text6"),
            Feeds("Imgae7", "Title7", "Text7"),
            Feeds("Imgae8", "Title8", "Text8"),
            Feeds("Imgae9", "Title9", "Text9"),
            Feeds("Imgae10", "Title10", "Text10"),
            Feeds("Imgae11", "Title11", "Text11"),
            Feeds("Imgae12", "Title12", "Text12"),
            Feeds("Imgae13", "Title13", "Text13"),
            Feeds("Imgae14", "Title14", "Text14"),
            Feeds("Imgae15", "Title15", "Text15")
        )

        return feeds
    }

}

//테스트용 listadapter
class ProfileFeedsAdapter (val context: Context, val feeds: ArrayList<Feeds>) : BaseAdapter() {
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{
        
        //각 리스트의 형태 저장
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_profile_feeds, null)
        
        //각 리스트의 내용
        val image = view.findViewById<TextView>(R.id.contentsImage)
        val title = view.findViewById<TextView>(R.id.contentsTitle)
        val text = view.findViewById<TextView>(R.id.contentsText)
        val item = feeds[position]
        image.text = item.image
        title.text = item.title
        text.text = item.text

        return view
    }
    //오버라이드 함수
    //리스트 크기 반환함
    override fun getCount(): Int {
        return feeds.size
    }
    //오버라이드 함수
    //해당 인덱스의 아이템 반환함
    override fun getItem(position: Int): Any {
        return feeds[position]
    }
    //오버라이드 함수
    //해당 인덱스의 아이디 반환함
    //버튼 안 만들어서 현재 쓸 일 없음
    override fun getItemId(position: Int): Long {
        return 0
    }

}