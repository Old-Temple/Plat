package com.example.plat

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 프로필 화면
 * 리스트 대충 구현함
 * 상세 모양은 나중에 다듬음
 */

class Profile(var userName:String,
              var firstName:String,
              var lastName:String,
              var profilePhoto:String,
              var followersCount:Int?,
              var followingsCount:Int?)

class Feeds(val image: String,
            val title:String,
            val text:String)

class ProfileFragment(val mainActivity: MainActivity) : Fragment() {

    var profile:Profile? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val list = loadFeeds()
        val view: View = inflater.inflate(R.layout.fragment_profile, null)
        val profileListView = view.findViewById<ListView>(R.id.profileFeedsListView)
        val profileAdapter = ProfileFeedsAdapter(activity!!, list)
        loadProfile(view)

        profileListView.adapter = profileAdapter



        return view
    }

    fun loadProfile(view : View){
        val apolloClient = apolloClient(mainActivity.applicationContext)
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val response : Response<SeeProfileQuery.Data> =
                apolloClient.query(SeeProfileQuery(PlatPrefs.prefs.getValue("userName", ""))).await()

            val userName = response.data?.seeProfile?.userName.toString()
            val firstName = response.data?.seeProfile?.firstName.toString()
            val lastName = response.data?.seeProfile?.lastName.toString()
            val profilePhoto = response.data?.seeProfile?.profilePhoto.toString()
            val followersCount = response.data?.seeProfile?.followersCount
            val followingsCount = response.data?.seeProfile?.followingsCount

            profile = Profile(userName, firstName, lastName, profilePhoto, followersCount, followingsCount)

            view.findViewById<TextView>(R.id.profileImage).text = profile?.profilePhoto
            view.findViewById<TextView>(R.id.profileUserName).text = profile?.userName
            view.findViewById<TextView>(R.id.profileFirstName).text = profile?.firstName
            view.findViewById<TextView>(R.id.profileLastName).text = profile?.lastName
            view.findViewById<TextView>(R.id.profileFollowerCount).text =
                "Follower : " + profile?.followersCount.toString()
            view.findViewById<TextView>(R.id.profileFollowingCount).text =
                "Following : " + profile?.followingsCount.toString()
            return@launch
        }
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