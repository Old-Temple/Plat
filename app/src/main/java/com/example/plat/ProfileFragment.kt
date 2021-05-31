package com.example.plat

import android.content.Context
import android.content.LocusId
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentTransaction
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.bumptech.glide.Glide
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

class ProfileFragment(val mainActivity: MainActivity) : Fragment() {

    val userName = PlatPrefs.prefs.getValue("userName","")
    private val apolloClient = apolloClient(mainActivity.applicationContext)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_profile, null)

        loadProfile()
        loadFeeds()

        return view
    }

    fun loadProfile(){
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val response : Response<SeeProfileQuery.Data> =
                apolloClient.query(SeeProfileQuery(userName)).await()

            val userName = response.data?.seeProfile?.userName.toString()
            val firstName = response.data?.seeProfile?.firstName.toString()
            val lastName = response.data?.seeProfile?.lastName.toString()
            val profilePhoto = response.data?.seeProfile?.profilePhoto.toString()
            val followersCount = response.data?.seeProfile?.followersCount
            val followingsCount = response.data?.seeProfile?.followingsCount

            val profile = Profile(userName, firstName, lastName, profilePhoto, followersCount, followingsCount)

            //fragment 호출
            val fragmentTransactionListener: FragmentTransaction = childFragmentManager.beginTransaction()
            fragmentTransactionListener.replace(R.id.profileChildMeLayout, ProfileChildMe(mainActivity, profile))
            fragmentTransactionListener.commit()

            return@launch
        }
    }

    class ProfileChildMe(val mainActivity: MainActivity, val profile: Profile?) : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view: View = inflater.inflate(R.layout.fragment_profile_child_me, null)

            Glide.with(view).load(profile?.profilePhoto).into(view.findViewById<ImageView>(R.id.profileImage))
            view.findViewById<TextView>(R.id.profileUserName).text = profile?.userName
            view.findViewById<TextView>(R.id.profileFirstName).text = profile?.firstName
            view.findViewById<TextView>(R.id.profileLastName).text = profile?.lastName
            view.findViewById<TextView>(R.id.profileFollowerCount).text =
                "Follower : " + profile?.followersCount.toString()
            view.findViewById<TextView>(R.id.profileFollowingCount).text =
                "Following : " + profile?.followingsCount.toString()

            view.findViewById<LinearLayout>(R.id.profileChildMeLayout).setOnClickListener { view ->
                val dialogEditProfile = DialogEditProfile(mainActivity, profile?.userName, profile?.firstName, profile?.lastName)
                dialogEditProfile.show(childFragmentManager.beginTransaction(), dialogEditProfile.tag)
            }

            return view
        }
    }

    fun loadFeeds(){
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val response : Response<SeeProfileQuery.Data> =
                apolloClient.query(SeeProfileQuery(userName)).await()

            val feeds: List<SeeProfileQuery.Feed>? = response.data?.seeProfile?.feeds

            //fragment 호출
            val fragmentTransactionListener: FragmentTransaction = childFragmentManager.beginTransaction()
            fragmentTransactionListener.replace(R.id.profileChileFeedsLayout, ProfileChildFeeds(feeds))
            fragmentTransactionListener.commit()

            return@launch
        }
    }

    class ProfileChildFeeds(val feeds: List<SeeProfileQuery.Feed>?) : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view: View = inflater.inflate(R.layout.fragment_profile_child_feeds, null)
            val profileListView = view.findViewById<ListView>(R.id.profileFeedsListView)
            val profileAdapter = ProfileFeedsAdapter(activity!!, feeds)

            profileListView.adapter = profileAdapter

            return view
        }
    }

}

//테스트용 listadapter
class ProfileFeedsAdapter (val context: Context, val feeds: List<SeeProfileQuery.Feed>?) : BaseAdapter() {
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{

        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_profile_feeds, null)

        val image = view.findViewById<ImageView>(R.id.contentsImage)
        val title = view.findViewById<TextView>(R.id.contentsTitle)
        val text = view.findViewById<TextView>(R.id.contentsText)
        val item = feeds?.get(position)
        Glide.with(view).load(item?.file.toString()).into(image)
        title.text = item?.title
        text.text = item?.caption

        return view
    }

    override fun getCount(): Int {
        if (feeds != null) {
            return feeds.size
        }
        else {
            return 0
        }
    }

    override fun getItem(position: Int): SeeProfileQuery.Feed? {
        return feeds?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

}