package com.example.plat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView

/**
 * 서치 창 화면
 */
class SearchFragment : Fragment() {

    private val myList = arrayListOf<SearchContentsListItem>(
        SearchContentsListItem("Image1","Text1"),
        SearchContentsListItem("Image2","Text2"),
        SearchContentsListItem("Image3","Text3"),
        SearchContentsListItem("Image4","Text4"),
        SearchContentsListItem("Image5","Text5"),
        SearchContentsListItem("Image6","Text6"),
        SearchContentsListItem("Image7","Text7"),
        SearchContentsListItem("Image8","Text8"),
        SearchContentsListItem("Image9","Text9"),
        SearchContentsListItem("Image0","Text0"),
        SearchContentsListItem("Image9","Text9"),
        SearchContentsListItem("Image8","Text8"),
        SearchContentsListItem("Image7","Text7"),
        SearchContentsListItem("Image6","Text6"),
        SearchContentsListItem("Image5","Text5"),
        SearchContentsListItem("Image4","Text4"),
        SearchContentsListItem("Image3","Text3"),
        SearchContentsListItem("Image2","Text2"),
        SearchContentsListItem("Image1","Text1"),
        SearchContentsListItem("Image2","Text2"),
        SearchContentsListItem("Image3","Text3"),
        SearchContentsListItem("Image4","Text4"),
        SearchContentsListItem("Image5","Text5")
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search, null)
        val searchListView = view.findViewById<GridView>(R.id.searchGridView)
        val searchListAdapter = SearchListAdapter(activity!!, myList)

        searchListView.adapter = searchListAdapter

        return view
    }

}

//이미지의 경우 임시로 String타입 줬음
class SearchContentsListItem(val image: String, val text: String)

//listAdapter
class SearchListAdapter(val context: Context, val searchContentsListItem: ArrayList<SearchContentsListItem>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //각 리스트 형태 저장
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_search, null)

        //각 리스트 내용
        val image = view.findViewById<TextView>(R.id.searchImage)
        val text = view.findViewById<TextView>(R.id.searchText)
        val item = searchContentsListItem[position]

        image.text = item.image
        text.text = item.text

        return view
    }

    override fun getCount(): Int {
        return searchContentsListItem.size
    }

    override fun getItem(position: Int): Any {
        return searchContentsListItem[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

}