package com.example.plat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentTransaction
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 서치 창 화면
 */
class SearchFragment(val mainActivity: MainActivity) : Fragment() {

    val apolloClient = apolloClient(mainActivity.applicationContext)
    val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_search, null)

        val searchInput = view.findViewById<EditText>(R.id.searchInput)


        view.findViewById<Button>(R.id.searchSubmitButton).setOnClickListener { view ->
            val input = searchInput.text.toString()
            scope.launch {
                val response : Response<SearchGroupsQuery.Data> =
                    apolloClient.query(SearchGroupsQuery(input)).await()

                val result = response.data?.searchGroups

                val fragmentTransactionListener: FragmentTransaction = childFragmentManager.beginTransaction()
                fragmentTransactionListener.replace(R.id.searchedLayout, SearchChildResult(result))
                fragmentTransactionListener.commit()
            }

        }

        return view
    }

    class SearchChildResult(val list : List<SearchGroupsQuery.SearchGroup>?):Fragment(){
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view: View = inflater.inflate(R.layout.fragment_search_child_result, null)
            val layout = view.findViewById<GridView>(R.id.searchGridView)
            val searchListAdapter = SearchListAdapter(this, activity!!, list)

            layout.adapter = searchListAdapter

            return view
        }
    }
}

//listAdapter
class SearchListAdapter(val fragment: Fragment, val context: Context, var searchedItems: List<SearchGroupsQuery.SearchGroup>?) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //각 리스트 형태 저장
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_search, null)

        //각 리스트 내용
        val image = view.findViewById<TextView>(R.id.searchedImage)
        val text = view.findViewById<TextView>(R.id.searchedText)
        val item = searchedItems?.get(position)

        image.text = item?.groupPhoto
        text.text = item?.title

        //검색된 아이템 클릭 리스너
        view.findViewById<LinearLayout>(R.id.searchedItem).setOnClickListener { view ->
            DialogSearchPlat(item)
                .show(fragment.childFragmentManager.beginTransaction(), DialogSearchPlat(item).tag)
        }
        return view
    }

    override fun getCount(): Int {
        return searchedItems!!.size
    }

    override fun getItem(position: Int): Any {
        return searchedItems!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }


}

