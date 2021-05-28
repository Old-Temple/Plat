package com.example.plat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.GridView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

/**
 * 서치 창 화면
 */
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var list = loadItems("")
        val view: View = inflater.inflate(R.layout.fragment_search, null)
        val searchListView = view.findViewById<GridView>(R.id.searchGridView)
        var searchListAdapter = SearchListAdapter(activity!!, list)

        view.findViewById<EditText>(R.id.searchInput).addTextChangedListener {
            list = loadItems(view.findViewById<EditText>(R.id.searchInput).text.toString())
            searchListAdapter = SearchListAdapter(activity!!, list)

            searchListView.adapter = searchListAdapter
        }

        return view
    }

    fun loadItems(text:String):ArrayList<SearchedItem>{
        //todo : 검색 결과 서버에서 가져와함.
        // 일단 테스트용으로 입력될 때마다 리스트 추가되는 것으로 만듬
        val list = arrayListOf<SearchedItem>()

        for(i in 0..text.length){
            list.add(SearchedItem(text, text))
        }

        return list
    }
}

//이미지의 경우 임시로 String타입 줬음
class SearchedItem(val image: String, val text: String)

//listAdapter
class SearchListAdapter(val context: Context, val searchedItems: ArrayList<SearchedItem>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //각 리스트 형태 저장
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_search, null)

        //각 리스트 내용
        val image = view.findViewById<TextView>(R.id.searchedImage)
        val text = view.findViewById<TextView>(R.id.searchedText)
        val item = searchedItems[position]

        image.text = item.image
        text.text = item.text

        return view
    }

    override fun getCount(): Int {
        return searchedItems.size
    }

    override fun getItem(position: Int): Any {
        return searchedItems[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

}